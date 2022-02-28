package com.lxc.novelsystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxc.novelsystem.entity.ResponseResult;
import com.lxc.novelsystem.entity.book;
import com.lxc.novelsystem.service.BookService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: Anthony
 * @time: 2022/2/18
 */
@Slf4j
@RestController
@Api(tags = "小说控制类")
public class BookController {


    @Autowired
    BookService bookService;
    
    /** 
    * @Description: 模糊查询
    * @Param: [java.lang.String]
    * @return: com.lxc.novelsystem.entity.ResponseResult
    * @Author: Anthony
    * @Date: 2022/2/26
    */
    @PostMapping("/NovelSystem/fuzzSearch/{str}")
    public ResponseResult  fuzzSearch(@PathVariable String str)
    {
        JSONObject jsonObject = new JSONObject();
        JSONArray  jsonArray = new JSONArray();
        List<book> books = bookService.fuzzySearch(str);
        jsonObject.put("books",jsonArray);
        for (book book:books
             ) {
            JSONObject jsonObject1 = new JSONObject();
            JSONArray jsonArray1 = new JSONArray();
            JSONObject jsonObject2 = new JSONObject();
            jsonObject1.put("id",book.getId());
            log.info("模糊查询结果书的id :{}",book.getId());
            jsonObject2.put("title",book.getTitle());
            log.info("模糊查询结果书的标题 :{}",book.getTitle());
            jsonArray1.add(jsonObject1);
            jsonArray1.add(jsonObject2);
            jsonArray.add(jsonArray1);
        }
        return  new ResponseResult(200,"获取成功", jsonObject);
    }
}
