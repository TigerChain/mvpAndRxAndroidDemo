package com.jun.mvpdemo;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jun.mvpdemo.domain.User;
import com.jun.mvpdemo.inter.OnLoginListener;
import com.jun.mvpdemo.presenter.IUserLoginPresenter;
import com.jun.mvpdemo.presenter.UserLoginPresenterImpl;
import com.jun.mvpdemo.view.IUserLoginView;

public class MainActivity extends AppCompatActivity implements IUserLoginView,OnLoginListener{


    private EditText userName ,userPass ;

    private Button login_button,login_clear ;



    private ProgressDialog progressDialog ;
    private IUserLoginPresenter iUserLoginPresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iUserLoginPresenter = new UserLoginPresenterImpl(this,this) ;

        initView() ;
    }

    private void initView() {

        userName = (EditText) this.findViewById(R.id.userName) ;
        userPass = (EditText) this.findViewById(R.id.userPass) ;


        login_button = (Button) this.findViewById(R.id.login_button) ;
        login_clear = (Button) this.findViewById(R.id.login_clear) ;


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName_content = userName.getText().toString().trim() ;
                String userPass_content = userPass.getText().toString().trim() ;

                if(TextUtils.isEmpty(userName_content) || TextUtils.isEmpty(userPass_content)){
                    Toast.makeText(MainActivity.this,"用户名或密码不能为空!!",Toast.LENGTH_SHORT).show();
                  return ;
                }

                iUserLoginPresenter.login(MainActivity.this);

            }
        });


        login_clear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                iUserLoginPresenter.clear();

            }
        });
    }

    @Override
    public String getUserName() {
        return userName.getText().toString();
    }

    @Override
    public String getUserPass() {
        return userPass.getText().toString();
    }

    @Override
    public void clearUserPass() {

    }

    @Override
    public void clearUserName() {

        userPass.setText("");
        userName.setText("");
    }

    @Override
    public void showProgress() {

        progressDialog = new ProgressDialog(this) ;
        progressDialog.setMessage("正在登录...");
        progressDialog.show();

    }

    @Override
    public void hidePorgress() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(progressDialog!=null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                    progressDialog = null ;
                }

            }
        },2000) ;

    }

    @Override
    public void loginSuccess(User user) {

        Toast.makeText(this,user.getUserName()+"登录成功",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loginFailed() {



        Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();

    }
}
