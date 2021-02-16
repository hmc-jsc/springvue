package com.dataspring.dataspring;

import com.dataspring.dataspring.entity.data;
import com.dataspring.dataspring.entity.login;
import com.dataspring.dataspring.response.dataResponse;
import com.dataspring.dataspring.response.loginResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.List;

@SpringBootTest
class DataspringApplicationTests {
    @Autowired
    private loginResponse loginResponse;
    @Autowired
    private dataResponse dataResponse;
    @Test
    void contextLoads() {
    }
    @Test
    void  findAll(){
        List<login> list =  loginResponse.findAll();
        System.out.println(list);

    }
    @Test
    void svae()throws Exception{
        String saveName = "123";
        String savepwd = DigestUtils.md5DigestAsHex("1345679ju".getBytes());
//        String savepwd ="123456";
        login savelogin = new login();
        savelogin.setName(saveName);
        savelogin.setPwd(savepwd);
        login login1 = loginResponse.save(savelogin);
        System.out.println(login1);
    }
    @Test
    void login()throws Exception{
        String name = "123";
        String savepwd = DigestUtils.md5DigestAsHex("1345679j".getBytes());
        login login1 = loginResponse.findById("123").get();
        System.out.println(login1.getPwd().equals(savepwd)? "yes": "no");
    }
    @Test
    void dataFindAll(){
        List<data> data = dataResponse.findAll();
        System.out.println(data);
    }
}
