package com.future.iot.controller.web;

import com.future.iot.dto.ChangePassWdDTO;
import com.future.iot.model.Employee;
import com.future.iot.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/acount")
public class AcountController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/changePassWd")
    public ModelAndView changePassWd(){
        ModelAndView mv = new ModelAndView("acount/changePassWd");
        Employee employee = new Employee();
        ChangePassWdDTO changePassWd = new ChangePassWdDTO();
        mv.addObject("changePassWd", changePassWd);
       return  mv;
    }

    @PostMapping("/changePassWd")
    public ModelAndView _changePassWd(@Valid @ModelAttribute("changePassWd") ChangePassWdDTO data, BindingResult result, Principal principal){
        ModelAndView mv = new ModelAndView("acount/changePassWd");
        if(result.hasErrors()){
            mv.addObject("changePassWd", data);
            return mv;
        }

        System.out.println(data);
        Employee employee = employeeRepository.getOne(principal.getName());
        String pass = employee.getHashPass();

        Boolean acc2 = new BCryptPasswordEncoder().matches("123452678", pass);

        System.out.println(acc2);

        if(pass.equals(employee._setPassword(data.getPassWdNew())) == true){
            System.out.println("ok");
        }


        employee.setPassword(data.getPassWdNew());

//        mv.addObject("changePassWd", changePassWd);
        return  mv;
    }
}
