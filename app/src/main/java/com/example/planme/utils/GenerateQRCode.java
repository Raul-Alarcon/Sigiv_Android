package com.example.planme.utils;

import android.graphics.Bitmap;

import com.example.planme.ui.models.GroupUI;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

public class GenerateQRCode {
    public static Bitmap qRCode(GroupUI groupUI) throws Exception {
        try {
            JSONObject groupData = new JSONObject();
            groupData.put("name", groupUI.getName());
            groupData.put("description", groupUI.getDescription());
            groupData.put("code", groupUI.getCode());
            groupData.put("date", groupUI.getDate());
//            groupData.put("userId", userId);

            String groupInfo = groupData.toString();

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

            BitMatrix bitMatrix = multiFormatWriter.encode(
                    groupInfo,
                    BarcodeFormat.QR_CODE,
                    500,
                    500
            );

            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

            return barcodeEncoder.createBitmap(bitMatrix);

        } catch (WriterException | JSONException e) {
            throw new Exception(e);
        }
    }
}
