package org.fullstack4.springboot.service.Board;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.Criteria.Criteria;
import org.fullstack4.springboot.domain.BoardEntity;
import org.fullstack4.springboot.dto.BoardDTO;
import org.fullstack4.springboot.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        int iResult = boardRepository.save(boardEntity).getBoardIdx();

        return iResult;
    }

    @Override
    public BoardDTO boardDetail(int boardIdx) {
        BoardEntity boardEntity = boardRepository.boardDetail(boardIdx);
        BoardDTO boardDTO = modelMapper.map(boardEntity, BoardDTO.class);
        return boardDTO;
    }

    @Override
    public int updateBoard(BoardDTO boardDTO) {
        BoardEntity board = modelMapper.map(boardDTO, BoardEntity.class);
        int uResult = boardRepository.save(board).getBoardIdx();
        return uResult;
    }

    @Override
    public int deleteBoard(int boardIdx) {
        int dResult = boardRepository.deleteBoard(boardIdx);
        return dResult;
    }



/*    @Override
    public int getTotalCount(String member_id,Criteria cri) {
        int count = boardRepository.getTotalCount(member_id, cri);
        return count;
    }*/

    public Page<BoardDTO> paging(String keyword, Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 10; // 한페이지에 보여줄 글 개수

        Page<BoardEntity> searchResult;

        if (keyword != null && !keyword.isEmpty()) {
            // 검색어가 제공된 경우, 해당 검색어를 제목(title) 또는 내용(content)에서 찾는다고 가정
            searchResult = boardRepository.findByBoardTitleContainingOrBoardContentContaining(keyword, keyword, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardIdx")));
        } else {
            // 검색어가 제공되지 않은 경우, 모든 게시물을 반환
            searchResult = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardIdx")));
        }

        // 검색 결과를 DTO로 변환하여 반환
        return searchResult.map(BoardDTO::new);
    }

/*    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 10; // 한페이지에 보여줄 글 개수

        // 한 페이지당 3개식 글을 보여주고 정렬 기준은 ID기준으로 내림차순
        Page<BoardEntity> postsPages = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardIdx")));

        // 목록 : id, title, content, author
        Page<BoardDTO> postsResponseDtos = postsPages.map(
                postPage -> new BoardDTO(postPage));

        return postsResponseDtos;
    }*/
}
