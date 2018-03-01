package com.xianqu.action;

import com.xianqu.common.AliyunOssClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/27.
 */
@Controller
@Api(value="上传图片设置controller",description="上传图片操作",tags={"上传图片接口"})
@CrossOrigin
public class CommonController {

    @Autowired
    private AliyunOssClient aliyunOssClient;

    @ApiOperation(value="上传文件", notes="上传文件")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> map = new HashMap<String, Object>();
        String fileCode = "";
        if (!file.isEmpty()) {
            File f = null;
            try {
                String fileName = file.getOriginalFilename();
                String[] fileNameArray = fileName.split("\\.");
                f = File.createTempFile("tmp", null);
                file.transferTo(f);
                fileCode = aliyunOssClient.uploadFile(f, fileNameArray[fileNameArray.length - 1]);
                f.deleteOnExit();
            } catch (IOException e) {
                e.printStackTrace();
            }
            map.put("fileCode", fileCode);
            map.put("code", 200);
            return map;
        } else {
            throw new RuntimeException("上传失败");
        }
    }
}
