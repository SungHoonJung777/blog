package org.fullstack4.springboot.repository;

import jakarta.transaction.Transactional;
import org.fullstack4.springboot.domain.BoardEntity;
import org.fullstack4.springboot.domain.CommonEntity;
import org.fullstack4.springboot.domain.MemberEntity;
import org.fullstack4.springboot.dto.CommonDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonRepository extends JpaRepository<CommonEntity, Integer> {
    @Query(value ="SELECT * FROM blog_common where board_idx = ?1" , nativeQuery = true)
    List<CommonEntity> commonDetail(int board_idx);
    @Transactional
    @Modifying
    @Query(value ="delete from blog_common where board_idx = ?1 and common_member_id = ?2" , nativeQuery = true)
    void commonDelete(int board_idx , String common_member_id);
}
