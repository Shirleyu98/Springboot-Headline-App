package com.atguigu.controller;

import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("headline")
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;

    @Autowired
    private JwtHelper jwtHelper;

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

    @PostMapping ("removeByHid")
    public Result removeById(Integer hid){
        headlineService.removeById(hid);
        return Result.ok(null);
    }
}
