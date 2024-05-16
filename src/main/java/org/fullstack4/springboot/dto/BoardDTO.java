package org.fullstack4.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
    private int board_idx;
    private String member_id;
    private String board_title;
    private String board_content;
    private int board_like;
    private String board_screen;
    private String board_category;
    private String board_hash;
    private LocalDateTime board_start_date;
    private LocalDateTime board_end_date;
    private String board_image;
    private LocalDateTime board_reg_date;
    private LocalDateTime board_modify_date;
}
