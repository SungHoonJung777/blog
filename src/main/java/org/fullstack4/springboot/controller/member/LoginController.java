package org.fullstack4.springboot.controller.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.dto.MemberDTO;
import org.fullstack4.springboot.service.Member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Random;

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
            session.setAttribute("member_name", loginMemberDTO.getMember_name());
            session.setAttribute("member_type",loginMemberDTO.getMember_type());
            session.setAttribute("loginInfo", loginMemberDTO);
            log.info(session.getAttribute("loginInfo"));
            System.out.println("memberName" +session.getAttribute("member_name"));
            model.addAttribute("loginInfo", loginMemberDTO);

            return "redirect:/main/main";
        }
        else {

            redirectAttributes.addFlashAttribute("error_login", "사용자 정보가 일치하지 않습니다.");
            return "redirect:/login/login";
        }

    }
    @GetMapping("/pwdFind")
    public String pwdFile(){
        log.info("==============================");
        log.info("MemberLoginController >> pwdFindGET()");
        log.info("==============================");

        return "/login/pwdFind";
    }
    @PostMapping("/pwdFind")
    public String pwdFind(String member_id, String member_email, Model model,HttpServletRequest request) throws  Exception{
        log.info("==============================");
        log.info("MemberLoginController >> pwdFindPost()");
        log.info("==============================");
        String url = "/login/pwdFind";
        int result = memberService.pwdFind(member_email);
        log.info("result :" + result);
        if (result > 0) {


            Random random = new Random();
            int checkNum = random.nextInt(888888) + 111111;

  /*          int uResult = memberService.pwdChange(member_id ,checkNum);*/
/*            if (uResult > 0) {*/
                log.info("인증번호 " + checkNum);


                String setFrom = "ssfu777@gmail.com";
                String toMail = member_email;
                String title = "임시 비밀번호 발급 안내드립니다.";

                String content =
                        "블로그를 방문해주셔서 감사합니다." +
                        "<br><br>" +
                        "인증번호는 " + checkNum + "입니다." +
                        "<br>" +
                        "해당 인증번호를 입력해주세요. ";
            System.out.println(checkNum);
              /*  try {

                    MimeMessage message = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
                    helper.setFrom(setFrom);
                    helper.setTo(toMail);
                    helper.setSubject(title);
                    helper.setText(content, true);
                    helper.setText(content, true);

                    mailSender.send(message);


                } catch (Exception e) {
                    e.printStackTrace();
                }*/
      /*      }*/
            request.setAttribute("success_login", "이메일로 임시 비밀번호를 발급해드렸습니다.");

            return url;
        }
        else {
            request.setAttribute("error_login", "입력하신 정보와 맞는 정보가 없습니다.");

            return url;
        }
    }


    @GetMapping("/email")
    @ResponseBody
    public String emailGET(String email) throws Exception {

        log.info("============================");
        log.info("MemberController >> emailGET()");
        log.info(email);
        int result = memberService.pwdFind(email);


        if(result > 0) {
            Random random = new Random();
            int checkNum = random.nextInt(888888) + 111111;
            log.info("인증번호 " + checkNum);


            String setFrom = "ssfu777@gmail.com";
            String toMail = email;
            String title = "인증 이메일 입니다.";

            String content =
                    "블로그를 방문해주셔서 감사합니다." +
                            "<br><br>" +
                            "인증번호는 " + checkNum + "입니다." +
                            "<br>" +
                            "해당 인증번호를 입력해주세요. ";

       /* try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            helper.setText(content, true);

            mailSender.send(message);


        } catch (Exception e) {
            e.printStackTrace();
        }
*/
            String num = Integer.toString(checkNum);

            return num;
        } else {
            String num = Integer.toString(0);

            return num;
        }


    }


    @GetMapping("/pwdChange")
    public String pwdChangeGET(String member_email , Model model) throws  Exception{
        log.info("==============================");
        log.info("MemberLoginController >> pwdChangeGET()");
        log.info("==============================");
        log.info("==============================" + member_email);
        model.addAttribute("member_email" , member_email);
        return "/login/pwdChange";
    }

    @PostMapping("/pwdChange")
    public String pwdChangePOST(String member_pwd, HttpServletRequest request ,@RequestParam String member_email) throws  Exception{
        log.info("==============================");
        log.info("MemberLoginController >> pwdChangePOST()");
        log.info("==============================");


        log.info("##" + member_pwd );
        log.info("##" + member_email );
        int uResult = memberService.pwdChange(member_pwd , member_email);
        return "/login/login";
    }

    @PostMapping("/infoChange")
    public String infoChange(MemberDTO memberDTO, HttpServletRequest request , Model model) throws Exception {
        log.info("==============================");
        log.info("MemberLoginController >> infoChange()");
        log.info("====memberDTO=======" + memberDTO);
        log.info("==============================");
        int uResult = memberService.infoChange(memberDTO);

        if (memberDTO != null) {
            HttpSession session = request.getSession(); // ()랑 (false)의 차이는? 로그아웃할 때 false 넣고 invalid 하면 깔끔하게 날라감.
            //() : 만약 세션이 형성이 안되어있으면 세션을 바로 생성해서 리턴. 있으면 있는 생성정보를 리턴.
            //(false) : 없더라도 생성안함. 그냥 세션만 리턴(null)

            session.setAttribute("member_id", memberDTO.getMember_id());
            session.setAttribute("member_name", memberDTO.getMember_name());
            session.setAttribute("member_type",memberDTO.getMember_type());
            session.setAttribute("loginInfo", memberDTO);
            log.info(session.getAttribute("loginInfo"));
            System.out.println("memberName" +session.getAttribute("member_name"));
            model.addAttribute("loginInfo", memberDTO);

            return "redirect:/main/main";
        }
        return "/main/main";

    }

    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest req,
                         HttpServletResponse res) {
        log.info("==============================");

        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }



        log.info("LoginController >> logout()");
        log.info("==============================");

        return "redirect:/login/login";
    }
}
