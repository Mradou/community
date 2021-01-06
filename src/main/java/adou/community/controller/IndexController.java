package adou.community.controller;

import adou.community.dto.PageDTO;
import adou.community.dto.QuestionDTO;
import adou.community.mapper.UserMapper;
import adou.community.model.User;
import adou.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @Resource
    private QuestionService questionService;

    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {


        PageDTO pageDTO = questionService.list(currentPage, size);
        model.addAttribute("pageDTO", pageDTO);
        return "index";
    }
}
