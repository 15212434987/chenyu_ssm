
import com.itheima.domain.SysLog;
import com.itheima.service.impl.ExportExcelServiceImpl;
import com.itheima.service.impl.SysLogServiceImpl;

import java.util.*;

public class ExportExcelTest {

    private static ExportExcelServiceImpl exportExcelService = new ExportExcelServiceImpl();
    public static void main(String[] args) throws Exception {
        List<SysLog> list = new ArrayList<SysLog>();
        for (int i = 0; i < 5; i++) {
            SysLog sysLog = new SysLog();
            sysLog.setId(""+i);
            sysLog.setMethod(""+i);
            list.add(sysLog);
        }
        exportExcelService.CreateExcelDemo(list);

    }
}
