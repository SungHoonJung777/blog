package org.fullstack4.springboot.service.Board;

import org.fullstack4.springboot.Criteria.Criteria;
import org.fullstack4.springboot.dto.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getList(String member_id);
    int registBoard(BoardDTO boardDTO);

    BoardDTO boardDetail(int boardIdx);

    int updateBoard(BoardDTO boardDTO);

    int deleteBoard(int boardIdx);

/*    int getTotalCount(String member_id, Criteria cri);*/

    Page<BoardDTO> paging( String keyword, Pageable pageable);


}
