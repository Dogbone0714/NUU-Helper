package com.dogbone0714.nuuhelper.view.activity;

import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.gordonwong.materialsheetfab.MaterialSheetFab;

import com.dogbone0714.nuuhelper.R;

import com.dogbone0714.nuuhelper.utils.Fab;

import com.dogbone0714.nuuhelper.utils.LoginService;

import com.nineoldandroids.view.ViewHelper;

import java.util.List;
/*
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
*/

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static Toolbar toolbar;
    private TextView tv_title;
//    private TextView tv_gg;//跳蚤市场公告
    public static AHBottomNavigation bottomNavigation;
    public Fab fab;
    private MaterialSheetFab materialSheetFab;
    private static DrawerLayout drawer;
    private Context context;
    public static MainActivity instance = null;//暴露给其他位置关闭主界面
    private long exitTime;//记录2次返回键的时间


    private OnFabClickedListener onFabClickedListener;//查询接口
//    private OnFabClickedListener2 onFabClickedListener2;//筛选接口

    public static BottomSheetBehavior bottomSheetBehavior;//底部小菜单


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        instance = MainActivity.this;
        context = MainActivity.this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);



        init();


        //如果点击了消费记录通知，跳转到消费记录界面
        bottomNavigation.setCurrentItem(getIntent().getIntExtra("it", 0));
    }

    /**
     * 检查有没有系统消息
     */


    /**
     * 显示消息,确定后保存id
     */
    private void showMessage(final String id, String mes) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle("系统提示");
        builder.setCancelable(true);
        builder.setMessage(mes);

        builder.show();
    }

    /**
     * 默认加载主界面
     */


    public void init() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("國立聯合大學");

//        tv_gg = (TextView) findViewById(R.id.tv_goods_gg);
//        tv_gg.setVisibility(View.GONE);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView tv_name = (TextView) headerView.findViewById(R.id.tv_menu_name);
        TextView tv_class = (TextView) headerView.findViewById(R.id.tv_menu_class);
        /*String str = FileTools.getshare(this, "login");
        if ("true".equals(str)) {// 表示已经成功登录过
            tv_class.setText(FileTools.getshareString("banji"));
            tv_name.setText(FileTools.getshareString("name"));
            User bmobUser = BmobUser.getCurrentUser(User.class);
            if (bmobUser != null) {
                // 允许用户使用应用
                Log.d("tip", "缓存账户不为空" + bmobUser.getName());
                Application.setuser(bmobUser);
            } else {//如果为空自动进行登录
                Log.d("tip", "缓存账户为空自动登录");
                LoginService.loginbmob(FileTools.getshareString("username"), FileTools.getshareString("password"));
            }
        }*/
        drawer.addDrawerListener(drawerListener);

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.tab_layout));
    }

    /**
     * 打开更多菜单
     */
    public static void openmore() {
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }



    //接口，  0：商品查询  1：失物查询
    public interface OnFabClickedListener {
        void onclicked(int num);
    }

    //接口，  0：商品筛选  1：失物筛选
//    public interface OnFabClickedListener2 {
//        void onclicked(int num);
//    }

    /**
     * fab 的筛选 点击事件
     */
//    View.OnClickListener listener_sx = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            //还要判断登录
//            int i = bottomNavigation.getCurrentItem();
//            if (i == 1) {
//                onFabClickedListener2.onclicked(0);
//            } else if (i == 2) {
//                onFabClickedListener2.onclicked(1);
//            }
//            materialSheetFab.hideSheet();
//        }
//    };

    /**
     * fab 的查询 点击事件
     */
    View.OnClickListener listener_cx = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //还要判断登录
            int i = bottomNavigation.getCurrentItem();
            if (i == 1) {
                onFabClickedListener.onclicked(0);
            } else if (i == 2) {
                onFabClickedListener.onclicked(1);
            }
            materialSheetFab.hideSheet();
        }
    };

    /**
     * 查询接口
     */
    public void setFabClickedListener_cx(OnFabClickedListener fabClickedListener) {
        this.onFabClickedListener = fabClickedListener;
    }

    /**
     * 筛选接口
     */
//    public void setFabClickedListener_sx(OnFabClickedListener2 fabClickedListener) {
//        this.onFabClickedListener2 = fabClickedListener;
//    }




    /**
     * 设置抽屉动画
     */
    DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            View mContent = drawer.getChildAt(0);//返回抽屉布局中的索引为0的子view
            View mMenu = drawerView;
            float scale = 1 - slideOffset;//偏移量导致scale从1.0-0.0
            float rightScale = 0.8f + scale * 0.2f;//将内容区域从1.0-0.0转化为1.0-0.8
            float leftScale = 1 - 0.3f * scale;//0.7-1.0
            ViewHelper.setScaleX(mMenu, leftScale);
            ViewHelper.setScaleY(mMenu, leftScale);
            ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));//开始这里设置成了这样，导致背景透明度有1.0-0.6
            ViewHelper.setTranslationX(mContent,
                    mMenu.getMeasuredWidth() * (1 - scale));
            ViewHelper.setPivotX(mContent, 0);
            ViewHelper.setPivotY(mContent,
                    mContent.getMeasuredHeight() / 2);
            mContent.invalidate();
            ViewHelper.setScaleX(mContent, rightScale);
            ViewHelper.setScaleY(mContent, rightScale);
        }

        @Override
        public void onDrawerOpened(View drawerView) {

        }

        @Override
        public void onDrawerClosed(View drawerView) {
            drawer.setDrawerLockMode(
                    DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };


    /**
     * 检查是否已经登录，没有返回false 并且提示更新
     *
     * @return false 没有登录
     */
    /* public boolean checkupload() {

        if (!"true".equals(str)) {// 表示已经成功登录过
            Snackbar.make(bottomNavigation, "登录校园帐号才能使用", Snackbar.LENGTH_LONG).setAction("登录", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(context, LoginSchool_Activity.class));
                }
            }).show();
            return false;
        }
        return true;
    }
/*
    public void setbottom() {
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("首页", R.drawable.ic_main_main, R.color.colorPrimary);
//        AHBottomNavigationItem item2 = new AHBottomNavigationItem("一卡通", R.drawable.ic_main_card, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("跳蚤市场", R.drawable.ic_main_tzsc, R.color.colorAccent);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("失物招领", R.drawable.ic_main_swzl, R.color.colorAccent);

// Add items
        bottomNavigation.addItem(item1);
//        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setForceTitlesDisplay(false);

        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#fafbfe"));


// Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (materialSheetFab.isSheetVisible()) {
                    materialSheetFab.hideSheet();
                    return false;
                }
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (position == 0 && !wasSelected) {
                    setToolBarVisibale(true);
                    settitle("成都工业学院");
                    showfab(false);
                    //隐藏所有fragment
                    hideFragment(ft);
                    if (fg_menu == null) {
                        fg_menu = new Fragment_Menu();
                        ft.add(R.id.content, fg_menu);
                    }
                    ft.show(fg_menu);
//                    showgg(false);
                } else if (position == 1 && !wasSelected) {
                    settitle("跳蚤市场");
                    showfab(true);
                    hideFragment(ft);
                    if (fg_good == null) {
                        fg_good = new Fragment_Goods();
                        ft.add(R.id.content, fg_good);
                    }
                    ft.show(fg_good);
//                    showgg(true);
                } else if (position == 2 && !wasSelected) {
                    settitle("失物招领");
                    showfab(true);
                    hideFragment(ft);
                    if (fg_lost == null) {
                        fg_lost = new Fragment_Lost();
                        ft.add(R.id.content, fg_lost);
                    }
                    ft.show(fg_lost);
//                    showgg(false);
                }
                ft.commit();
                return true;
            }
        });
    }

    /**
     * 方法，外面用来设置底部菜单所在位置
     *
     * @param num
     */
    public static void setBottomNavigation(int num) {
        bottomNavigation.setCurrentItem(num);
    }

    /**
     * 显示或者隐藏fab
     */
    private void showfab(boolean show) {
        if (show) {
            if (!fab.isShown())
                fab.show();
        } else {
            if (fab.isShown())
                fab.hide();
        }
    }

    /**
     * 显示公告按钮
     */
//    private void showgg(boolean show) {
//        if (show) {
//            tv_gg.setVisibility(View.VISIBLE);
//            tv_gg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, ImageActivity.class);
//                    intent.putExtra("pic_id", R.drawable.goods_gg);
//                    intent.putExtra("title_name", "公告");
//                    startActivity(intent);
//                }
//            });
//        } else {
//            tv_gg.setVisibility(View.GONE);
//        }
//    }

    //隐藏所有的fragment


    /**
     * 点击返回按键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //检查抽屉是否关闭
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return false;
        }
        //检查fab是否展开
        if (materialSheetFab.isSheetVisible()) {
            materialSheetFab.hideSheet();
            return false;
        }
        //检查sheetbehavior是否展开
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            return false;
        }

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - exitTime > 2000) {

                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * 接口，用来设置标题
     * @param title
     */
    public void settitle(String title) {
        tv_title.setText(title);
    }

    /**
     * 用来设置toolbar的隐藏与显示
     * @param isshow
     */
    public static void setToolBarVisibale(boolean isshow){
        if(isshow){
            toolbar.setVisibility(View.VISIBLE);
        }else{
            toolbar.setVisibility(View.GONE);
        }
    }


    /**
     * 用来检查更新
     */

    /**
     * 有可用更新
     */


}
