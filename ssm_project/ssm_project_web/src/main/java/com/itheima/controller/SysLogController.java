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

    @RequestMapping("/getExcel")
    public String getExcel(String ids) throws Exception {
        String[] split = ids.split(",");
        String s = exportExcelService.getExcel(split);
        return "redirect:/sysLog/findAll";
    }
}
