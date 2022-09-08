package ltd.ruikai.reggie.controller;

import lombok.extern.slf4j.Slf4j;
import ltd.ruikai.reggie.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传下载
 * @ author  tanruikai
 * @ date  2022/9/5 20:12
 * @ version 1.0
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${reggie.path}")
    private String bashPath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){

        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + suffix;

        File dir = new File(bashPath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(bashPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        //输入流读取文件内容
        try {
            File file = new File(bashPath + name);
            if(!file.exists()){
                return;
            }
            FileInputStream fis = new FileInputStream(file);
            ServletOutputStream os = response.getOutputStream();

            response.setContentType("image/png");
            int len = 0;
            byte[] bytes = new byte[1024];
            while((len = fis.read(bytes)) != -1){
                os.write(bytes, 0, len);
                os.flush();
            }
            os.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //输出流写回数据

    }


}
