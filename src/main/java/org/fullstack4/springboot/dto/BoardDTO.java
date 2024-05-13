package org.fullstack4.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
    private int idx;
    private String user_id;
    private String title;
    private String content;
    private String display_date;
    private LocalDateTime reg_date;
    private LocalDateTime modify_date;
}
