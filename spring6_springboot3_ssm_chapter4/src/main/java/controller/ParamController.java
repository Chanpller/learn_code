package controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("param")
public class ParamController {

    /**
     * 前端请求: http://localhost:8080/param/value?name=xx&age=18
     *
     * 可以利用形参列表,直接接收前端传递的param参数!
     *    要求: 参数名 = 形参名
     *          类型相同
     * 出现乱码正常，json接收具体解决！！
     * @return 返回前端数据
     */
    @GetMapping(value="/value")
    @ResponseBody
    public String setupForm(String name,int age){
        System.out.println("name = " + name + ", age = " + age);
        return name + age;
    }
    /**
    * 前端请求: http://localhost:8080/param/data?name=xx&stuAge=18
    *
    *  使用@RequestParam注解标记handler方法的形参
     *  指定形参对应的请求参数@RequestParam(请求参数名称)
     */
    @GetMapping(value="/data")
    @ResponseBody
    public Object paramForm(@RequestParam("name") String name,
                            @RequestParam("stuAge") int age){
        System.out.println("name = " + name + ", age = " + age);
        return name+age;
    }
     /**
             * 前端请求: http://localhost:8080/param/mul?hbs=吃&hbs=喝
            *
            *  一名多值,可以使用集合接收即可!但是需要使用@RequestParam注解指定
   */
    @GetMapping(value="/mul")
    @ResponseBody
    public Object mulForm(@RequestParam List<String> hbs){
        System.out.println("hbs = " + hbs);
        return Arrays.toString(hbs.toArray());
    }
    @GetMapping("/cookieValue")
    public void handle(@CookieValue("JSESSIONID") String cookie) {
        System.out.println("cookie = " + cookie);
    }
    @GetMapping("/save/cookieValue")
    public void save(HttpServletResponse response) {

        Cookie cookie = new Cookie("JSESSIONID","cookie");
        response.addCookie(cookie);
    }
    @GetMapping("/requestHeader")
    public void handle(
            @RequestHeader("Accept-Encoding") String encoding,
            @RequestHeader("Keep-Alive") long keepAlive) {
        System.out.println("encoding = " + encoding);
        System.out.println("keepAlive = " + keepAlive);
    }

    @RequestMapping("/attr/request/model")
    @ResponseBody
    public String testAttrRequestModel(// 在形参位置声明Model类型变量，用于存储模型数据
                                       Model model) {

        // 我们将数据存入模型，SpringMVC 会帮我们把模型数据存入请求域
        // 存入请求域这个动作也被称为暴露到请求域
        model.addAttribute("requestScopeMessageModel","i am very happy[model]");

        return "target";
    }

    @RequestMapping("/attr/request/model/map")
    @ResponseBody
    public String testAttrRequestModelMap(

            // 在形参位置声明ModelMap类型变量，用于存储模型数据
            ModelMap modelMap) {

        // 我们将数据存入模型，SpringMVC 会帮我们把模型数据存入请求域
        // 存入请求域这个动作也被称为暴露到请求域
        modelMap.addAttribute("requestScopeMessageModelMap","i am very happy[model map]");

        return "target";
    }
    @RequestMapping("/attr/request/map")
    @ResponseBody
    public String testAttrRequestMap(

            // 在形参位置声明Map类型变量，用于存储模型数据
            Map<String, Object> map) {

        // 我们将数据存入模型，SpringMVC 会帮我们把模型数据存入请求域
        // 存入请求域这个动作也被称为暴露到请求域
        map.put("requestScopeMessageMap", "i am very happy[map]");

        return "target";
    }
    @RequestMapping("/attr/request/original")
    @ResponseBody
    public String testAttrOriginalRequest(

            // 拿到原生对象，就可以调用原生方法执行各种操作
            HttpServletRequest request) {

        request.setAttribute("requestScopeMessageOriginal", "i am very happy[original]");

        return "target";
    }
    @RequestMapping("/attr/request/mav")
    public ModelAndView testAttrByModelAndView() {

        // 1.创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        // 2.存入模型数据
        modelAndView.addObject("requestScopeMessageMAV", "i am very happy[mav]");
        // 3.设置视图名称
        modelAndView.setViewName("target");

        return modelAndView;
    }

    @RequestMapping("/attr/session")
    @ResponseBody
    public String testAttrSession(HttpSession session) {
        //直接对session对象操作,即对会话范围操作!

        session.setAttribute("session","test");
        return "target";
    }

    @Autowired
    private ServletContext servletContext;

    @RequestMapping("/attr/application")
    @ResponseBody
    public String attrApplication() {

        servletContext.setAttribute("appScopeMsg", "i am hungry...");

        return "target";
    }
}
