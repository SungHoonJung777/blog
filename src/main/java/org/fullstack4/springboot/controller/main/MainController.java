package org.fullstack4.springboot.controller.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.dto.BoardDTO;
import org.fullstack4.springboot.service.Board.BoardService;
import org.fullstack4.springboot.service.Member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String boardGET(HttpServletRequest  request, Model model) throws Exception{
        log.info("==============================");
        log.info("mainController >> boardGET()");
        log.info("==============================");
        HttpSession session = request.getSession();
        String member_id = (String) session.getAttribute("member_id");

        List<BoardDTO> list = boardService.getList(member_id);
        log.info("===========list==============" + list);
        model.addAttribute("list",list);
        return "/main/board";
    }

}
