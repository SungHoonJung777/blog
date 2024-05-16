package org.fullstack4.springboot.service.Board;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.domain.BoardEntity;
import org.fullstack4.springboot.dto.BoardDTO;
import org.fullstack4.springboot.repository.BoardRepository;
import org.fullstack4.springboot.service.Board.BoardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<BoardDTO> getList(String member_id) {
        List<BoardDTO> boardEntities = boardRepository.getList(member_id).stream().map(vo->modelMapper.map(vo,BoardDTO.class)).collect(Collectors.toList());

        return boardEntities;
    }

    @Override
    public int registBoard(BoardDTO boardDTO) {
        BoardEntity boardEntity = modelMapper.map(boardDTO, BoardEntity.class);
        int iResult = boardRepository.save(boardEntity).getBoard_idx();

        return iResult;
    }

    @Override
    public BoardDTO boardDetail(int board_idx) {
        BoardEntity boardEntity = boardRepository.boardDetail(board_idx);
        BoardDTO boardDTO = modelMapper.map(boardEntity, BoardDTO.class);
        return boardDTO;
    }
}
