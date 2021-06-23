package com.example.login_userinformation;

public class LoginData {
    private String loginUserName;
    private String loginTime;
    private String loginUserEmail;

    public LoginData(String loginUserName, String loginTime, String loginUserEmail) {
        this.loginUserName = loginUserName;
        this.loginTime = loginTime;
        this.loginUserEmail = loginUserEmail;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginUserEmail() {
        return loginUserEmail;
    }

    public void setLoginUserEmail(String loginUserEmail) {
        this.loginUserEmail = loginUserEmail;
    }
}
