package com.lxc.novelsystem.mapper;

import com.lxc.novelsystem.entity.book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:
 * @author: Anthony
 * @time: 2022/2/18
 */
@Mapper
public interface BookMapper {
   @Select("select id,title from book where title regexp '${arg0}' limit 10")
    List<book> fuzzySearch (String str);
}
