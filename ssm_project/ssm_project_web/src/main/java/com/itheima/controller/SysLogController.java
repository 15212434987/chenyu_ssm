package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysLog;
import com.itheima.service.ExportExcelService;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.plugin2.message.Serializer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController{

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private ExportExcelService exportExcelService;

    //查询所有日志信息
    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                          @RequestParam(name = "pageSize",required = false,defaultValue = "15")Integer pageSize,
                          @RequestParam(name = "searchValue",required = false,defaultValue = "")String searchValue, Model model) throws Exception{
        List<SysLog> list = sysLogService.findAll(pageNum,pageSize,searchValue);
        PageInfo<SysLog> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("sv",searchValue);
        return "syslog-list";
    }

    //生成Excel表格
    @RequestMapping("/getExcel")
    public String getExcel(@RequestParam(value = "ids",required = false,defaultValue = "") String ids,String kind, HttpServletRequest request) throws Exception {
        String fileName = "";
        if (kind.equals("choose")){
            if (ids.equals("")){
                return "forward:/sysLog/findAll";
            }
            String[] split = ids.split(",");
            fileName = exportExcelService.getExcel(split);
        }
        if (kind.equals("all")){
            fileName = exportExcelService.getExcel();
        }
        return "forward:/sysLog/download?fileName="+fileName;
    }

    //下载生成的Excel表格
    @RequestMapping("/download")
    public void downloadExcel(String fileName, HttpServletResponse response) throws Exception {

        //将文件读取到内存
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));

        //给文件设置编码
        fileName = URLEncoder.encode(fileName,"UTF-8");

        //设置响应头和数据格式
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setContentType("multipart/form-data");

        //输出流
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();
    }
}
