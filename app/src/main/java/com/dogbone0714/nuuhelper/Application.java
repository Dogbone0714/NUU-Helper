package com.dogbone0714.nuuhelper;


import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import com.blankj.utilcode.utils.Utils;
import com.dogbone0714.nuuhelper.modle.bean.User;
import com.dogbone0714.nuuhelper.view.activity.MainActivity;
import com.dogbone0714.nuuhelper.utils.FileTools;
import java.util.List;




/**
 * Created by Dogbone0714 on 2020.03.06.
 */
public class Application extends android.app.Application {

    private static Application INSTANCE;

    public static Application getINSTANCE() {
        return INSTANCE;
    }

    public static int theme;//得到用户选择的主题颜色

    public static User my = null;

    public static void setuser(User user) {
        my = user;
    }



    //初始化一个颜色，用来设置下拉菜单
    public void init() {
        String str = FileTools.getshareString("refreshcolor");
        if ("".equals(str)) {
            FileTools.saveshareInt("theme", R.style.AppTheme_NoActionBar);
            FileTools.saveshareString("refreshcolor", "#3F51B5");//保存一个颜色，用来设置下拉刷新的颜色
        }
    }

    /**
     * 得到保存的主题
     */
    private void gettheme() {
        theme = FileTools.getshareInt("theme");
        theme = theme == 404 ? R.style.AppTheme_NoActionBar : theme;
    }

    public static void settheme(int mtheme) {
        theme = mtheme;
    }


}
