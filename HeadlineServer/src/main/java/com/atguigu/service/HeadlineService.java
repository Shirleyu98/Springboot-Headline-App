package com.atguigu.service;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.vo.PortalVo;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author shiyu
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-11-10 22:55:46
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline);

    Result findHeadlineByHid(Integer hid);

    Result updateHeadLine(Headline headline);
}
