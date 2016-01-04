package com.jun.mvpdemo.view;

/**
 * Created by chenjunjun on 1/4/16.
 */
public interface IUserLoginView {

    String getUserName() ;
    String getUserPass() ;
    void clearUserPass() ;
    void clearUserName() ;

    void showProgress() ;
    void hidePorgress() ;

    void showSuccess(String msg);
    void showFail(String msg);
}
