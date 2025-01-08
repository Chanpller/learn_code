package com.chanpller.controller;

import com.chanpller.model.Type;
import com.chanpller.service.HeadlineService;
import com.chanpller.service.TypeService;
import com.chanpller.vo.PortalVo;
import com.chanpller.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    /**
     * 查询全部类别信息
     * @return
     */
    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        //直接调用业务层,查询全部数据
        List<Type> list = typeService.list();
        return  Result.ok(list);
    }

    /**
     * 首页分页查询
     * @return
     */
    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        Result result = headlineService.findNewsPage(portalVo);
        return result;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}
