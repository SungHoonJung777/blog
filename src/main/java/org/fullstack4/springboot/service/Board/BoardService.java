package org.fullstack4.springboot.service.Board;

import org.fullstack4.springboot.Criteria.Criteria;
import org.fullstack4.springboot.dto.BoardDTO;
import org.fullstack4.springboot.dto.CommonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardService {
    List<BoardDTO> getList(String member_id);
    int registBoard(BoardDTO boardDTO);

    BoardDTO boardDetail(int boardIdx);
    List<CommonDTO>  commonDetail(int boardIdx);
    int updateBoard(BoardDTO boardDTO);

    int deleteBoard(int boardIdx);

/*    int getTotalCount(String member_id, Criteria cri);*/

    Page<BoardDTO> paging(LocalDateTime boardStartDate, LocalDateTime boardEndDate, String keyword, Pageable pageable ,
                          String searchType);
    Page<BoardDTO> pagingLike(LocalDateTime boardStartDate, LocalDateTime boardEndDate, String keyword, Pageable pageable ,
                          String searchType);

    void commonDelete(CommonDTO commonDTO);

    List<BoardDTO> getListMy(String member_id);
    List<BoardDTO> getListYou(String member_id);

}
