package com.ceng.webonline.login;

import com.ceng.webonline.homepage.HomePageImpl;
import com.ceng.webonline.redirectLoginPage.RedirectLoginPageImpl;

public interface ILogin {
    void submit();
    HomePageImpl login(String username, String password);
    RedirectLoginPageImpl loginWithWrong(String username, String password);

    Boolean isLoggedIn();

    HomePageImpl logout();

}
