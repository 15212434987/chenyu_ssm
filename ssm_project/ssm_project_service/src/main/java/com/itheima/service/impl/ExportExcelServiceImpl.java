package com.itheima.service.impl;

import com.itheima.dao.SysLogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.ExportExcelService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.*;

@Service
public class ExportExcelServiceImpl implements ExportExcelService {
    @Autowired
    private SysLogDao sysLogDao;

    public String getExcel(String[] split) throws Exception {
        List<SysLog> list = new ArrayList<SysLog>();
        for (String id : split) {
            SysLog sysLog = sysLogDao.findById(id);
            list.add(sysLog);
        }
        return CreateExcelDemo(list);
    }

    public String getExcel() throws Exception {
        List<SysLog> all = sysLogDao.findAll("");
        return CreateExcelDemo(all);
    }

    public  <T> String CreateExcelDemo(List<T> list) throws Exception {
        String fileName = "";
        // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("系统日志");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        Class clazz = list.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("序号");
        for (int i = 0; i < fields.length; i++) {
            cell = row.createCell((short) i+1);
            cell.setCellValue(fields[i].getName());
        }


        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            // 第四步，创建单元格，并设置值  
            row = sheet.createRow((int) i + 1);
            HSSFCell celli = row.createCell((short) 0);
            row.createCell((short) 0).setCellValue(i);
            for (int j = 0; j < fields.length; j++) {
                Character ch = fields[j].getName().charAt(0);
                row.createCell((short) j+1).setCellValue( clazz.getMethod("get" +ch.toString().toUpperCase()+fields[j].getName().substring(1)).invoke(obj).toString());
            }

        }
        // 第六步，将文件存到指定位置  
        try {
            fileName = "J:\\idea_project2\\ssm_project\\ssm_project_web\\src\\download\\" + UUID.randomUUID().toString() + ".xls";
            FileOutputStream fout = new FileOutputStream(fileName);
            wb.write(fout);
            fout.close();
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }


}
