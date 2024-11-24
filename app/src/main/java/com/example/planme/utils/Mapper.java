package com.example.planme.utils;

import com.example.planme.data.models.Group;
import com.example.planme.data.models.Message;
import com.example.planme.ui.models.GroupUI;
import com.example.planme.ui.models.MessageUI;

import java.util.Date;

public class Mapper {

    public static GroupUI groupToUI(Group group){
//        Date dateGroup =  DateFormatHelper.stringToDate(group.getDate());
//        String formatDateGroup = DateFormatHelper.format(dateGroup, "HH:mm a");

        GroupUI groupUI = new GroupUI();
        groupUI.setId(group.getId());
        groupUI.setName(group.getName());
        groupUI.setDescription(group.getDescription());

        groupUI.setDate(group.getDate());

        if(!group.getMessages().isEmpty() && group.getMessages() != null){
            int lastIndex = group.getMessages().size() - 1;
            Message lastMessage = group.getMessages().get(lastIndex);

            String dateFormat = DateFormatHelper.format(lastMessage.getDate(), "HH:mm a");
            groupUI.setContentLastMessage(lastMessage.getContent());
            groupUI.setDateLastMessage(dateFormat);
        }

        return  groupUI;
    }

    public static MessageUI messageToUI(Message message){
        String dateFormat = DateFormatHelper.format(message.getDate(), "HH:mm a");
        return new MessageUI(
                message.getId(),
                message.getContent(),
                dateFormat,
                message.getUser().getId(),
                "");
    }
}
