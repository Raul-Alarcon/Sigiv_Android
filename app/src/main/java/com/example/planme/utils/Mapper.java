package com.example.planme.utils;

import com.example.planme.data.models.Group;
import com.example.planme.data.models.Message;
import com.example.planme.ui.models.CardMessageUI;
import com.example.planme.ui.models.GroupUI;
import com.example.planme.ui.models.UserUI;

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

            // TODO: formatear las fechas para una mejor visualizacion
            //String dateFormat = DateFormatHelper.format(lastMessage.getDate(), "HH:mm a");
            groupUI.setContentLastMessage(lastMessage.getContent());
            groupUI.setDateLastMessage(lastMessage.getDate());
        }

        return  groupUI;
    }

    public static CardMessageUI messageToCard(Message message, UserUI userSession){
        return  new CardMessageUI(
                message.getId(),
                message.getContent(),
                message.getDate(),
                message.getUserId(),
                userSession.getUrlImg(),
                userSession.getId(),
                userSession.getName());
    }

//    public static MessageUI messageToUI(Message message){
//        String dateFormat = DateFormatHelper.format(message.getDate(), "HH:mm a");
//        return new MessageUI(
//                message.getId(),
//                message.getContent(),
//                dateFormat,
//                message.getUser().getId(),
//                "");
//    }
}
