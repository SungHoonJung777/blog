package org.fullstack4.springboot.repository;

import jakarta.transaction.Transactional;
import org.apache.ibatis.annotations.Param;
import org.fullstack4.springboot.Criteria.Criteria;
import org.fullstack4.springboot.domain.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository  extends JpaRepository<BoardEntity, Integer> {
    @Query(value ="SELECT * FROM blog_board where member_id = ?1 ORDER BY board_idx DESC" , nativeQuery = true)
    List<BoardEntity> getList(String member_id);


/*
    @Query(value = "insert into blog_board (board_title, board_like, board_content, board_category, board_hash, member_id, board_start_date, board_end_date , board_reg_date) " +
            "values (:#{#boardDTO.board_title}, :#{#boardDTO.board_like}, :#{#boardDTO.board_content}" +
            ", :#{#boardDTO.board_category}, :#{#boardDTO.board_hash}, :#{#boardDTO.member_id}, :#{#boardDTO.board_start_date}, :#{#boardDTO.board_end_date}, now())", nativeQuery = true)
    int registBoard( BoardDTO boardDTO);
*/
    @Query(value ="SELECT * FROM blog_board where board_idx = ?1" , nativeQuery = true)
    BoardEntity boardDetail(int board_idx);

    @Modifying
    @Transactional
    @Query(value = "delete from blog_board where board_idx = ?1", nativeQuery = true)
    int deleteBoard(int board_idx);

        // 제목 또는 내용에 특정 키워드가 포함된 게시물을 검색
        Page<BoardEntity> findByBoardTitleContainingOrBoardContentContaining(String boardTitle, String boardContent, Pageable pageable);


}
