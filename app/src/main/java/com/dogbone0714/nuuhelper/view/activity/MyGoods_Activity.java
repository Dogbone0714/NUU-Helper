package com.dogbone0714.nuuhelper.view.activity;
/*
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.dogbone0714.nuuhelper.Application;
import com.dogbone0714.nuuhelper.modle.bean.Good;
import com.dogbone0714.nuuhelper.modle.bean.Lost;
import com.dogbone0714.nuuhelper.modle.bean.User;
import com.dogbone0714.nuuhelper.R;
import com.dogbone0714.nuuhelper.adapter.GoodsAdapter;
import com.dogbone0714.nuuhelper.utils.FileTools;
import com.dogbone0714.nuuhelper.utils.Toast;
//import com.xiaomi.mistatistic.sdk.MiStatInterface;

import java.util.List;
/*
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
*/
/**
 * Created by Axu on 2016/9/24.
 */
/*public class MyGoods_Activity extends AppCompatActivity {
    private Context context;
    private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private Boolean atbottom;
    private int i = 6;
    public GoodsAdapter goodsAdapter;
    public static List<Good> my_goods = null;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Application.theme);
        setContentView(R.layout.activity_mygoods);

        context = MyGoods_Activity.this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.swipe);
        mWaveSwipeRefreshLayout.setColorSchemeColors(Color.WHITE, Color.WHITE);
        mWaveSwipeRefreshLayout.setWaveColor(Color.parseColor(FileTools.getshareString("refreshcolor")));
        mWaveSwipeRefreshLayout.setMaxDropHeight(1350);
        mWaveSwipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        recyclerView = (RecyclerView) findViewById(R.id.rv_goods_main);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        gridLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnScrollListener(onScrollListener);

        toolbar.setTitle("????????????");
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        setData();
    }
*/
/*
    /**
     * ?????????????????????????????????????????????
     *//*
    public RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visible = gridLayoutManager.getChildCount();
            int total = gridLayoutManager.getItemCount();
            int past = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
            if ((visible + past) >= total) {
                if (atbottom) {
                    atbottom = false;
                }
            }
        }
    };
*/

    /**
     * ????????????
     */
 /*   public void setData() {
        if (!mWaveSwipeRefreshLayout.isRefreshing()) {
            mWaveSwipeRefreshLayout.setRefreshing(true);
        }
        User user = BmobUser.getCurrentUser(User.class);
        BmobQuery<Good> query = new BmobQuery<Good>();
        query.addWhereEqualTo("seller", user);
        query.order("-updatedAt");
        query.include("seller");
        query.setLimit(6);
        i = 6;
        boolean isCache = query.hasCachedResult(Good.class);
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> list, BmobException e) {
                if (mWaveSwipeRefreshLayout.isRefreshing()) {
                    mWaveSwipeRefreshLayout.setRefreshing(false);
                }
                if (e != null) {
                    Toast.show("???????????????????????????" + e.toString());
                    Log.d("????????????????????????", e.toString());
                    return;
                }
                my_goods = list;
                goodsAdapter = new GoodsAdapter(my_goods, context, "");
                goodsAdapter.setOnItemclicklister(itemclick);
                recyclerView.setAdapter(goodsAdapter);
                goodsAdapter.notifyDataSetChanged();
                atbottom = true;
            }
        });
    }
*/
    /**
     * ?????????????????????
     */
  /*  GoodsAdapter.Itemclick itemclick = new GoodsAdapter.Itemclick() {

        public void OnItemclick(View v, int position) {
            Good good = my_goods.get(position);
            Intent intent = new Intent(context, Goods_Activity.class);
            intent.putExtra("Good", good);
            startActivity(intent);
        }
    };*/
    /**
     * ????????????
     */ /*
    WaveSwipeRefreshLayout.OnRefreshListener onRefreshListener = new WaveSwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    setData();
                    mWaveSwipeRefreshLayout.setRefreshing(false);
                }
            }, 800);
        }
    };
    protected void onResume() {
        super.onResume();
        MiStatInterface.recordPageStart(this, "??????????????????");
    }

    protected void onPause() {
        super.onPause();
        MiStatInterface.recordPageEnd();
    }


}
*/