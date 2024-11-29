package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @GetMapping("jump")
    public String jumpJsp(Model model){
        System.out.println("FileController.jumpJsp");
        model.addAttribute("msg","request data!!");
        return "home";
    }
    @RequestMapping("/redirect-demo")
    public String redirectDemo() {
        // 重定向到 /demo 路径
        return "redirect:/demo";
    }

    @RequestMapping("/forward-demo")
    public String forwardDemo() {
        // 转发到 /demo 路径
        return "forward:/demo";
    }
    @RequestMapping("/redirect/baidu")
    public String forwardBaidu() {
        // 转发到 外部地址
        return "redirect:http://www.baidu.com";
    }
}
