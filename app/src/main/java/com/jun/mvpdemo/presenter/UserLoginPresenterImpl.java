package com.jun.mvpdemo.presenter;

import android.util.Log;

import com.jun.mvpdemo.domain.IUser;
import com.jun.mvpdemo.domain.User;
import com.jun.mvpdemo.view.IUserLoginView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenjunjun on 1/4/16.
 */
public class UserLoginPresenterImpl implements IUserLoginPresenter {

    private IUser iUser ;
    private IUserLoginView iUserLoginView ;


   public  UserLoginPresenterImpl(IUserLoginView iUserLoginView, IUser iUser){

       this.iUserLoginView = iUserLoginView ;

       this.iUser = iUser;

    }


    @Override
    public void login() {
        iUserLoginView.showProgress();
        String userName = iUserLoginView.getUserName().trim();
        String userPass = iUserLoginView.getUserPass().trim();
        iUser.login(userName, userPass) // 只有逻辑，没有分配到具体线程
        .subscribeOn(Schedulers.io()) // 指明在异步线程中调用(订阅)
        .observeOn(AndroidSchedulers.mainThread()) // 通知到主线程中
        .subscribe(new Subscriber<User>() { // 因为上一步通知到了主线程，所以下面调用(订阅)也就是在主线程了。
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                iUserLoginView.hidePorgress();
                iUserLoginView.showFail("登录失败");


            }

            @Override
            public void onNext(User user) {
                String successMsg = user.getUserName() + "登录成功";
                iUserLoginView.showSuccess(successMsg);
                Log.e("===", "login success");
                iUserLoginView.hidePorgress();

            }
        });
    }

    @Override
    public void clear() {
        iUserLoginView.clearUserName();
        iUserLoginView.clearUserPass();
    }
}
