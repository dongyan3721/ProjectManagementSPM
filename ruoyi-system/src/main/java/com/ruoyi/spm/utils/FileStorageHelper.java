package com.ruoyi.spm.utils;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.UUID;

import java.util.List;

public class FileStorageHelper {
    // 自定义路径，前面和后面都不要加斜杠
    public static String[] generateUploadPath(String attribute, String originalFileName, String customStorageFolder){
        List<String> list = StringUtils.str2List(originalFileName, "\\.", true, true);
        String filename = attribute!=null?attribute+"-"+ UUID.randomUUID()+"."+list.get(list.size()-1):UUID.randomUUID()+"."+list.get(list.size()-1);
        String fileStorage = RuoYiConfig.getDownloadPath() + customStorageFolder + "/" + filename;
        String visitUrl = "/static/" + customStorageFolder + "/" + filename;
        return new String[] {fileStorage, visitUrl};
    }

//    public static String[] parseStoragePath(String url){
//        List<String> list = StringUtils.str2List(url, "/", true, true);
//        StringBuilder sb = new StringBuilder();
//        for (int i=0;i<list.size()-1;++i){
//            sb.append(list.get(i));
//            sb.append("/");
//        }
//        sb.append()
//    }
}
