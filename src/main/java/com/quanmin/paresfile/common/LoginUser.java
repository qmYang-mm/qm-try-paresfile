package com.quanmin.paresfile.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUser implements Serializable {

    public static final int MEMBER = 1; // 会员
    public static final int SYSTEM_USER = 2; // 系统用户

    /**
     * 登录类型， 是会员登录，还是后台管理用户登录
     */
    private int type;

    /**
     * 用户ID
     */
    private int id;

    /**
     * 用户名称
     */
    private String nickname;
}
