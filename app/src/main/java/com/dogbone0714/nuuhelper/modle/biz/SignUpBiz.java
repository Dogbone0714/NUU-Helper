package com.dogbone0714.nuuhelper.modle.biz;

import android.util.Log;

import com.dogbone0714.nuuhelper.Application;
import com.dogbone0714.nuuhelper.modle.bean.Love;
import com.dogbone0714.nuuhelper.modle.bean.PicHeadTip;
import com.dogbone0714.nuuhelper.modle.bean.SignUp;
import com.dogbone0714.nuuhelper.modle.bean.User;

import java.util.List;
/*
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
*/
/**
 * Created by longer on 2017/4/17.
 */

public class SignUpBiz implements SignUp.ISignUpBiz {

    @Override
    public void set(String object, String name, String tel, String bj, String infor, final SignUp.OnsetSignUpLister onsetSignUpLister) {
        SignUp signUp = new SignUp();
        signUp.setMy(Application.my);
        signUp.setBj(bj);
        signUp.setName(name);
        signUp.setTel(tel);
        signUp.setInfor(infor);
        signUp.setObject(object);
        signUp.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e != null) {
                    e.printStackTrace();
                    onsetSignUpLister.Failed();
                } else {
                    onsetSignUpLister.Success();
                }
            }
        });
    }
}
