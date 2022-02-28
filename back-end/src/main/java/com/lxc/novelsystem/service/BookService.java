package com.lxc.novelsystem.service;

import com.lxc.novelsystem.entity.book;

import java.util.List;

/**
 * @description:
 * @author: Anthony
 * @time: 2022/2/18
 */
public interface BookService {
    /** 
    * @Description: 模糊查询
    * @Param: [java.lang.String]
    * @return: java.util.List<com.lxc.novelsystem.entity.book>
    * @Author: Anthony
    * @Date: 2022/2/28
    */
    List<book> fuzzySearch(String str);
}
