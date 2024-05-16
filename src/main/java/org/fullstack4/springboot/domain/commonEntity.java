package org.fullstack4.springboot.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="blog_common")
public class commonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int common_idx;

    @Column(nullable = false)
    private int board_idx;


    @Column(length = 120, nullable = false)
    private String member_id;
}
