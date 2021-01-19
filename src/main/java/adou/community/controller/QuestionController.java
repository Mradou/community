package adou.community.controller;

import adou.community.dto.QuestionDTO;
import adou.community.mapper.QuestionMapper;
import adou.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

@Controller
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        questionService.incView(id);//浏览数功能
        model.addAttribute("question",questionDTO);
        return "question";
    }

}
