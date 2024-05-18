package org.fullstack4.springboot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "blog_board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = true)
    private int boardIdx;

    @Column(length = 120, nullable = false)
    private String member_id;

    @Column(length = 120, nullable = false)
    private String boardTitle;

    @Column(length = 120, nullable = false)
    private String boardContent;

    @Column(nullable = false)
    private int boardLike;

    @Column(length = 120, nullable = false)
    private String board_screen;

    @Column(length = 120, nullable = false)
    private String board_category;

    @Column(length = 120, nullable = false)
    private String board_hash;

    @Column(nullable = false)
    private LocalDateTime boardStartDate;

    @Column(nullable = false)
    private LocalDateTime boardEndDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime boardRegDate;

    @Column(nullable = false)
    private LocalDateTime boardModifyDate;

    @Column(length = 120, nullable = false)
    private String board_image;

    @PrePersist
    public void prePersist() {
        this.boardRegDate = LocalDateTime.now();
        this.boardModifyDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.boardModifyDate = LocalDateTime.now();
    }
}
