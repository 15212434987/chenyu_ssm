package com.itheima.service;

import com.itheima.domain.SysLog;

import java.util.List;

public interface SysLogService {
    void save(SysLog sysLog) throws Exception;


    List<SysLog> findAll(Integer pageNum,Integer pageSize,String searchValue)throws Exception;


}
