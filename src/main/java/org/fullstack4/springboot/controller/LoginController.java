package org.fullstack4.springboot.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.dto.MemberDTO;
import org.fullstack4.springboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private MemberService memberService;
    @GetMapping("/login")
    public String loginGET(HttpServletRequest request, MemberDTO memberDTO, Model model) {
        log.info("==============================");
        log.info("MemberLoginController >> loginGET()");

        String auto_user_id = "";



        model.addAttribute("acc_url", request.getHeader("referer"));

        log.info("==============================");

        return "/login/login";
    }
    @PostMapping("/login")
    public String loginPOST(Model model , HttpServletRequest request , HttpServletResponse response , String member_id, String password, RedirectAttributes redirectAttributes) {
        log.info("==============================");
        log.info("LoginController >> loginPOST()");
        log.info("memberId " + member_id);
        log.info("password " + password);
        log.info("==============================");
        MemberDTO loginMemberDTO = memberService.login_info(member_id ,password);
        log.info("loginMemberDTO : " + loginMemberDTO);



        if (loginMemberDTO != null) {
            HttpSession session = request.getSession(); // ()랑 (false)의 차이는? 로그아웃할 때 false 넣고 invalid 하면 깔끔하게 날라감.
            //() : 만약 세션이 형성이 안되어있으면 세션을 바로 생성해서 리턴. 있으면 있는 생성정보를 리턴.
            //(false) : 없더라도 생성안함. 그냥 세션만 리턴(null)
            session.setAttribute("member_id", loginMemberDTO.getMember_id());
            session.setAttribute("member_type",loginMemberDTO.getMember_type());
            session.setAttribute("loginInfo", loginMemberDTO);
            log.info(session.getAttribute("loginInfo"));
            model.addAttribute("loginInfo", loginMemberDTO);

            return "redirect:/";
        }
        else {

            redirectAttributes.addFlashAttribute("error_login", "사용자 정보가 일치하지 않습니다.");
            return "redirect:/login/login";
        }

    }

}
