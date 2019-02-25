package com.itheima.service;

import java.util.List;

public interface ExportExcelService {

    String getExcel(String[] split) throws Exception;

    String getExcel() throws Exception;
}
