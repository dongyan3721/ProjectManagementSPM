package com.ruoyi.system.controller;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.file.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Santa Antilles
 * @Date 2023/12/13-0:00
 */
@RestController
@RequestMapping("/static")
public class StaticResourceVisitController {

    @GetMapping("/course-avatar/{resourceName}")
    public void getStaticResource(HttpServletResponse response, @PathVariable String resourceName) throws Exception {
        System.out.println(resourceName);
        if(!FileUtils.checkAllowDownload(resourceName)){
            throw new Exception(String.format("请求的文件%s不存在！", resourceName));
        }
        String fullPath = RuoYiConfig.getDownloadPath() + "course-avatar/" +resourceName;
        System.out.println(fullPath);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        FileUtils.setAttachmentResponseHeader(response, resourceName);
        FileUtils.writeBytes(fullPath, response.getOutputStream());
    }
}
