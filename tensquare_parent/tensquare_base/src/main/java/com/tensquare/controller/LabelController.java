package com.tensquare.controller;

import com.tensquare.pojo.Label;
import entity.Result;
import entity.StatuCode;
import org.springframework.web.bind.annotation.*;

/**
 * LabelController
 *
 * @Author wanggaian
 * @Date 2019/4/18 22:27
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/label")
public class LabelController {

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatuCode.OK, "查询成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findAllById(@PathVariable(value = "labelId") String labelId) {
        return new Result(true, StatuCode.OK, "查询成功");
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label) {
        return new Result(true, StatuCode.OK, "添加成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result update(@PathVariable String labelId, @RequestBody Label label) {
        return new Result(true, StatuCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{labelId}")
    public Result deleteById(@PathVariable String labelId) {
        return new Result(true, StatuCode.OK, "删除成功");
    }
}
