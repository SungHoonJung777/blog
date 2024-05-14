package org.fullstack4.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
    private int member_idx;
    private String member_id;
    private String member_pwd;
    private String member_name;
    private String member_email;
    private String member_phone;
    private LocalDateTime member_reg_date;
    private String member_type;

}
