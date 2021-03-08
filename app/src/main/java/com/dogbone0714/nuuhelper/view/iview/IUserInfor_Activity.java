package com.dogbone0714.nuuhelper.view.iview;

/**
 * Created by longer on 2017/5/2.
 */

public interface IUserInfor_Activity {

    String getnicename();

    String getsex();

    String getroom();

    void setCardVisibale(boolean isshow);

    void setLLVisibale(boolean ishow);

    void showSubmiting();

    void hideSubmiting();

    void toUser_Activity();

    void SubmitSuccess();

    void SubmitFailed();

}
