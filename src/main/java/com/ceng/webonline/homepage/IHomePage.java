package com.ceng.webonline.homepage;

import com.ceng.webonline.coursepage.ICoursePage;

public interface IHomePage {
    Boolean checkIsLoggedIn();
    ICoursePage goToCoursePage(int courseId);
}
