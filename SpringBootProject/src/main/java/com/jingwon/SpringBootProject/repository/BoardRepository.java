package com.jingwon.SpringBootProject.repository;

import com.jingwon.SpringBootProject.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByid(Long id);
    Page<Board> findByTitleContaining(String title, Pageable pageable);
}
