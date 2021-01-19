package adou.community.controller;

import adou.community.dto.CommentCreateDTO;
import adou.community.dto.CommentDTO;
import adou.community.dto.QuestionDTO;
import adou.community.service.CommentService;
import adou.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class QuestionController {

    @Resource
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id, Model model){

        QuestionDTO questionDTO = questionService.getById(id);
        List<CommentDTO> comments =  commentService.listByQuestionId(id);
        questionService.incView(id);//浏览数功能
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        return "question";
    }

}
