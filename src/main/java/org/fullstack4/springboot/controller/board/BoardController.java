package org.fullstack4.springboot.controller.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.dto.BoardDTO;
import org.fullstack4.springboot.service.Board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Http2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j2
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/regist")
    public String regsitGET()throws Exception{
        log.info("==============================");
        log.info("BoardController >> regsitGET()");
        log.info("==============================");

        return "/board/regist";
    }
    @PostMapping("/regist")
    public String regsitPOST(String board_title,String board_content, String board_screen,String board_start_date, String board_end_date,String board_category,
            String board_hash, @RequestParam("file") MultipartFile file , HttpServletRequest request)throws Exception{
        log.info("==============================");
        log.info("BoardController >> regsitPOST()");
        log.info("###"+file.getOriginalFilename());
        log.info("###board_title###"+board_title);
        log.info("###board_content###"+board_content);
        log.info("###board_screen###"+board_screen);
        log.info("###board_start_date###"+board_start_date);
        log.info("###board_end_date###"+board_end_date);
        log.info("###board_category###"+board_category);
        log.info("###board_hash###"+board_hash);
        log.info("###file###"+file);
        log.info("==============================");
        HttpSession session = request.getSession();

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setMember_id((String) session.getAttribute("member_id"));
        boardDTO.setBoard_title(board_title);
        boardDTO.setBoard_content(board_content);
        boardDTO.setBoard_screen(board_screen);
        boardDTO.setBoard_category(board_category);
        boardDTO.setBoard_hash(board_hash);

        LocalDateTime startDateTime = LocalDate.parse(board_start_date, DateTimeFormatter.ISO_DATE).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(board_end_date, DateTimeFormatter.ISO_DATE).atStartOfDay();


        boardDTO.setBoard_start_date(startDateTime);
        boardDTO.setBoard_end_date(endDateTime);


        log.info("###boardDTO###"+boardDTO);
        String uploadFolder = "C:\\java4\\springboot\\blog\\src\\main\\resources\\static\\img";
        String fileRealName = file.getOriginalFilename(); //원래 파일의 이름
        long size = file.getSize();
        String fileExt = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length()); // 확장자명
        //엑셀.파.일xxx.xls --> 제일 마지막 인덱스의 . 에서부터 파일이름 끝에를 파싱

        log.info("============================");
        log.info("uploadFolder : " + uploadFolder);
        log.info("fileRealName : " + fileRealName);
        log.info("size : " + size);
        log.info("fileExt : " + fileExt);


        //새로운 파일명 생성
        UUID uuid = UUID.randomUUID();
        String[] uuids = uuid.toString().split("-");
        String newName = uuids[0];

        log.info("uuid : " + uuid);
        log.info("uuids : " + uuids);
        log.info("newName : " + newName);


        File saveFile = new File(uploadFolder + "\\" + newName + fileExt);

        try {


            file.transferTo(saveFile);
            File thunsailFile = new File(uploadFolder + "\\" + newName + fileExt);

            BufferedImage bo_image = ImageIO.read(saveFile);
            double ratio = 3;
            int width = (int) (bo_image.getWidth() / ratio);
            int height = (int) (bo_image.getHeight() / ratio);


            BufferedImage bt_image = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D graphics2D = bt_image.createGraphics();
            graphics2D.drawImage(bo_image,0,0,width,height,null);

            ImageIO.write(bt_image,fileExt,thunsailFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        boardDTO.setBoard_image(newName+fileExt);

        int insertResult = boardService.registBoard(boardDTO);
        if(insertResult > 0){
            return "redirect:/main/board";
        } else {
            return "redirect:/main/board";
        }

    }
    @GetMapping("/boardDetail")
    public String boardDetailGET(String board_idx, Model model)throws Exception{
        log.info("==============================");
        log.info("BoardController >> boardDetailGET()");
        log.info("==============================");
        int idx = Integer.parseInt(board_idx);
        BoardDTO boardDTO = boardService.boardDetail(idx);
        log.info("=========boardDTO=========" + boardDTO);
        model.addAttribute("list", boardDTO);
        return "/board/detail";
    }

}
