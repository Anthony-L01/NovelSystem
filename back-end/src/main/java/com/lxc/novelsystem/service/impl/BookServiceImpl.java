package com.lxc.novelsystem.service.impl;

import com.lxc.novelsystem.entity.book;
import com.lxc.novelsystem.mapper.BookMapper;
import com.lxc.novelsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Anthony
 * @time: 2022/2/18
 */
@Service
public class BookServiceImpl implements BookService {
   @Autowired
    BookMapper bookMapper;

    @Override
    public List<book> fuzzySearch(String str) {
      return bookMapper.fuzzySearch(str);
    }
}
