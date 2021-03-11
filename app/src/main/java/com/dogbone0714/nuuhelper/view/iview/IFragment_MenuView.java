package com.dogbone0714.nuuhelper.view.iview;


import java.util.List;

/**
 * Created by longer on 2017/4/17.
 */

public interface IFragment_MenuView {



    //设置校园消息


    void setmenu_message_llshow();

    void setmenu_message_llhide();

    void setmenu_message_cardshow();

    void setmenu_message_cardhide();

    void setmenu_message_qqshow();

    void setmenu_message_qqhide();

    void setmenu_message_signshow();

    void setmenu_message_signhide();

    //设置表白墙




    //设置图书馆
    void setmenu_library_cardshow(boolean isshow);

    void setmenu_librarydata(String str);


}
