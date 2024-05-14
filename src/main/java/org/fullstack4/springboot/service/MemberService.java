package org.fullstack4.springboot.service;

import org.fullstack4.springboot.dto.MemberDTO;

public interface MemberService {
    public MemberDTO login_info(String member_id, String member_pwd);
    public int pwdFind(String member_id);
}
