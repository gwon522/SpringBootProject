package com.jingwon.SpringBootProject.controller;

import com.jingwon.SpringBootProject.model.Board;
import com.jingwon.SpringBootProject.repository.BoardRepository;
import com.jingwon.SpringBootProject.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards",boards);
        return "board/list";
    }

    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("board", new Board());
        return "board/write";
    }
    @PostMapping("/write")
    public String writeSave(@Valid Board board, BindingResult bindingResult){
        boardValidator.validate(board,bindingResult);

        if (bindingResult.hasErrors()){
            return "board/write";
        }
        board.setWriter("nesaz");
        boardRepository.save(board);
        return "redirect:/board/list";
    }
}
