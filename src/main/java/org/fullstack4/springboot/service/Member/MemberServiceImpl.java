package org.fullstack4.springboot.service.Member;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.domain.CommonEntity;
import org.fullstack4.springboot.domain.MemberEntity;
import org.fullstack4.springboot.dto.BoardDTO;
import org.fullstack4.springboot.dto.CommonDTO;
import org.fullstack4.springboot.dto.MemberDTO;
import org.fullstack4.springboot.repository.CommonRepository;
import org.fullstack4.springboot.repository.MemberRepository;
import org.fullstack4.springboot.service.Member.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class MemberServiceImpl implements MemberService {
    @Autowired
    private CommonRepository commonRepository;

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

    @Override
    public int pwdFind(String member_email) {
        int rResult = memberRepository.pwdFind(member_email);
        return rResult;
    }

    @Override
    public int pwdChange(String member_pwd , String member_email) {
        log.info("member Pwd:" + member_pwd);
        log.info("member_email:" + member_email);
        int uResult = memberRepository.pwdChange(member_pwd ,member_email);
        return uResult;
    }

    @Override
    public int infoChange(MemberDTO memberDTO) {
        log.info("##" + memberDTO);
        int uREsult = memberRepository.infoChange(memberDTO);
        return uREsult;
    }

    @Override
    public List<MemberDTO> getMemberList() {

        List<MemberDTO> memberDTOList = memberRepository.findAll().stream().map(vo->
                modelMapper.map(vo,MemberDTO.class)).collect(Collectors.toList());

        return memberDTOList;
    }

    @Override
    public int insertCommon(CommonDTO commonDTO) {
        CommonEntity commonEntity = modelMapper.map(commonDTO,CommonEntity.class);
        int insertCommon = commonRepository.save(commonEntity).getCommon_idx();
        return insertCommon;
    }

}
