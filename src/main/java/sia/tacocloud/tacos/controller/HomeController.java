package sia.tacocloud.tacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.tacos.entity.User;
import sia.tacocloud.tacos.service.UserService;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
//@CrossOrigin
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<User> list(HttpServletResponse resp, HttpServletRequest req, Model model){
        return userService.list();
    }

//    @RequestMapping("/list")
//    public String list(HttpServletResponse resp, HttpServletRequest req, Model model){
//        model.addAttribute("users",userService.list());
//        return "home";
//    }
}
