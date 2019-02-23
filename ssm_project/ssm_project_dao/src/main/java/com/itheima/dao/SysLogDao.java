package com.itheima.dao;

import com.itheima.domain.SysLog;

import java.util.List;

public interface SysLogDao {
    //添加日志
    void save(SysLog sysLog) throws Exception;

    //查询所有日志
    List<SysLog> findAll(String searchValue) throws Exception;
}
