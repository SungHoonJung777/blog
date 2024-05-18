package org.fullstack4.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonDTO {

    private int common_idx;
    private int board_idx;
    private String member_id;
    private String common_member_id;
}
