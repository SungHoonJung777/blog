package org.fullstack4.springboot.service;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.domain.MemberEntity;
import org.fullstack4.springboot.dto.MemberDTO;
import org.fullstack4.springboot.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@Log4j2
public class MemberServiceImpl implements MemberService{


    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private  ModelMapper modelMapper;

    @Override
    public MemberDTO login_info(String member_id, String member_pwd) {
        MemberEntity memberEntity = memberRepository.login_info(member_id,member_pwd);
        MemberDTO dto = null;
        if(memberEntity != null) {
            dto = modelMapper.map(memberEntity, MemberDTO.class);
        }

        return dto;
    }


}
