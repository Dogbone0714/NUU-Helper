package com.dogbone0714.nuuhelper.modle.biz;

import android.content.Intent;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dogbone0714.nuuhelper.R;
import com.dogbone0714.nuuhelper.modle.bean.Good;
import com.dogbone0714.nuuhelper.utils.Toast;
import com.dogbone0714.nuuhelper.view.activity.Goods_Activity;

import java.util.List;
/*
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
*/
/**
 * Created by longer on 2017/4/23.
 */

public class GoodBiz implements Good.GoodBiz {
    @Override
    public void getgoods(final Good.OnGetGoods onGetGoods) {
        //得到数据
        BmobQuery<Good> query = new BmobQuery<Good>();
        query.order("-createdAt");
        query.setLimit(6);
        query.findObjects(new FindListener<Good>() {
              @Override
              public void done(List<Good> list, BmobException e) {
                  if (e != null) {
                      e.printStackTrace();
                      onGetGoods.Failed();
                  }else{
                      onGetGoods.Success(list);
                  }
              }
          }
        );
    }
}
