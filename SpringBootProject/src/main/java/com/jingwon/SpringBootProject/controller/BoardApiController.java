package com.jingwon.SpringBootProject.controller;

import com.jingwon.SpringBootProject.model.Board;
import com.jingwon.SpringBootProject.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardApiController {

    @Autowired
    private BoardRepository repository;

//    BoardApiController(BoardRepository repository) {
//        this.repository = repository;
//    }

    @GetMapping("/board")
    List<Board> all() {
        return repository.findAll();
    }

    @PostMapping("/board")
    Board board(@RequestBody Board board) {
        return repository.save(board);
    }

    @GetMapping("/board/{id}")
    Board one(@PathVariable Long id) {
        return repository.findByid(id);
    }

    @PutMapping("/board/{id}")
    Board replaceEmployee(@RequestBody Board newBoard, @PathVariable Long id) {

        return repository.findById(id)
                .map(board -> {
                    board.setTitle(newBoard.getTitle());
                    board.setContent(newBoard.getContent());
                    return repository.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return repository.save(newBoard);
                });
    }

    @DeleteMapping("/Board/{id}")
    void deleteBoard(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
