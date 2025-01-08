package com.chanpller.service;

import com.chanpller.model.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chanpller.vo.PortalVo;
import com.chanpller.vo.Result;

/**
* @author Administrator
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2025-01-08 10:19:09
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline);

    Result findHeadlineByHid(Integer hid);

    Result updateHeadLine(Headline headline);
}
