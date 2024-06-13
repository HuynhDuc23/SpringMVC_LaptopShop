package vn.com.demo.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadFileService {
  private ServletContext servletContext;

  public UploadFileService(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  public String handleSaveUploadFile(MultipartFile file, String targetFolder) {
    String finalName = "";
    try {
      byte[] bytes;
      String pathResult = this.servletContext.getRealPath("");
      System.out.println(pathResult);

      bytes = file.getBytes();
      // Đường dẫn thư mục gốc nơi bạn muốn lưu trữ file
      String rootPath = this.servletContext.getRealPath("/resources/images");
      // D:\SpringMVC_LaptopShop\demo\demo\src\main\webapp\resources

      File dir = new File(rootPath + File.separator + targetFolder);
      if (!dir.exists())
        dir.mkdirs();
      // Create the file on server
      finalName = dir.getAbsolutePath() + File.separator +
          +System.currentTimeMillis() + "-" + file.getOriginalFilename();

      File serverFile = new File(finalName);
      // Ghi dữ liệu vào file
      BufferedOutputStream stream = new BufferedOutputStream(
          new FileOutputStream(serverFile));
      stream.write(bytes);
      stream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return finalName;
  }
}

// absoluteb path : duong dan tuyet doi
// relativeb path : duong dan tuong doi
// spring no boc servletContext.getRealPath() : tra webapp
