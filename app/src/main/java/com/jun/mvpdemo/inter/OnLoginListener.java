package com.jun.mvpdemo.inter;

import com.jun.mvpdemo.domain.User;

/**
 * Created by chenjunjun on 1/4/16.
 */
public interface OnLoginListener {

    void loginSuccess(User user) ;
    void loginFailed() ;
}
