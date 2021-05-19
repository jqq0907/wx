package com.example.wx;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfCell;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class WxApplicationTests {

    @Test
    void contextLoads() {
        HttpResponse execute = HttpUtil.createGet("https://api.weixin.qq.com/sns/jscode2session")
                .form("appid", "wx2cc7e47c681d1101")
                .form("js_code", "003Qdy1w3wyIoW2zDp2w3MjOGY1Qdy1r")
                .form("secret", "f82ac985066a669e42def2f77e0e8b30")
                .form("grant_type", "authorization_code")
                .charset(CharsetUtil.CHARSET_UTF_8)
                .execute();
        String body = execute.body();
    }

    @Test
    void test1() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        String fileName = "C:\\Users\\xiaoen\\Desktop\\uploadFiles\\";
        String s = LocalDate.now().toString();
        // 竖直方向文档
        Document document = new Document(new Rectangle(PageSize.A4));
        try {
            // 中文字体
            Font font = new Font(BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fileName + s + atomicInteger + ".pdf"));
            atomicInteger.incrementAndGet();
            document.addTitle("违法");
            document.open();
            // 生成一个两列的表格
            PdfPTable table = new PdfPTable(2);
            // 无边框
            table.getDefaultCell().setBorder(PdfCell.NO_BORDER);
            PdfPCell cell;
            int size = 15;
            cell = new PdfPCell(new Phrase("一", font));
            // 设置高度
            cell.setFixedHeight(size);
//            cell.setBorder(PdfCell.NO_BORDER);
            table.addCell(cell);
            table.addCell(new PdfPCell(new Phrase("二", font)));
            document.add(table);
            /*document.add(new Paragraph("签字:", font));
            document.add(new Paragraph("日期:", font));*/
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    @Test
    public void test3(){
        String s = IdUtil.simpleUUID();
        System.out.println(s);
    }
}
