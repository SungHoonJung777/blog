package org.fullstack4.springboot.repository;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.domain.BaseEntity;
import org.fullstack4.springboot.domain.BoardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testGetNow(){
        String now = boardRepository.getNow();
        log.info("--------------------");
        log.info(now);
        log.info("--------------------");
    }

    @Test
    public void testRegist(){
        IntStream.rangeClosed(0,10).forEach(i-> {
            BoardEntity bbs = BoardEntity.builder().title("test제목"+i).content("content제목"+i).user_id("test")
                    .display_date(new SimpleDateFormat("yyyy-MM-dd")
                            .format(new Date(2024-1900,5,(i%10==0?1:i%10)))
                            .toString()).build();
            BoardEntity bbsResult = boardRepository.save(bbs);
        });
    }


    @Test
    public void testView(){
        int idx = 1;
        Optional<BoardEntity> result = boardRepository.findById(idx);
        BoardEntity bbs = result.orElse(BoardEntity.builder().idx(idx).user_id("test").title("제목 수정1").content("내용 수정 1").build());


    }

    
}
