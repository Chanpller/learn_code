package com.chanpller.controller;

import com.chanpller.model.Headline;
import com.chanpller.service.HeadlineService;
import com.chanpller.util.JwtHelper;
import com.chanpller.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadLineController {
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private HeadlineService headlineService;
    /**
     * 实现步骤:
     *   1. token获取userId [无需校验,拦截器会校验]
     *   2. 封装headline数据
     *   3. 插入数据即可
     */
    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token){

        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        Result result = headlineService.publish(headline);
        return result;
    }

    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid){
        Result result = headlineService.findHeadlineByHid(hid);
        return result;
    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline){
        Result result = headlineService.updateHeadLine(headline);
        return result;
    }

    @PostMapping("removeByHid")
    public Result removeById(Integer hid){
        headlineService.removeById(hid);
        return Result.ok(null);
    }

}
