package org.fullstack4.springboot.repository;

import org.fullstack4.springboot.domain.MemberEntity;
import org.fullstack4.springboot.dto.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
   @Query(value = "SELECT * FROM blog_member WHERE member_id = ?1 AND member_pwd = ?2", nativeQuery = true)
   public MemberEntity login_info(String member_id, String member_pwd);
   @Query(value = "SELECT count(*) FROM blog_member WHERE member_id = ?1 ", nativeQuery = true)
   public int pwdFind (String member_id);
}
