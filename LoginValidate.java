package com.dao;

import com.beans.Login;

public interface LoginValidate {
public String userValidate(String username,String password);
public String adminValidate(Login al);
}
