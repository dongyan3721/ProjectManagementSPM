package com.ruoyi.system.controller;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.file.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * 静态资源通用下载接口
 * @author Santa Antilles
 * @Date 2023/12/13-0:00
 */
@CrossOrigin
@RestController
@RequestMapping()
public class StaticResourceVisitController {

    @RequestMapping(value = "/static/**", method = RequestMethod.GET)
    public void getStaticResourceGet(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String decode = URLDecoder.decode(request.getRequestURL().toString(), "UTF-8");
        String resourceName = decode.replace("http://localhost:8080/static/", "");
        if(!FileUtils.checkAllowDownload(resourceName)){
            throw new Exception(String.format("请求的文件%s不存在！", resourceName));
        }
        String fullPath = RuoYiConfig.getDownloadPath() + resourceName;
        System.out.println(fullPath);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        FileUtils.setAttachmentResponseHeader(response, resourceName);
        FileUtils.writeBytes(fullPath, response.getOutputStream());
    }

    @RequestMapping(value = "/static/**", method = RequestMethod.POST)
    public void getStaticResourcePost(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String decode = URLDecoder.decode(request.getRequestURL().toString(), "UTF-8");
        String resourceName = decode.replace("http://localhost:8080/static/", "");
        if(!FileUtils.checkAllowDownload(resourceName)){
            throw new Exception(String.format("请求的文件%s不存在！", resourceName));
        }
        String fullPath = RuoYiConfig.getDownloadPath() + resourceName;
        System.out.println(fullPath);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        FileUtils.setAttachmentResponseHeader(response, resourceName);
        FileUtils.writeBytes(fullPath, response.getOutputStream());
    }
}
