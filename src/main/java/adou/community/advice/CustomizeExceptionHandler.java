package adou.community.advice;

import adou.community.dto.ResultDTO;
import adou.community.exception.CustomizeErrorCode;
import adou.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    Object handle(Throwable e, Model model, HttpServletRequest request) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            //返回json
            if (e instanceof CustomizeException) {
                return ResultDTO.errorOf((CustomizeException) e);
            } else {
                return ResultDTO.errorOf(CustomizeErrorCode.SYSTEM_ERROR);
            }
        } else {
            //跳转页面
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYSTEM_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }

}
