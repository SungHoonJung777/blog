package org.fullstack4.springboot.controller.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.Criteria.Criteria;
import org.fullstack4.springboot.dto.BoardDTO;
import org.fullstack4.springboot.service.Board.BoardService;
import org.fullstack4.springboot.service.Member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/main")
public class MainController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardService boardService;

    @GetMapping("/main")
    public String main(){
        log.info("==============================");
        log.info("mainController >> main()");
        log.info("==============================");

        return "/main/main";
    }

    @GetMapping("/board")
    public String boardGET(@RequestParam(value = "boardStartDate", required = false) String boardStartDate,
                           @RequestParam(value = "boardEndDate", required = false) String boardEndDate,
                           @RequestParam(value = "keyword", required = false) String keyword,
                           @RequestParam(value = "searchType", required = false) String searchType,
                           @RequestParam(value = "regDate", required = false) String regDate,
                           @RequestParam(value = "like", required = false) String like,
                           @PageableDefault(page = 1) Pageable pageable,
                           HttpServletRequest request,
                           Model model) throws Exception {
        log.info("==============================");
        log.info("mainController >> boardGET()");
        log.info("==============================");
        HttpSession session = request.getSession();
        String member_id = (String) session.getAttribute("member_id");
        log.info("=====regDate====" + regDate);
        log.info("=====like====" + like);
        Page<BoardDTO> postsPages;
        if(regDate != "" && like == ""){
            if (boardStartDate != null && !boardStartDate.isEmpty() && boardEndDate != null && !boardEndDate.isEmpty()) {
                LocalDateTime startDateTime = LocalDate.parse(boardStartDate, DateTimeFormatter.ISO_DATE).atStartOfDay();
                LocalDateTime endDateTime = LocalDate.parse(boardEndDate, DateTimeFormatter.ISO_DATE).atStartOfDay();
                postsPages = boardService.paging(startDateTime, endDateTime, keyword, pageable , searchType);
            } else {
                // 기간이 설정되지 않은 경우, 전체 게시물을 조회
                postsPages = boardService.paging(null, null, keyword, pageable , searchType);
            }
        } else if( regDate == "" && like != ""){
            System.out.println("like 들어옴");
            if (boardStartDate != null && !boardStartDate.isEmpty() && boardEndDate != null && !boardEndDate.isEmpty()) {
                LocalDateTime startDateTime = LocalDate.parse(boardStartDate, DateTimeFormatter.ISO_DATE).atStartOfDay();
                LocalDateTime endDateTime = LocalDate.parse(boardEndDate, DateTimeFormatter.ISO_DATE).atStartOfDay();
                postsPages = boardService.pagingLike(startDateTime, endDateTime, keyword, pageable , searchType);
            } else {
                // 기간이 설정되지 않은 경우, 전체 게시물을 조회
                postsPages = boardService.pagingLike(null, null, keyword, pageable , searchType);
            }
        } else {
            if (boardStartDate != null && !boardStartDate.isEmpty() && boardEndDate != null && !boardEndDate.isEmpty()) {
                LocalDateTime startDateTime = LocalDate.parse(boardStartDate, DateTimeFormatter.ISO_DATE).atStartOfDay();
                LocalDateTime endDateTime = LocalDate.parse(boardEndDate, DateTimeFormatter.ISO_DATE).atStartOfDay();
                postsPages = boardService.paging(startDateTime, endDateTime, keyword, pageable , searchType);
            } else {
                // 기간이 설정되지 않은 경우, 전체 게시물을 조회
                postsPages = boardService.paging(null, null, keyword, pageable , searchType);
            }
        }


        int blockLimit = 3;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), postsPages.getTotalPages());

        System.out.println(" postsPages.getTotalPages() " + postsPages.getTotalPages());
        System.out.println("endPageL " + endPage);
        System.out.println("postsPages " + postsPages);
        model.addAttribute("postsPages", postsPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("boardStartDate", boardStartDate);
        model.addAttribute("boardEndDate", boardEndDate);
        model.addAttribute("searchType", searchType);
        model.addAttribute("regDate", regDate);
        model.addAttribute("like", like);
        model.addAttribute("keyword", keyword);
        return "/main/board";
    }




}
