package adou.community.controller;

import adou.community.dto.PageDTO;
import adou.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @Resource
    private QuestionService questionService;

    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        @RequestParam(name = "search",required = false)String search) {
        PageDTO pageDTO = questionService.list(search,currentPage, size);
        model.addAttribute("pageDTO", pageDTO);
        model.addAttribute("search",search);
        return "index";
    }
}
