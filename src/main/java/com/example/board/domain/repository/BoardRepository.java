package com.example.board.domain.repository;

import com.example.board.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    //최신순으로 정렬
    List<BoardEntity> findAllByOrderByCreatedDateDesc();
}
