package com.jingwon.SpringBootProject.controller;

import com.jingwon.SpringBootProject.model.Board;
import com.jingwon.SpringBootProject.repository.BoardRepository;
import com.jingwon.SpringBootProject.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model,@PageableDefault(size = 5) Pageable pageable,@RequestParam(required = false, defaultValue = "") String searchText){
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContaining(searchText,pageable);
        int startPage = Math.max(1,boards.getPageable().getPageNumber()-4);
        int endPage = Math.min(boards.getTotalPages(),boards.getPageable().getPageNumber()+4);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
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
