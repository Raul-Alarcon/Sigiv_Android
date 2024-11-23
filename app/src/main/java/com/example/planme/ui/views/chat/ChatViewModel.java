package com.example.planme.ui.views.chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planme.ui.models.CardMessageUI;
import com.example.planme.utils.GenerateID;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {
    private MutableLiveData<List<CardMessageUI>> messages;
    public ChatViewModel(){
        this.messages = new MutableLiveData<>(new ArrayList<>());
        this.initialize(); // temporalmente para pruebas
    }

    public void initialize(){
        List<CardMessageUI> msg = this.getMessage();
        messages.setValue(msg);
    }

    public LiveData<List<CardMessageUI>> getCardMessageUI(){
        return this.messages;
    }

    private List<CardMessageUI> getMessage() {
        // TODO: FAKE DATA
        List<CardMessageUI> lst = new ArrayList<>();
        String idUserSession = GenerateID.invoke();

        lst.add(new CardMessageUI(GenerateID.invoke(), "klasdlkas dalksjdnalksd aslkd as", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "asd asd asd asdasda sdasd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "as dasd asd asdasdasdasdas d", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "asd asda sdasdasdas dasd asd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new CardMessageUI(GenerateID.invoke(), ".", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));

        lst.add(new CardMessageUI(GenerateID.invoke(), "klasdlkas dalksjdnalksd aslkd as", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "asd asd asd asdasda sdasd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "as dasd asd asdasdasdasdas d", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "asd asda sdasdasdas dasd asd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new CardMessageUI(GenerateID.invoke(), ".", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));

        lst.add(new CardMessageUI(GenerateID.invoke(), "klasdlkas dalksjdnalksd aslkd as", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "asd asd asd asdasda sdasd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "as dasd asd asdasdasdasdas d", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "asd asda sdasdasdas dasd asd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new CardMessageUI(GenerateID.invoke(), ".", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));

        lst.add(new CardMessageUI(GenerateID.invoke(), "klasdlkas dalksjdnalksd aslkd as", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "asd asd asd asdasda sdasd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "as dasd asd asdasdasdasdas d", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "asd asda sdasdasdas dasd asd", "8:05 PM", GenerateID.invoke(), "", idUserSession, "other user"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new CardMessageUI(GenerateID.invoke(), ".", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        lst.add(new CardMessageUI(GenerateID.invoke(), "klasd asd asd asd", "8:05 PM", idUserSession, "", idUserSession, "marcos"));
        return lst;
    }
}