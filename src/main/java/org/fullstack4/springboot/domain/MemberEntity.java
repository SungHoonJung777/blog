package org.fullstack4.springboot.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="blog_member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true , nullable = true)
    private int member_idx;
    @Column(length = 20, nullable = false)
    private String member_id;
    @Column(length = 20, nullable = false)
    private String member_pwd;
    @Column(length = 20, nullable = false)
    private String member_name;
    @Column(length = 20, nullable = false)
    private String member_email;
    @Column(length = 20, nullable = false)
    private String member_phone;
    @Column(length = 20, nullable = false)
    private LocalDateTime member_reg_date;
    @Column(length = 20, nullable = false)
    private String member_type;
}
