package com.example.planme.ui.views.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planme.data.models.Group;
import com.example.planme.data.models.MemberGroup;
import com.example.planme.data.repository.GroupRepository;
import com.example.planme.data.repository.MemberGroupRepository;
import com.example.planme.data.repository.MessageRepository;
import com.example.planme.ui.models.GroupUI;
import com.example.planme.ui.models.MessageException;
import com.example.planme.utils.DateFormatHelper;
import com.example.planme.utils.GenerateCode;
import com.example.planme.utils.Mapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class HomeViewModel extends ViewModel {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser userSession;
    final List<GroupUI> dataCache = new ArrayList<>();
    private final MessageException messageException;
    final GroupRepository groupRepository;
    final MemberGroupRepository memberGroupRepository;
    final MessageRepository messageRepository;
   private final MutableLiveData<List<GroupUI>> groups;
   private final MutableLiveData<MessageException> exception;
   private final MutableLiveData<GroupUI> group;


    public HomeViewModel() {
        this.groupRepository = new GroupRepository(db.getReference());
        this.memberGroupRepository = new MemberGroupRepository(db.getReference());
        this.messageRepository = new MessageRepository(db.getReference());

        this.groups = new MutableLiveData<>();
        this.exception = new MutableLiveData<>(new MessageException());
        this.group = new MutableLiveData<>();

        userSession = auth.getCurrentUser();
        this.messageException = new MessageException();

        this.initialize();
    }
    private void initialize() {

        if(userSession != null){
            groupRepository
                    .getGroupsByUserId(userSession.getUid(), (groups, exception) -> {
                        if(exception == null){
                            dataCache.clear();
                            dataCache.addAll(groups.stream()
                                    .map(Mapper::groupToUI).collect(Collectors.toList()));

                            Collections.reverse(dataCache);

                            this.groups.setValue(dataCache);
                        } else {
                            messageException.setContent("Error al cargar los grupos");
                            messageException.setActive(true);
                            messageException.setStack(exception.getMessage());
                            this.exception.setValue(messageException);
                            messageException.setActive(false);
                        }
                    });

            this.initializeMember();
        }

    }
    private void initializeMember(){
        memberGroupRepository.listenOnAddMember( userSession.getUid(), (member, exception) -> {
            if(exception == null){
                groupRepository.getGroupById(member.getGroupId(),(group, _exception) ->{
                    if(_exception == null){
                        GroupUI groupUI = Mapper.groupToUI(group);
                        if(!dataCache.contains(groupUI)){
                            dataCache.add(groupUI);
                            this.groups.setValue(dataCache);
                        }
                    }
                });
            }
        });
    }

    public void addGroup(GroupUI groupUI){
        Group group = new Group();
        group.setName(groupUI.getName());
        group.setDescription(groupUI.getDescription());
        group.setUserId(userSession.getUid());
        group.setDate(DateFormatHelper.getCurrentDateTime());
        group.setCode(GenerateCode.generateUniqueCode());
        
        groupRepository.add(group, (exception) -> {
            if(exception != null){
                messageException.setActive(true);
                messageException.setContent("Ocurrio un problema intentelo mas tarde");
                messageException.setStack(exception.getMessage());
                this.exception.setValue(messageException);
                messageException.setActive(false);
            }
        });
    }

    public void findByCode(String code){
        this.groupRepository.getGroupByCode(code,(group, exception) -> {
            if(exception == null && group != null){
                GroupUI groupUI = Mapper.groupToUI(group);
                this.group.setValue(groupUI);
            }
        });
    }

    public void addMemberGroup(GroupUI groupUI){
        MemberGroup member = new MemberGroup();
        member.setGroupId(groupUI.getId());
        member.setMember(userSession.getUid());

        this.memberGroupRepository.addMemberToGroup( groupUI.getId(),
            member,(exception) -> {
                if(exception == null){

                }
        });
    }

    public CompletableFuture<Boolean> deleteGroup(GroupUI groupUI){
        return this.groupRepository.delete(groupUI.getId())
                .thenCompose(isGroupDeleted -> {
                    if (isGroupDeleted) {
                        return messageRepository.deleteMessageToGroup(groupUI.getId());
                    } else {
                        return CompletableFuture.completedFuture(false);
                    }
                }).thenCompose( isMemberDeleted -> {
                    if(isMemberDeleted){
                        return memberGroupRepository.deleteMemberToGroup(groupUI.getId());
                    } else {
                        return  CompletableFuture.completedFuture(false);
                    }
                });
    }

    public CompletableFuture<Boolean> exit(GroupUI groupUI){
        String userId = this.userSession.getUid();
        return  this.memberGroupRepository.exitToGroup(userId)
                .thenCompose( isExit -> {
                    if (isExit) {
                        this.excludeGroup(groupUI);
                    }
                    return CompletableFuture.completedFuture(isExit);
                });
    }

    private void excludeGroup(GroupUI groupUI){
        List<GroupUI> filterExclude = this.dataCache.stream()
                .filter(g -> !g.getId().equals(groupUI.getId()))
                .collect(Collectors.toList());
        this.dataCache.clear();
        this.dataCache.addAll(filterExclude);
        this.groups.setValue(dataCache);
    }

    public LiveData<List<GroupUI>> getGroups() { return this.groups; }
    public LiveData<MessageException> getException(){ return this.exception; }
    public LiveData<GroupUI> getGroup() { return this.group; }
}