package com.chanpller.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chanpller.model.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chanpller.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author Administrator
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2025-01-08 10:19:09
* @Entity com.chanpller.model.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    Page<Map> selectPageMap(IPage<Headline> page, @Param("portalVo") PortalVo portalVo);

    Map selectDetailMap(Integer hid);
}




