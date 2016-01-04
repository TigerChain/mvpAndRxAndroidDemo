package com.jun.mvpdemo.presenter;

import android.content.Context;
import android.util.Log;

import com.jun.mvpdemo.domain.IUser;
import com.jun.mvpdemo.domain.User;
import com.jun.mvpdemo.domain.UserImpl;
import com.jun.mvpdemo.inter.OnLoginListener;
import com.jun.mvpdemo.view.IUserLoginView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by chenjunjun on 1/4/16.
 */
public class UserLoginPresenterImpl implements IUserLoginPresenter {

    private IUser iUser ;
    private IUserLoginView iUserLoginView ;

    private Context context ;

    private OnLoginListener onLoginListener ;
   public  UserLoginPresenterImpl(IUserLoginView iUserLoginView,OnLoginListener onLoginListener){

       this.iUserLoginView = iUserLoginView ;
       this.onLoginListener = onLoginListener ;

       iUser = new UserImpl() ;

    }


    @Override
    public void login(final Context context) {
        this.context = context ;
        iUserLoginView.showProgress();
        String userName = iUserLoginView.getUserName().trim();
        String userPass = iUserLoginView.getUserPass().trim();
        iUser.login(userName, userPass,onLoginListener)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<User>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                iUserLoginView.hidePorgress();
                onLoginListener.loginFailed();


            }

            @Override
            public void onNext(User user) {
                onLoginListener.loginSuccess(user);
                Log.e("===", "login success") ;
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
