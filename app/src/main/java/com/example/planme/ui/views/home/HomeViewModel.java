package com.example.planme.ui.views.home;

import android.renderscript.Script;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planme.data.models.Group;
import com.example.planme.data.repository.GroupRepository;
import com.example.planme.ui.models.GroupUI;
import com.example.planme.ui.models.MessageException;
import com.example.planme.utils.DateFormatHelper;
import com.example.planme.utils.GenerateCode;
import com.example.planme.utils.Mapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.stream.Collectors;

public class HomeViewModel extends ViewModel {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser userSession;
    private final MessageException messageException;
    final GroupRepository groupRepository;
   private final MutableLiveData<List<GroupUI>> groups;
   private final MutableLiveData<MessageException> exception;


    public HomeViewModel() {
        this.groupRepository = new GroupRepository(db.getReference());
        this.groups = new MutableLiveData<>();
        this.exception = new MutableLiveData<>(new MessageException());
        userSession = auth.getCurrentUser();
        this.messageException = new MessageException();

        this.initialize();
    }
    private void initialize() {
        groupRepository
                .getGroupsByUserId(userSession.getUid(), (groups, exception) -> {
                    if(exception == null){
                        List<GroupUI> _groups = groups.stream()
                                .map(Mapper::groupToUI).collect(Collectors.toList());
                        this.groups.setValue(_groups);
                    } else {
                        messageException.setContent("Error al cargar los grupos");
                        messageException.setActive(true);
                        messageException.setStack(exception.getMessage());
                        this.exception.setValue(messageException);
                        messageException.setActive(false);
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

    public LiveData<List<GroupUI>> getGroups() { return this.groups; }
    public LiveData<MessageException> getException(){ return this.exception; }
}