package org.fullstack4.springboot.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="blog_board")
public class BoardEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = true)
    private int board_idx;

    @Column(length = 120, nullable = false)
    private String member_id;

    @Column(length = 120, nullable = false)
    private String board_title;

    @Column(length = 120, nullable = false)
    private String board_content;

    @Column(nullable = false)
    private int board_like;

    @Column(length = 120,nullable = false)
    private String board_screen;

    @Column(length = 120, nullable = false)
    private String board_category;

    @Column(length = 120, nullable = false)
    private String board_hash;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime board_start_date;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime board_end_date;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime board_reg_date;

    @Column(nullable = false , insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime board_modify_date;

    @Column(length = 120, nullable = false)
    private String board_image;
    @PrePersist
    public void prePersist() {
        this.board_reg_date = LocalDateTime.now();
    }
}
