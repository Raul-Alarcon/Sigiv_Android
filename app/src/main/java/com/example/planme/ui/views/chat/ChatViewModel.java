package com.example.planme.ui.views.chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planme.ui.models.MessageUI;
import com.example.planme.utils.GenerateID;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {
    private MutableLiveData<List<MessageUI>> messages;
    public ChatViewModel(){
        this.messages = new MutableLiveData<>(new ArrayList<>());
        this.initialize(); // temporalmente para pruebas
    }

    public void initialize(){
        List<MessageUI> msg = this.getMessage();
        messages.setValue(msg);
    }

    public LiveData<List<MessageUI>> getMessageUI(){
        return this.messages;
    }

    private List<MessageUI> getMessage() {
        // TODO: FAKE DATA
        List<MessageUI> lst = new ArrayList<>();
        String idUserSession = GenerateID.invoke();

        lst.add(new MessageUI(GenerateID.invoke(), "klasdlkas dalksjdnalksd aslkd as", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new MessageUI(GenerateID.invoke(), "asd asd asd asdasda sdasd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new MessageUI(GenerateID.invoke(), "as dasd asd asdasdasdasdas d", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new MessageUI(GenerateID.invoke(), "asd asda sdasdasdas dasd asd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new MessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new MessageUI(GenerateID.invoke(), ".", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new MessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));

        lst.add(new MessageUI(GenerateID.invoke(), "klasdlkas dalksjdnalksd aslkd as", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new MessageUI(GenerateID.invoke(), "asd asd asd asdasda sdasd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new MessageUI(GenerateID.invoke(), "as dasd asd asdasdasdasdas d", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new MessageUI(GenerateID.invoke(), "asd asda sdasdasdas dasd asd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new MessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new MessageUI(GenerateID.invoke(), ".", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new MessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));

        lst.add(new MessageUI(GenerateID.invoke(), "klasdlkas dalksjdnalksd aslkd as", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new MessageUI(GenerateID.invoke(), "asd asd asd asdasda sdasd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new MessageUI(GenerateID.invoke(), "as dasd asd asdasdasdasdas d", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new MessageUI(GenerateID.invoke(), "asd asda sdasdasdas dasd asd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new MessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new MessageUI(GenerateID.invoke(), ".", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new MessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));

        lst.add(new MessageUI(GenerateID.invoke(), "klasdlkas dalksjdnalksd aslkd as", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new MessageUI(GenerateID.invoke(), "asd asd asd asdasda sdasd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new MessageUI(GenerateID.invoke(), "as dasd asd asdasdasdasdas d", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new MessageUI(GenerateID.invoke(), "asd asda sdasdasdas dasd asd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new MessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new MessageUI(GenerateID.invoke(), ".", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new MessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        return lst;
    }
}