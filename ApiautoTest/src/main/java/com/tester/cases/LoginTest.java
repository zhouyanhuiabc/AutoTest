package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.model.InterfaceName;
import com.tester.model.LoginCase;
import com.tester.utils.ConfigFile;
import com.tester.utils.DatabaseUtil;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    @BeforeTest(groups = "loginTrue",description = "测试准备工作")
    public void beforeTest(){
        System.out.println("LoginTest.java:"+"    beforeTest()");
        //提前初始化URL
        TestConfig.getUserInfoUrl= ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl=ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.addUserUrl= ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.updateUserInfoUrl= ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.loginUrl= ConfigFile.getUrl(InterfaceName.LOGIN);

        //httpclient中设置cookie
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    }
    @Test(groups = "loginTrue",description = "用户登录成功接口测试")
    public void loginSuccess() throws IOException {
        System.out.println("测试用例：loginSuccess");
        SqlSession sqlSession= DatabaseUtil.getSqlSession();
        LoginCase loginCase=sqlSession.selectOne("loginCase",1);
        System.out.println(TestConfig.loginUrl);
        System.out.println(loginCase.toString());
    }

    @Test(groups = "loginFail",description = "用户登录成功接口测试")
    public void loginFail() throws IOException {
        System.out.println("测试用例：loginFail");
        SqlSession sqlSession=DatabaseUtil.getSqlSession();
        LoginCase loginCase=sqlSession.selectOne("loginCase",2);
        System.out.println(TestConfig.loginUrl);
        System.out.println(loginCase.toString());

    }
}
