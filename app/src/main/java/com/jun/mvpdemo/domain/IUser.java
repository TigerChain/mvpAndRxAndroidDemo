package com.jun.mvpdemo.domain;


import rx.Observable;

/**
 * Created by chenjunjun on 1/4/16.
 */
public interface IUser {


    /**
     * 登录方法
     * @param userName
     * @param userPass
     * @return
     */
    Observable<User> login(String userName,String userPass) ;
}
