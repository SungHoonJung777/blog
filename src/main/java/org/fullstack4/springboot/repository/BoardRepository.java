package org.fullstack4.springboot.repository;

import org.fullstack4.springboot.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    @Query(value = "select now()" , nativeQuery = true)
    public String getNow();

}
