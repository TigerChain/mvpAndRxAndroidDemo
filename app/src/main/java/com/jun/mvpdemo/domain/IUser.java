package com.jun.mvpdemo.domain;


import com.jun.mvpdemo.inter.OnLoginListener;

import rx.Observable;

/**
 * Created by chenjunjun on 1/4/16.
 */
public interface IUser {


    /**
     * 登录方法
     * @param userName
     * @param userPass
     * @param listener
     * @return
     */
    Observable<User> login(String userName,String userPass,OnLoginListener listener) ;
}
