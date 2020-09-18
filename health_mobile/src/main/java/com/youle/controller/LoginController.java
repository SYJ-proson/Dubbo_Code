package com.youle.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.youle.constant.MessageConstant;
import com.youle.constant.RedisMessageConstant;
import com.youle.entity.Result;
import com.youle.pojo.Member;
import com.youle.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Reference
    private LoginService loginService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/check")
    public Result check(HttpServletResponse response, @RequestBody Map map) {
        String validateCode = jedisPool.getResource().get(map.get("telephone").toString() + RedisMessageConstant.SENDTYPE_LOGIN);
        String validateCode1 = map.get("validateCode").toString();
        if (validateCode != null && validateCode1 != null && validateCode.equals(validateCode1)) {
            Member member = loginService.findByTelephone(map.get("telephone").toString());
            if (member == null) {
                member = new Member();
                member.setPhoneNumber(map.get("telephone").toString());
                member.setRegTime(new Date());
                loginService.add(member);
            }
            Cookie cookie = new Cookie("login_member_telephone", map.get("telephone").toString());
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
            String json = JSON.toJSON(member).toString();
            jedisPool.getResource().setex(map.get("telephone").toString(), 60 * 30, json);
            return new Result(true, MessageConstant.LOGIN_SUCCESS);
        } else {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }
}
