package org.fullstack4.springboot.service.Member;

import org.fullstack4.springboot.dto.CommonDTO;
import org.fullstack4.springboot.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    public MemberDTO login_info(String member_id, String member_pwd);
    public int pwdFind(String member_email);

    public int pwdChange(String member_pwd , String member_email);

    public int infoChange(MemberDTO memberDTO);

    public List<MemberDTO> getMemberList();

    public int insertCommon(CommonDTO commonDTO);

}
