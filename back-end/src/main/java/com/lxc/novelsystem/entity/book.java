package com.lxc.novelsystem.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description:
 * @author: Anthony
 * @time: 2022/2/18
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("小说")
public class book {
    private int id;
    private String fileName ;
    private String cover;
    private String title;
    private String author;
    private  String publisher;
    private String bookId;
    private int category;
    private String categoryText;
    private String language;
    private String rootFile;

}
