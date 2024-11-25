package com.example.planme.ui.views.chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planme.data.models.Message;
import com.example.planme.data.repository.MessageRepository;
import com.example.planme.ui.models.CardMessageUI;
import com.example.planme.ui.models.MessageUI;
import com.example.planme.ui.models.UserUI;
import com.example.planme.utils.DateFormatHelper;
import com.example.planme.utils.GenerateID;
import com.example.planme.utils.Mapper;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

public class ChatViewModel extends ViewModel {
    final MessageRepository messageRepository;
    private final MutableLiveData<List<CardMessageUI>> messages;
    private final FirebaseUser userSession;
    public ChatViewModel(){
        this.messageRepository = new MessageRepository(
                FirebaseDatabase.getInstance().getReference());

        this.messages = new MutableLiveData<>(new ArrayList<>());
        this.userSession = FirebaseAuth.getInstance().getCurrentUser();
    }


    public LiveData<List<CardMessageUI>> getMessageGroup(String groupID){
        UserUI user = new UserUI(userSession.getUid(),
                userSession.getPhotoUrl(),
                userSession.getEmail(),
                userSession.getDisplayName());

        this.messageRepository.getAllMessageToGroup(groupID,
                messages -> {
                    List<CardMessageUI> lstMessages = new ArrayList<>();
                    lstMessages = messages.stream()
                            .map(msg -> Mapper.messageToCard(msg, user))
                            .collect(Collectors.toList());

                    this.messages.setValue(lstMessages);
                },
                (exception)->{}
        );

        return this.messages;
    }

    public void sendMessage(String groupId, String content){
        Message message = new Message();
        message.setContent(content);
        message.setGroupId(groupId);
        message.setUserId(userSession.getUid());
        message.setDate(DateFormatHelper.getCurrentDateTime());
        message.setUserName(userSession.getDisplayName());

        if(userSession.getPhotoUrl() != null){
            message.setUrlImg(userSession.getPhotoUrl().toString());
        }

        this.messageRepository.addMessageToGroup( groupId, message, exception -> {
            if(exception == null){
                // validar si hay un error
            }
        });
    }


}