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
    private int boardLike;
    private String board_screen;
    private String board_category;
    private String board_hash;
    private LocalDateTime boardStartDate;
    private LocalDateTime boardEndDate;
    private String board_image;
    private LocalDateTime boardRegDate;
    private LocalDateTime board_modify_date;

    public BoardDTO(BoardEntity entity) {
        this.boardIdx = entity.getBoardIdx();
        this.member_id = entity.getMember_id();
        this.boardTitle = entity.getBoardTitle();
        this.boardContent = entity.getBoardContent();
        this.boardLike = entity.getBoardLike();
        this.board_screen = entity.getBoard_screen();
        this.board_category = entity.getBoard_category();
        this.board_hash = entity.getBoard_hash();
        this.boardStartDate = entity.getBoardStartDate();
        this.boardEndDate = entity.getBoardEndDate();
        this.board_image = entity.getBoard_image();
        this.boardRegDate = entity.getBoardRegDate();
        this.board_modify_date = entity.getBoardModifyDate();
    }
}
