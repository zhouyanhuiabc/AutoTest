package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.model.AddUserCase;
import com.tester.model.LoginCase;
import com.tester.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {
    @Test(dependsOnGroups = "loginTrue",description = "测试增加用户接口")
    public void addUserTest() throws IOException {
        SqlSession sqlSession= DatabaseUtil.getSqlSession();
        AddUserCase addUserCase=sqlSession.selectOne("addUserCase",1);
        System.out.println(TestConfig.addUserUrl);
        System.out.println(addUserCase.toString());
    }
}
