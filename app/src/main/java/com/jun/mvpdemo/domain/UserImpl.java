package com.jun.mvpdemo.domain;


import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by chenjunjun on 1/4/16.
 */
public class UserImpl implements IUser {


    @Override
    public Observable<User> login(final String userName, final String userPass) {
        return rx.Observable.create(new rx.Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {

                Log.e("===","name:"+userName+"-->pass:"+userPass) ;

                if(userName.equals("chen") && userPass.equals("123")){

                    User user = new User() ;
                    user.setUserName(userName);
                    user.setUserPass(userPass);
                  //  listener.loginSuccess(user);
                    subscriber.onNext(user);
                    subscriber.onCompleted();
                }else{
                    subscriber.onError(new Exception("login failed"));
                 //   listener.loginFailed();
                }
            }




        });// 只有逻辑，不应该分配到具体线程
    }
}
