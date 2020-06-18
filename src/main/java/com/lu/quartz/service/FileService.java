package com.lu.quartz.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author 小卢
 */
@Service
public class FileService {

    public void test() {
        System.out.println("成功");
    }

    public void out() {
        File sourceFile = new File("src/main/resources/sourceFile/test.txt");

        LocalDate today = LocalDate.now();
        String format = DateTimeFormatter.BASIC_ISO_DATE.format(today);

        File outPath = new File("src/main/resources/outFile/" + format);
        File outFile = new File("src/main/resources/outFile/" + format + "/out.txt");
        //如果文件夹不存在则创建
        if (!outPath.exists()) {
            outFile.mkdir();
        }
        try (FileInputStream fileInputStream = new FileInputStream(sourceFile); FileOutputStream fileOutputStream = new FileOutputStream(outFile)) {

            int read;
            while ( (read = fileInputStream.read())!=-1) {
                fileOutputStream.write(read);
            }

            String  s= "\n测试通过没问题,追加一段文字测试";

            byte[] bytes = s.getBytes();
            fileOutputStream.write(bytes);


            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
