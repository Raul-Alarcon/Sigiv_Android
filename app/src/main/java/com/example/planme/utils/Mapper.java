package com.example.planme.utils;

import com.example.planme.data.models.Group;
import com.example.planme.data.models.Message;
import com.example.planme.ui.models.GroupUI;
import com.example.planme.ui.models.MessageUI;

public class Mapper {

    public static GroupUI groupToUI(Group group){
        GroupUI groupUI = new GroupUI();
        groupUI.setId(group.getId());
        groupUI.setName(group.getName());
        groupUI.setDescription(group.getDescription());
        groupUI.setDate(group.getDate());

        if(!group.getMessages().isEmpty()){
            int lastIndex = group.getMessages().size() - 1;
            MessageUI messageUI = messageToUI(
                    group.getMessages().get(lastIndex));
            groupUI.setLastMessage(messageUI);
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
