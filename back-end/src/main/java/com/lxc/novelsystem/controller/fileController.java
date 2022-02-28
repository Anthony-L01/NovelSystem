package com.lxc.novelsystem.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxc.novelsystem.Util.JwtUtil;
import com.lxc.novelsystem.entity.ResponseResult;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: Anthony
 * @time: 2022/1/27
 */
@Slf4j
@RestController
@Api(tags = "文件控制类")
public class fileController {



    List<JSONArray> list = new ArrayList<>();



    /**
    * @Description: 文件上传
    * @Param: [org.springframework.web.multipart.MultipartFile]
    * @return: com.lxc.novelsystem.entity.ResponseResult
    * @Author: Anthony
    * @Date: 2022/2/26
    */

    @ApiOperation("用户端文件上传")
    @PostMapping("/NovelSystem/upload")
    public ResponseResult upload(@RequestPart("file")MultipartFile file)
    {
        try {
            if (file.isEmpty()) {
                return new ResponseResult(400,"文件为空");
            }

            // 获取文件名
            String fileName = file.getOriginalFilename();
           log.info("上传的文件名为：" + fileName);//写日志
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);//写日志
            // 设置文件存储路径         *************************************************
         String filePath = "/home/";
//            String filePath="../";
            String path = filePath + fileName;
            log.info("文件存放路径为：" + path);
            File dest = new File(new File(path).getAbsolutePath());// dist为文件，有多级目录的文件
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {//因此这里使用.getParentFile()，目的就是取文件前面目录的路径
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",fileName);
            JSONObject jsonObject1 = new JSONObject();
            String downloadPath="http://101.132.45.41:8087/NovelSystem/download/"+fileName;
            jsonObject1.put("download",downloadPath);

            log.info("文件的下载路径为：" + downloadPath);
            JSONArray jsonArray = new JSONArray();
           jsonArray.add(jsonObject);
           jsonArray.add(jsonObject1);
               Date date = new Date();
               SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
               String time=dateFormat.format(date);
              JSONObject jsonObject2 = new JSONObject();
              jsonObject2.put("UploadTime",time);
              JSONArray jsonArray1=new JSONArray();
              jsonArray1.add(jsonObject2);
              jsonArray1.add(jsonObject);
              list.add(jsonArray1);



            return new ResponseResult(200,"上传成功",jsonArray);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseResult(400,"上传失败");

    }


    @GetMapping("/NovelSystem/download/{name}")
    public String download(@PathVariable("name")String fileName,  HttpServletResponse response)
    {
        if (fileName != null) {
            //设置文件路径，.是在此文件夹下
            File file = new File("/home/"+fileName);
//            File file = new File("../"+fileName);
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally { // 做关闭操作
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }


    @GetMapping("/NovelSystem/getUpload")
    public List<JSONArray> getUpload()
    {
        return list;
    }

}
