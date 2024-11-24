package com.example.planme.utils;

import android.net.Uri;

import com.example.planme.data.models.Group;
import com.example.planme.data.models.Message;
import com.example.planme.ui.models.CardMessageUI;
import com.example.planme.ui.models.GroupUI;
import com.example.planme.ui.models.UserUI;

public class Mapper {

    public static GroupUI groupToUI(Group group){
        String dateFormat =  DateFormatHelper.format(group.getDate(), "HH:mm a");

        GroupUI groupUI = new GroupUI();
        groupUI.setId(group.getId());
        groupUI.setName(group.getName());
        groupUI.setDescription(group.getDescription());

        groupUI.setDate(dateFormat);

        if(!group.getMessages().isEmpty() && group.getMessages() != null){
            int lastIndex = group.getMessages().size() - 1;
            Message lastMessage = group.getMessages().get(lastIndex);

            String dateFormatLastMessage =  DateFormatHelper.format(lastMessage.getDate(), "HH:mm a");
            groupUI.setContentLastMessage(lastMessage.getContent());
            groupUI.setDateLastMessage(dateFormatLastMessage);
        }

        return  groupUI;
    }

    public static CardMessageUI messageToCard(Message message, UserUI userSession){
        String dateFormat = DateFormatHelper.format(message.getDate(), "HH:mm a");
        Uri img = userSession.getUrlImg();

        if(!message.getUserId().equals(userSession.getId())){
            img = Uri.parse(message.getUrlImg());
        }

        return  new CardMessageUI(
                message.getId(),
                message.getContent(),
                dateFormat,
                message.getUserId(),
                img,
                userSession.getId(),
                userSession.getName());
    }
}
