package org.fullstack4.springboot.repository;

import org.fullstack4.springboot.domain.BoardEntity;
import org.fullstack4.springboot.domain.CommonEntity;
import org.fullstack4.springboot.domain.MemberEntity;
import org.fullstack4.springboot.dto.CommonDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonRepository extends JpaRepository<CommonEntity, Integer> {
    @Query(value ="SELECT * FROM blog_common where board_idx = ?1" , nativeQuery = true)
    List<CommonEntity> commonDetail(int board_idx);

}
