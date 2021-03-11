package com.dogbone0714.nuuhelper.view.activity;
/*
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dogbone0714.nuuhelper.Application;
import com.dogbone0714.nuuhelper.modle.bean.User;
import com.dogbone0714.nuuhelper.R;
import com.dogbone0714.nuuhelper.utils.FileTools;
import com.dogbone0714.nuuhelper.utils.LoginService;

import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

public class LoginSchool_Activity extends AppCompatActivity {

    private Button but_login1;
    private EditText et_username;
    private EditText et_password;
    private Context context;
    private ProgressBar pb;
    private TextView tv_zhuangtai;
    private TextView tv_jiazai;
    private ProgressDialog pg;

    private final int log_success = 1;
    private final int log_false = 2;
    private final int savefish = 3;
    private final int progress = 4;
    private final int log_error = 5;// &#x5BC6;&#x7801;&#x9519;&#x8BEF;
    private final int bmob_register = 6;// bmob&#x4E91;&#x6CE8;&#x518C;&#x5931;&#x8D25;
    private final int bmob_login = 7;// bmob&#x4E91;&#x767B;&#x5F55;&#x5931;&#x8D25;
    private static int i = 0;

    // &#x4E3B;&#x7EBF;&#x7A0B;&#x521B;&#x5EFA;&#x6D88;&#x606F;&#x5904;&#x7406;&#x5668;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case progress:
                    tv_jiazai.setText("&#x5DF2;&#x7ECF;&#x52A0;&#x8F09;  " + i + "%");
                    break;
                case log_success:
                    pg.dismiss();// &#x5BF9;&#x8BDD;&#x6846;&#x6D88;&#x5931;
                    Toast.makeText(context, "&#x606D;&#x559C;&#x60A8;&#x767B;&#x5165;&#x6210;&#x529F;  ", Toast.LENGTH_SHORT).show();
                    tv_jiazai.setVisibility(View.VISIBLE);
                    pb.setVisibility(View.VISIBLE);
                    break;
                case savefish:
                    success();
                    break;
                case log_false:
                    pg.dismiss();// &#x5BF9;&#x8BDD;&#x6846;&#x6D88;&#x5931;
                    Toast.makeText(context, "&#x767B;&#x5165;&#x5931;&#x6557;", Toast.LENGTH_SHORT).show();
                    break;
                case log_error:
                    pg.dismiss();// &#x5BF9;&#x8BDD;&#x6846;&#x6D88;&#x5931;
                    Toast.makeText(context, "&#x55E8;&#x55E8;~&#x5BC6;&#x78BC;&#x932F;&#x8AA4;", Toast.LENGTH_SHORT).show();
                    break;
                /* case bmob_register:
                    Toast.makeText(context, "&#x54CE;&#x5440;~&#x6CE8;&#x518C;&#x5931;&#x8D25;&#x4E86;", Toast.LENGTH_SHORT).show();
                    break;
                case bmob_login:
                    Toast.makeText(context, "&#x54CE;&#x5440;~&#x767B;&#x5F55;&#x5931;&#x8D25;&#x4E86;", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void success() {
        FileTools.saveFile(context, "LINK.txt", "");
        FileTools.saveshareString("login","true");

        MainActivity.instance.finish();
        startActivity(new Intent(context, MainActivity.class));
        finish();

//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("&#x9080;&#x8BF7;&#x60A8;&#x8FDB;&#x7FA4;");
//        builder.setCancelable(false);
//        builder.setMessage("&#x6709;&#x4EC0;&#x4E48;&#x95EE;&#x9898; &#x6216; &#x5EFA;&#x8BAE;&#x53EF;&#x4EE5;&#x7FA4;&#x91CC;&#x53D1;&#x8069;!");
//        builder.setPositiveButton("&#x540C;&#x610F;", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                PublicTools.joinQQGroup("drHL1h7gqcaKC-kaNohbkzos5FtH6BQo");
                // &#x66F4;&#x6539;&#x6B63;&#x65B9;&#x7CFB;&#x7EDF;&#x7684;&#x94FE;&#x63A5;&#x4E3A;&#x7A7A;&#xFF08;&#x9632;&#x6B62;&#x5207;&#x6362;&#x7528;&#x6237;&#x8FC7;&#x540E;&#xFF0C;&#x8FD8;&#x662F;&#x4EE5;&#x524D;&#x7528;&#x6237;&#x7684;&#x6570;&#x636E;&#xFF09;
//                MainActivity.instance.finish();
//                startActivity(new Intent(context, MainActivity.class));
//                finish();
//            }
//        });
//        builder.setNegativeButton("&#x62D2;&#x7EDD;", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // &#x66F4;&#x6539;&#x6B63;&#x65B9;&#x7CFB;&#x7EDF;&#x7684;&#x94FE;&#x63A5;&#x4E3A;&#x7A7A;&#xFF08;&#x9632;&#x6B62;&#x5207;&#x6362;&#x7528;&#x6237;&#x8FC7;&#x540E;&#xFF0C;&#x8FD8;&#x662F;&#x4EE5;&#x524D;&#x7528;&#x6237;&#x7684;&#x6570;&#x636E;&#xFF09;
//                MainActivity.instance.finish();
//                startActivity(new Intent(context, MainActivity.class));
//                finish();
//            }
//        });
//        builder.show();

/*
    }*/
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Application.theme);
        setContentView(R.layout.activity_login_school);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("登入");
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        ButterKnife.bind(this);
        context = LoginSchool_Activity.this;

        inti();
    }
*//*
    public void inti() {
        but_login1 = (Button) findViewById(R.id.frag_but_log1);
        et_username = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        tv_zhuangtai = (TextView) findViewById(R.id.frag_tv_zhuangtai);
        tv_jiazai = (TextView) findViewById(R.id.frag_tv_jiazai);
        but_login1.setOnClickListener(new RefreshListener());

        String str = FileTools.getshareString("login");
        if ("true".equals(str)) {// 表示已经成功登录过
            tv_zhuangtai.setVisibility(View.VISIBLE);
            String string = FileTools.getshareString("name") + "  已登入";
            tv_zhuangtai.setText(string);
            et_username.setText(FileTools.getshareString("username"));
            et_username.setEnabled(false);
            et_password.setText(FileTools.getshareString("password"));
            et_password.setEnabled(false);
            but_login1.setText("註銷");
        }
*/
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("邀请您进群");
//        builder.setCancelable(false);
//        builder.setMessage("有什么问题 或 建议可以群里发聩!");
//        builder.setPositiveButton("同意", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                PublicTools.joinQQGroup(context,"drHL1h7gqcaKC-kaNohbkzos5FtH6BQo");
//            }
//        });
//        builder.setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        });
//        builder.show();
   // }


    /* class RefreshListener implements View.OnClickListener {
        public void onClick(View v) {
            if ("注销".equals(but_login1.getText())) {
                FileTools.saveshareString("login", "false");
                BmobUser.logOut();
                et_username.setText("");
                et_password.setText("");
                but_login1.setText("登入");
                et_username.setEnabled(true);
                et_password.setEnabled(true);
            } else if ("登入".equals(but_login1.getText())) {
                login();
            }
        } */

        //登录/*
     /*   private void login() {
            // 点击登录之后隐藏输入法
            InputMethodManager imm = (InputMethodManager) context.getApplicationContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et_password.getWindowToken(), 0);
            final String username = et_username.getText().toString().trim();
            final String password = et_password.getText().toString().trim();
            pg = new ProgressDialog(context);
            pg.setMessage("登入中...");
            pg.setCanceledOnTouchOutside(false);// 点击对话框以外是否消失
            pg.show();

            new Thread() {
                public void run() {/*
                    String result = LoginService.login(username, password);
                    Message mes = new Message();
                    if ("error".equals(result)) {
                        mes.what = log_error;
                        handler.sendMessage(mes);
                    } else if ("success".equals(result)) {
                        mes.what = log_success;
                        handler.sendMessage(mes);
                        // 多线程（保存用户信息）
                        save_info(username, password);
                        for (i = 0; i <= 30; i++) {
                            try {
                                mes = mes.obtain();
                                mes.what = progress;
                                handler.sendMessage(mes);
                                pb.setProgress(i);
                                sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        // 登录失败
                        mes.what = 2;
                        handler.sendMessage(mes);
                    }
                }
            }.start();
        }

    }

    /**
     * 开启线程（保存用户信息，保存20条消费记录到card.txt）
     * 之后再Bmob上注册登录
     *//*
    public void save_info(final String username, final String password) {
        new Thread() {
            public void run() {
                Message mes = new Message();
                LoginService.getcookie2();// 得到cookie2
                for (i = 30; i < 55; i++) {
                    try {
                        mes = mes.obtain();
                        mes.what = progress;
                        handler.sendMessage(mes);
                        pb.setProgress(i);
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                LoginService.getstudent(context, username, password);// 保存学生的基本信息到share
*/
                //在bmob云上注册,注册成功之后自动登录
                //User bu = new User();
                //bu.setUsername(username);
                //bu.setPassword(password);
                //对于得到的电话号码进行判断，如果格式不正确则不设置号码
                /* String phonenum = FileTools.getshareString("tel");
                if(phonenum.length() == 11) {
                    bu.setMobilePhoneNumber(phonenum);
                }
                bu.setBj(FileTools.getshareString("banji"));
                bu.setName(FileTools.getshareString("name"));
                bu.setYx(FileTools.getshareString("yuanxi"));
                bu.setZy(FileTools.getshareString("zhuanye"));
                bu.setSex(FileTools.getshareString("sex"));
                bu.setBirthday(FileTools.getshareString("birthday"));
                bu.setPlace(FileTools.getshareString("place"));
                bu.setPwd(FileTools.getshareString("password"));
                bu.signUp(new SaveListener<User>() {
                    @Override
                    public void done(User s, BmobException e) {
                        if (e == null) {
                            Log.d("tip", "注册成功");
                            //自动登录用户
                            BmobUser.loginByAccount(username, password, new LogInListener<User>() {
                                @Override
                                public void done(User user, BmobException e) {
                                    if (user != null) {
                                        Log.i("smile", "用户登陆成功");
                                        User userInfo = BmobUser.getCurrentUser(User.class);
                                        com.longer.school.Application.setuser(userInfo);
                                        Message mes = new Message();
                                        for (i = 55; i < 100; i++) {
                                            try {
                                                mes = mes.obtain();
                                                mes.what = progress;
                                                handler.sendMessage(mes);
                                                pb.setProgress(i);
                                                sleep(10);
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                            }
                                        }
                                        mes = new Message();
                                        mes.what = savefish;
                                        handler.sendMessage(mes);
                                    } else {
                                        Log.i("smile", "用户登陆失败");
                                        Message mes = new Message();
                                        mes.what = bmob_login;
                                        handler.sendMessage(mes);
                                        return;
                                    }
                                }
                            });
                        } else {//注册失败或者之前已经注册过了
                            if (e.getErrorCode() == 202) {//已经注册过，但是还需要登录
                                Log.e("tip", "已经注册过了，马上登录");
                                //自动登录用户
                                BmobUser.loginByAccount(username, password, new LogInListener<User>() {
                                    @Override
                                    public void done(User user, BmobException e) {
                                        if (user != null) {
                                            Log.i("smile", "用户登陆成功");
                                            User userInfo = BmobUser.getCurrentUser(User.class);
                                            com.longer.school.Application.setuser(userInfo);
                                            Message mes = new Message();
                                            for (i = 55; i < 100; i++) {
                                                try {
                                                    mes = mes.obtain();
                                                    mes.what = progress;
                                                    handler.sendMessage(mes);
                                                    pb.setProgress(i);
                                                    sleep(10);
                                                } catch (Exception ex) {
                                                    ex.printStackTrace();
                                                }
                                            }
                                            mes = new Message();
                                            mes.what = savefish;
                                            handler.sendMessage(mes);
                                            return;
                                        } else {
                                            Log.i("smile", "用户登陆失败");
                                            Message mes = new Message();
                                            mes.what = bmob_login;
                                            handler.sendMessage(mes);
                                            return;
                                        }
                                    }
                                });
                            }else {
                                Log.e("tip", "注册失败" + e.getErrorCode() + "--" + e.toString());
                                Message mes = new Message();
                                mes.what = bmob_register;
                                handler.sendMessage(mes);
                                return;
                            }
                        }
                    }
                }); */
           // }


  //      }.start();
  //  }
  //  /* protected void onResume() {
  //      super.onResume();
   //     MiStatInterface.recordPageStart(this, "登录界面");
   // }

  //  protected void onPause() {
 //       super.onPause();
  //      MiStatInterface.recordPageEnd();
   // }
   // */
//
