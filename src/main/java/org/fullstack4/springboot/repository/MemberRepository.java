package org.fullstack4.springboot.repository;

import jakarta.transaction.Transactional;
import org.fullstack4.springboot.domain.MemberEntity;
import org.fullstack4.springboot.dto.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
   @Query(value = "SELECT * FROM blog_member WHERE member_id = ?1 AND member_pwd = ?2", nativeQuery = true)
   public MemberEntity login_info(String member_id, String member_pwd);
   @Query(value = "SELECT count(*) FROM blog_member WHERE member_email = ?1 ", nativeQuery = true)
   public int pwdFind (String member_email);
   @Transactional
   @Modifying
   @Query(value = "UPDATE blog_member SET member_pwd = ?1 WHERE member_email = ?2", nativeQuery = true)
   public int pwdChange(String member_pwd, String member_email);
   @Transactional
   @Modifying
   @Query(value = "UPDATE blog_member SET member_pwd = :#{#memberDTO.member_pwd}, member_email = :#{#memberDTO.member_email}, member_phone = :#{#memberDTO.member_phone} WHERE member_id = :#{#memberDTO.member_id}", nativeQuery = true)
   public int infoChange(MemberDTO memberDTO);



}
