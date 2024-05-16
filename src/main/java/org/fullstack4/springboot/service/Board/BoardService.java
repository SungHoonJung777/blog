package org.fullstack4.springboot.service.Board;

import org.fullstack4.springboot.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getList(String member_id);
    int registBoard(BoardDTO boardDTO);

    BoardDTO boardDetail(int board_idx);
}
