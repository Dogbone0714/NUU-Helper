package com.dogbone0714.nuuhelper.presenter;

import com.dogbone0714.nuuhelper.Application;
import com.dogbone0714.nuuhelper.modle.bean.User;
import com.dogbone0714.nuuhelper.modle.biz.UserBiz;
import com.dogbone0714.nuuhelper.utils.FileTools;
import com.dogbone0714.nuuhelper.view.activity.UserInfor_Activity;
import com.dogbone0714.nuuhelper.view.activity.User_Activity;
import com.dogbone0714.nuuhelper.view.iview.IUserActivityView;
import com.dogbone0714.nuuhelper.view.iview.IUserInfor_Activity;

/**
 * Created by longer on 2017/4/27.
 */

public class UserInfor_ActivityPresenter {

    private IUserInfor_Activity inforActivity;
    private User.UserBiz userBiz;

    public UserInfor_ActivityPresenter(UserInfor_Activity inforActivity) {
        this.inforActivity = inforActivity;
        userBiz = new UserBiz();
    }

    public void updata(){
        inforActivity.showSubmiting();
        userBiz.upDataNicenameandRoom(Application.my, inforActivity.getnicename(), inforActivity.getsex(), inforActivity.getroom(), new User.OnUpUser() {
            @Override
            public void Success() {
                inforActivity.hideSubmiting();
                inforActivity.SubmitSuccess();
                inforActivity.toUser_Activity();
            }

            @Override
            public void Failed() {
                inforActivity.hideSubmiting();
                inforActivity.SubmitFailed();
            }
        });
    }


}
