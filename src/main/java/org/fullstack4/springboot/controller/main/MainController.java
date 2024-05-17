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
    public String boardGET(@RequestParam(value = "keyword", required = false) String keyword, @PageableDefault(page = 1) Pageable pageable , HttpServletRequest  request, Model model) throws Exception{
        log.info("==============================");
        log.info("mainController >> boardGET()");
        log.info("==============================");
        HttpSession session = request.getSession();
        String member_id = (String) session.getAttribute("member_id");

        Page<BoardDTO> postsPages = boardService.paging(keyword,pageable);
        int blockLimit = 3;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), postsPages.getTotalPages());

        System.out.println(" postsPages.getTotalPages() " +  postsPages.getTotalPages());
        System.out.println("endPageL " + endPage);
        System.out.println("postsPages " + postsPages);
        model.addAttribute("postsPages", postsPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("keyword", keyword);
/*        List<BoardDTO> list = boardService.getList(member_id);*/
   /*     log.info("===========list==============" + list);
        model.addAttribute("list",list);*/
        return "/main/board";
    }

}
