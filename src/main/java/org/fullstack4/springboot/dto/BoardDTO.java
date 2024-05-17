package org.fullstack4.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fullstack4.springboot.domain.BoardEntity;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
    private int boardIdx;
    private String member_id;
    private String boardTitle;
    private String boardContent;
    private int board_like;
    private String board_screen;
    private String board_category;
    private String board_hash;
    private LocalDateTime board_start_date;
    private LocalDateTime board_end_date;
    private String board_image;
    private LocalDateTime board_reg_date;
    private LocalDateTime board_modify_date;

    public BoardDTO(BoardEntity entity) {
        this.boardIdx = entity.getBoardIdx();
        this.member_id = entity.getMember_id();
        this.boardTitle = entity.getBoardTitle();
        this.boardContent = entity.getBoardContent();
        this.board_like = entity.getBoard_like();
        this.board_screen = entity.getBoard_screen();
        this.board_category = entity.getBoard_category();
        this.board_hash = entity.getBoard_hash();
        this.board_start_date = entity.getBoard_start_date();
        this.board_end_date = entity.getBoard_end_date();
        this.board_image = entity.getBoard_image();
        this.board_reg_date = entity.getBoard_reg_date();
        this.board_modify_date = entity.getBoardModifyDate();
    }
}
