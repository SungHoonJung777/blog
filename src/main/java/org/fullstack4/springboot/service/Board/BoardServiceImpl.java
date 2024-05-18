package org.fullstack4.springboot.service.Board;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.Criteria.Criteria;
import org.fullstack4.springboot.domain.BoardEntity;
import org.fullstack4.springboot.domain.CommonEntity;
import org.fullstack4.springboot.dto.BoardDTO;
import org.fullstack4.springboot.dto.CommonDTO;
import org.fullstack4.springboot.dto.MemberDTO;
import org.fullstack4.springboot.repository.BoardRepository;
import org.fullstack4.springboot.repository.CommonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CommonRepository commonRepository;
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

    public Page<BoardDTO> paging(LocalDateTime boardStartDate, LocalDateTime boardEndDate, String keyword, Pageable pageable
    ,String searchType) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 10; // 한페이지에 보여줄 글 개수

        Page<BoardEntity> searchResult;
        System.out.println("#boardStartDate" + boardStartDate);
        System.out.println("#boardEndDate" + boardEndDate);
        if (keyword != null && !keyword.isEmpty()) {
            if (boardStartDate != null && boardEndDate != null) {
                // 검색어와 시작일과 종료일이 모두 제공된 경우
                System.out.println("모두 들어옴 ");
                if(searchType == "title") {

                    searchResult = boardRepository.findByBoardTitleContainingAndBoardRegDateBetween(keyword,boardStartDate, boardEndDate, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardIdx")));
                } else {
                    searchResult = boardRepository.findByBoardContentContainingAndBoardRegDateBetween(keyword,boardStartDate, boardEndDate, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardIdx")));

                }
            } else {
                // 검색어는 제공되었지만 시작일 또는 종료일 중 하나 이상이 null인 경우
                searchResult = boardRepository.findByBoardTitleContainingOrBoardContentContaining(keyword, keyword, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardIdx")));
            }
        } else {
            if (boardStartDate != null && boardEndDate != null) {
                // 검색어가 제공되지 않았지만 시작일과 종료일이 모두 제공된 경우
                searchResult = boardRepository.findByBoardRegDateBetween(boardStartDate, boardEndDate, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardIdx")));
            } else {
                // 검색어와 시작일과 종료일 모두 제공되지 않은 경우, 모든 게시물을 반환
                searchResult = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardIdx")));
            }
        }

        // 검색 결과가 null인 경우 예외 처리
        if (searchResult == null) {
            // 예외 처리 방법에 따라 빈 페이지를 반환하거나 오류 메시지를 전달할 수 있음
            return Page.empty(); // 빈 페이지 반환
        }

        System.out.println(searchResult.map(BoardDTO::new)+"결과 반환");
        // 검색 결과를 DTO로 변환하여 반환
        return searchResult.map(BoardDTO::new);
    }
    public Page<BoardDTO> pagingLike(LocalDateTime boardStartDate, LocalDateTime boardEndDate, String keyword, Pageable pageable
            ,String searchType) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 10; // 한페이지에 보여줄 글 개수

        Page<BoardEntity> searchResult;
        System.out.println("#boardStartDate" + boardStartDate);
        System.out.println("#boardEndDate" + boardEndDate);
        if (keyword != null && !keyword.isEmpty()) {
            if (boardStartDate != null && boardEndDate != null) {
                // 검색어와 시작일과 종료일이 모두 제공된 경우
                System.out.println("모두 들어옴 ");
                if(searchType == "title") {

                    searchResult = boardRepository.findByBoardTitleContainingAndBoardRegDateBetween(keyword,boardStartDate, boardEndDate, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardLike")));
                } else {
                    searchResult = boardRepository.findByBoardContentContainingAndBoardRegDateBetween(keyword,boardStartDate, boardEndDate, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardLike")));

                }
            } else {
                // 검색어는 제공되었지만 시작일 또는 종료일 중 하나 이상이 null인 경우
                searchResult = boardRepository.findByBoardTitleContainingOrBoardContentContaining(keyword, keyword, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardLike")));
            }
        } else {
            if (boardStartDate != null && boardEndDate != null) {
                // 검색어가 제공되지 않았지만 시작일과 종료일이 모두 제공된 경우
                searchResult = boardRepository.findByBoardRegDateBetween(boardStartDate, boardEndDate, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardLike")));
            } else {
                // 검색어와 시작일과 종료일 모두 제공되지 않은 경우, 모든 게시물을 반환
                searchResult = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardLike")));
            }
        }

        // 검색 결과가 null인 경우 예외 처리
        if (searchResult == null) {
            // 예외 처리 방법에 따라 빈 페이지를 반환하거나 오류 메시지를 전달할 수 있음
            return Page.empty(); // 빈 페이지 반환
        }

        System.out.println(searchResult.map(BoardDTO::new)+"결과 반환");
        // 검색 결과를 DTO로 변환하여 반환
        return searchResult.map(BoardDTO::new);
    }

    @Override
    public List<CommonDTO>  commonDetail(int boardIdx) {
        List<CommonDTO> commonDTOList = commonRepository.commonDetail(boardIdx).stream().map(vo-> modelMapper.map(vo,CommonDTO.class)).collect(Collectors.toList());


        return commonDTOList;
    }

    @Override
    public void commonDelete(CommonDTO commonDTO) {
        int board_idx = commonDTO.getBoard_idx();
        String common_member_id = commonDTO.getCommon_member_id();

        commonRepository.commonDelete(board_idx, common_member_id);

    }

    @Override
    public List<BoardDTO> getListMy(String member_id) {
        List<BoardDTO> boardDTOList = boardRepository.getListMy(member_id).stream().map(vo-> modelMapper.map(vo,BoardDTO.class)).collect(Collectors.toList());

        return boardDTOList;
    }
    @Override
    public List<BoardDTO> getListYou(String member_id) {
        List<BoardDTO> boardDTOList = boardRepository.getListYou(member_id).stream().map(vo-> modelMapper.map(vo,BoardDTO.class)).collect(Collectors.toList());

        return boardDTOList;
    }

    @Override
    public List<BoardDTO> getListToday() {
        List<BoardDTO> boardEntities = boardRepository.getListToday().stream().map(vo->modelMapper.map(vo,BoardDTO.class)).collect(Collectors.toList());

        return boardEntities;
    }
}
