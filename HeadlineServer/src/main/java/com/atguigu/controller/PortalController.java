package com.atguigu.controller;

import com.atguigu.pojo.Type;
import com.atguigu.pojo.vo.PortalVo;
import com.atguigu.service.HeadlineService;
import com.atguigu.service.TypeService;
import com.atguigu.utils.Result;
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

    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        //直接调用业务层，查询全部数据
        List<Type> list = typeService.list();
        return Result.ok(list);
    }

    @PostMapping("findNewsPage")
    public Result findNewPage(@RequestBody PortalVo portalVo){
        Result result = headlineService.findNewPage(portalVo);
        return result;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}
