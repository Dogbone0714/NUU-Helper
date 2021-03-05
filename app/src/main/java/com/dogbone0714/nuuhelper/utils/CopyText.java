package com.dogbone0714.nuuhelper.utils;

import android.content.ClipboardManager;
import android.content.Context;

import com.dogbone0714.nuuhelper.Application;

/**
 * 复制文字到剪贴板
 * Created by Dogbone0714 on 2020.03.06
 */
public class CopyText {
    public static void settext(String str){
        ClipboardManager cm = (ClipboardManager) Application.getINSTANCE().getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText(str);
        Toast.show("複製到剪貼簿");
    }
}
