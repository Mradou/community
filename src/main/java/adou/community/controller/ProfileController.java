package adou.community.controller;

import adou.community.dto.PageDTO;
import adou.community.model.User;
import adou.community.service.NotificationService;
import adou.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Resource
    private QuestionService questionService;
    @Resource
    private NotificationService notificationService;


    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            //跳转到问题页面
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            PageDTO pageDTO = questionService.list(user.getId(), currentPage, size);
            model.addAttribute("pageDTO", pageDTO);
        } else if ("replies".equals(action)) {
            ////跳转到通知页面
            PageDTO pageDTO = notificationService.list(user.getId(), currentPage, size);
            model.addAttribute("pageDTO", pageDTO);
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }


        return "profile";
    }
}
