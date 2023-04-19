package com.github.yutooou.mdconvintellij.services;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.vfs.VirtualFile;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public final class ConvertService {

    public void convert(VirtualFile file) throws IOException {
        // 判断path是文件还是文件夹
        if (file.isDirectory()) {
            System.out.println("文件夹");
            // TODO: 做poi测试
            XWPFDocument document = new XWPFDocument();

            // 创建一个段落，写入一些文本
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("Hello, World!");

            // 保存文档到磁盘
            File outputFile = new File(file.getPath() + "/example.docx");
            FileOutputStream out = new FileOutputStream(outputFile);
            document.write(out);
            out.close();
        } else {
            System.out.println("文件");
            byte[] bytes = file.contentsToByteArray();
            System.out.println(new String(bytes));
            System.out.println("Word document created successfully!");


        }
    }
}
