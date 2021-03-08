package com.dogbone0714.nuuhelper.view.iview;

import android.content.Context;
import android.widget.EditText;

import com.dogbone0714.nuuhelper.modle.bean.Love;
import com.dogbone0714.nuuhelper.modle.bean.LoveComment;

import java.util.List;

/**
 * Created by longer on 2017/4/13.
 */

public interface ILoveOne_ActivityView {

    void setSms(Integer sms);

    void setLike(Integer num);

    void showCommentView();

    void hideComentView();

    void showCommenting();

    void setCommentData(List<LoveComment> list);

    void addCommentData(LoveComment comment);

    void showsetCommentDataFail();

    void showCommentFail();

    void clearCommnet();

    Love getLove();

    String getComment();

    EditText getedittext();

    boolean checksblike();



}
