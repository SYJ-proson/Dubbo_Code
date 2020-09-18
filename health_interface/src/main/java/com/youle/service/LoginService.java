package com.youle.service;

import com.youle.pojo.Member;

public interface LoginService {

    public Member findByTelephone(String telephone);

    public void add(Member member);
}
