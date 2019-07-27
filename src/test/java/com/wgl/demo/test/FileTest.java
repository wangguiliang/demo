package com.wgl.demo.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-05-12
 */
@Slf4j
public class FileTest {


    private final String rootPath2 = "/";

    @Test
    public void test() {
        System.out.println("aaa");
    }

    @Test
    public void testa() {

        String initString = "initContent!";

        String content = "testContent!";

        FileOutputStream fos = null;
        PrintWriter pw = null;

        try {
            String filePath = rootPath2 + "services/Process/aaa.xml";
            String fileContent = content;


            File file = new File(filePath);

            File parentFile = file.getParentFile();

            if (!parentFile.exists()) {
                boolean b = parentFile.mkdirs();
                if (b) {
                    System.out.println("文件夹创建成功！");
                } else {
                    System.out.println("文件夹创建失败！");
                }
            }
            if (!file.exists()) {
                file.createNewFile();
                fileContent = initString;
            }

            fos = new FileOutputStream(filePath);
            pw = new PrintWriter(fos);
            pw.write(fileContent);
            pw.flush();

            if (fos != null) {
                fos.close();
            }

            if (pw != null) {
                pw.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createFile(String path, String content) {
        try {
            FileOutputStream fos = null;
            PrintWriter pw = null;

            File file = new File(path);
            File parentFile = file.getParentFile();

            if (!parentFile.exists() && !parentFile.mkdirs()) {
                log.error("ProcessBuilder过程创建文件路径失败。mkdirs:path=" + parentFile.getPath());
                throw new BusinessException(502, "ProcessBuilder过程创建文件路径失败。mkdirs:path=" + parentFile.getPath());
            }
            try {
                if (!file.exists() && !file.createNewFile()) {
                    log.error("ProcessBuilder过程创建文件失败。createNewFile:path=" + file.getPath());
                    throw new BusinessException("ProcessBuilder过程创建文件失败。createNewFile:path=" + file.getPath());
                }
                fos = new FileOutputStream(path);
                pw = new PrintWriter(fos);
                pw.write(content);
                pw.flush();
            } catch (IOException ioe) {
                log.error("创建文件或写入文件异常", ioe);
                throw new BusinessException("创建文件或写入文件异常", ioe);
            } catch (BusinessException be) {
                throw be;
            } catch (Exception e) {
                log.error("ProcessBuilder中createFile异常。", e);
                throw new BusinessException("ProcessBuilder中createFile异常。", e);
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (Exception e) {
                    log.error("FileOutputStream关闭失败！");
                    throw new BusinessException("FileOutputStream关闭失败！");
                }
                if (pw != null) {
                    pw.close();
                }
            }
        } catch (Exception e) {

        }


    }

}
