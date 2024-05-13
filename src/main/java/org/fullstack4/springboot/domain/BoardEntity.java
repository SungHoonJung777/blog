package org.fullstack4.springboot.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="tbl_board")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true , nullable = true)
    private int idx;
    @Column(length = 20, nullable = false)
    private String user_id;
    @Column(length = 1000,nullable = false)
    private String title;
    @Column(length = 2000,nullable = false)
    private String content;
    @Column(length = 10,nullable = true)
    private String display_date;
  /*  private Date reg_date;
    private Date modify_date;*/


}
