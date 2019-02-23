package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.SysLogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    //添加日志信息
    public void save(SysLog sysLog) throws Exception{
        sysLogDao.save(sysLog);
    }

    //查询所有日志
    public List<SysLog> findAll(Integer pageNum,Integer pageSize,String searchValue) throws Exception{
        PageHelper.startPage(pageNum,pageSize);
        return sysLogDao.findAll(searchValue);
    }

    public List<SysLog> findAll(String searchValue) throws Exception{
        return sysLogDao.findAll(searchValue);
    }




}
