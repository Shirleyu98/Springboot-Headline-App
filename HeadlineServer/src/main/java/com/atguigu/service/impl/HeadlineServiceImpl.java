package com.atguigu.service.impl;

import com.atguigu.pojo.vo.PortalVo;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.mapper.HeadlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @author shiyu
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-11-10 22:55:46
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService {

    @Autowired
    private HeadlineMapper headlineMapper;

    @Override
    public Result findNewPage(PortalVo portalVo) {

        //1.条件拼接，需要非空判断
        LambdaQueryWrapper<Headline> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(portalVo.getKeyWords()), Headline::getTitle, portalVo.getKeyWords())
                .eq(portalVo.getType() != null, Headline::getType, portalVo.getType());

        //2.分页参数
        IPage<Headline> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());

        //3.分页查询，自定义方法
        headlineMapper.selectPageMap(page, portalVo);

        //4.结果封装
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageData", page.getRecords());
        pageInfo.put("pageNum", page.getCurrent());
        pageInfo.put("pageSize", page.getSize());
        pageInfo.put("totalPage", page.getPages());
        pageInfo.put("totalSize", page.getTotal());

        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo", pageInfo);
        // 响应JSON
        return Result.ok(pageInfoMap);
    }

    @Override
    public Result showHeadlineDetail(Integer hid) {
        //1.实现根据id的查询(多表
        Map headLineDetail = headlineMapper.selectDetailMap(hid);
        //2.拼接头条对象(阅读量和version)进行数据更新
        Headline headline = new Headline();
        headline.setHid(hid);
        headline.setPageViews((Integer) headLineDetail.get("pageViews") + 1); //阅读量+1
        headline.setVersion((Integer) headLineDetail.get("version")); //设置版本
        headlineMapper.updateById(headline);

        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("headline", headLineDetail);
        return Result.ok(pageInfoMap);
    }

    @Override
    public Result publish(Headline headline) {
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headline.setPageViews(0);
        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result findHeadlineByHid(Integer hid) {
        Headline headline = headlineMapper.selectById(hid);
        Map<String,Object> pageInfoMap=new HashMap<>();
        pageInfoMap.put("headline",headline);
        return Result.ok(pageInfoMap);
    }

    @Override
    public Result updateHeadLine(Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();

        headline.setVersion(version);
        headline.setUpdateTime(new Date());

        headlineMapper.updateById(headline);

        return Result.ok(null);
    }


}




