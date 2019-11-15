package com.ucucs.practice.excel.controller;

import com.ucucs.practice.excel.sax.XLSX2CSV;
import com.ucucs.practice.excel.util.TempFileUtil;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/excel")
public class ExcelController {

  @PostMapping("/upload")
  public String uploadExcel(@RequestParam("file") MultipartFile file, String name,
      HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    //String filename = "D:\\8月\\otc\\sax解析测试\\0221otcposdata20190909.xlsm";
    File uploadFile = TempFileUtil.writeInputStreamToFile(file.getInputStream(), 1024);

    /*
    OPCPackage pkg;
    try {

      pkg = OPCPackage.open(uploadFile);
      XSSFReader r = new XSSFReader(pkg);
      //查看转换的xml原始文件，方便理解后面解析时的处理,
      InputStream in = r.getSheet("rId1");
      byte[] buf = new byte[1024];
      int len;
      while ((len = in.read(buf)) != -1) {
        System.out.write(buf, 0, len);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
     */

    try (OPCPackage p = OPCPackage.open(uploadFile, PackageAccess.READ)) {
      XLSX2CSV xlsx2csv = new XLSX2CSV(p, System.out, -1);
      xlsx2csv.process();
    }

    return "Error";
  }

}
