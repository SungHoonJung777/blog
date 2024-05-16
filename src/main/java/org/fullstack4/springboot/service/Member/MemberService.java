package org.fullstack4.springboot.service.Member;

import org.fullstack4.springboot.dto.MemberDTO;

public interface MemberService {
    public MemberDTO login_info(String member_id, String member_pwd);
    public int pwdFind(String member_email);

    public int pwdChange(String member_pwd , String member_email);

    public int infoChange(MemberDTO memberDTO);
}
