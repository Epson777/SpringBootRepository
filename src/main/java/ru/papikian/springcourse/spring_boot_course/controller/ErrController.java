package ru.papikian.springcourse.spring_boot_course.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("errorMessage", "Ошибка 403: Доступ запрещен");
            } else if(statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorMessage", "Ошибка 404: Страница не найдена");
            } else {
                model.addAttribute("errorMessage", "Ошибка " + statusCode);
            }
        }
        return "error";
    }
}