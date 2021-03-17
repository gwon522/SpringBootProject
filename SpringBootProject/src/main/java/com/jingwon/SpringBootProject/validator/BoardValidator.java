package com.jingwon.SpringBootProject.validator;

import com.jingwon.SpringBootProject.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }
    @Override
    public void validate(Object o, Errors e) {
        Board board = (Board) o;
        if (StringUtils.isEmpty(board.getContent())){
            e.rejectValue("content","key","내용을 입력해주세요.");
        }
    }
}
