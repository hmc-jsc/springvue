package com.dataspring.dataspring.controller;

import com.dataspring.dataspring.entity.login;
import com.dataspring.dataspring.response.loginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/login")
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class LoginController {
    @Autowired
    private loginResponse loginResponse;
//登录验证
    @PostMapping("/pass")
    public String pass(@RequestBody login login,HttpServletResponse response ,HttpServletRequest request){
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        String name = login.getName();
        if (name.equals("undefined")){
            return "user";
        }
        if (!loginResponse.findById(name).isPresent()){
            return "user";    //处理返回对象是否为空
        }
        System.out.println(login);
        String pwd = DigestUtils.md5DigestAsHex(login.getPwd().getBytes());
        if (loginResponse.findById(name).get().getPwd().equals(pwd)){
            return "yes";
        }
        return "password";
    }

//注册
    @PutMapping("/save")
    public String regedit(@RequestBody login login,HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        String name =login.getName();
        String pwd = login.getPwd();
        if(loginResponse.findById(name).isPresent()){
            return "no";
        }
        String  cahce = (int)(1+Math.random()*10000)+"";
        login.setCrypt(cahce);
        login.setPwd( DigestUtils.md5DigestAsHex(pwd.getBytes()));
        loginResponse.save(login);
        return "yes";
    }

//cookie设置
    @GetMapping("/cookie/{name}")
    public String myCookie(HttpServletResponse response,HttpServletRequest request,@PathVariable("name") String name){
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        if (name.equals("undefined")){
            return "no";
        }
        Cookie cookie =new Cookie("name",name.trim());
        cookie.setMaxAge(60*60);
        cookie.setPath("/");
        response.addCookie(cookie);
        System.out.println(name);
        return "yes";
    }
//cookie检查
    @GetMapping("/passCookie")
    public String passCookie(HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("name")) {
                    if (!cookie.getValue().equals(null)) {
                        if (cookie.getValue().equals("admin")) {
                            return "admin";
                        }else return "client";
                    }
                }
            }
        }
        return "noPass";
    }
//cookie删除
    @GetMapping("/exit")
    public String clearCookie(HttpServletResponse response,HttpServletRequest request){
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setValue(null);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        return "";
    }

}
