package com.future.iot.controller.web;

import com.future.iot.model.Employee;
import com.future.iot.model.Posts;
import com.future.iot.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PropertySource(value = "classpath:/common.properties", encoding = "utf-8")
public class AdminController {
    @Autowired
    private Environment env;

    @Autowired
    private EmployeeRepository empRepo;

    @GetMapping("/posts")
    public ModelAndView posts(Principal principal){
        Employee employee = empRepo.getOne(principal.getName());
        ModelAndView mv = new ModelAndView("/admin/posts.manage");
        mv.addObject("employee", employee);
        mv.addObject("posts", new Posts());
        mv.addObject("themes", env.getProperty("posts.themes", String[].class));
        return mv;
    }
}
