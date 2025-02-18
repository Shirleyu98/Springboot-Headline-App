package com.atguigu.mapper;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.vo.PortalVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author shiyu
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-11-10 22:55:46
* @Entity com.atguigu.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    //自定义分页查询方法
    IPage<Map> selectPageMap(IPage<Headline> page,
                             @Param("portalVo") PortalVo portalVo);

    Map selectDetailMap(Integer hid);
}




