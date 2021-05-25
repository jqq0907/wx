package com.example.wx;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.lowagie.text.pdf.PdfCell;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
//            table.getDefaultCell().setBorder(PdfCell.NO_BORDER);
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
        Document document = new Document(new Rectangle(PageSize.A4));
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos = null;
        PdfStamper stamper;
        try {
            // 模板路径
            String templatePath = "C:\\Users\\xiaoen\\Desktop\\pdfTemplate\\wgtxTemplate.pdf";
            // 文件名称
            String fileName = URLEncoder.encode("违规通行" + LocalDate.now().toString() + ".pdf", "UTF-8");

            out = new FileOutputStream(fileName);
            PdfWriter writer = PdfWriter.getInstance(document, out);
            // 读取模板
            reader = new PdfReader(templatePath);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            // 模板表单
            AcroFields form = stamper.getAcroFields();
            BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            form.addSubstitutionFont(baseFont);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 车牌号
            form.setField("carNo", "d");
            // 通行证号
            form.setField("txzbh", "d");
            // 车辆类型
            form.setField("carType", "2");
            // 有效期起
            form.setField("yxqs", "");
            // 驾驶员名称
            form.setField("jsyxm", "");
            // 有效期止
            form.setField("yxqz", "");
            // 驾驶员电话
            form.setField("jsylxfs", "");
            // 违法类型
            form.setField("wflx", "");
            // 不可编辑
            stamper.setFormFlattening(true);
            stamper.close();
            PdfCopy copy = new PdfCopy(document, out);
            document.addTitle("违规通行");
            document.open();
            // 生成一个两列的表格
            PdfPTable table = new PdfPTable(2);
            // 无边框
            table.getDefaultCell().setBorder(PdfCell.NO_BORDER);
            PdfPCell cell;
            int size = 15;
            Font font = new Font(BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
            cell = new PdfPCell(new Phrase("一", font));
            // 设置高度
            cell.setFixedHeight(size);
//            cell.setBorder(PdfCell.NO_BORDER);
            table.addCell(cell);
            table.addCell(new PdfPCell(new Phrase("二", font)));
            document.add(table);
            PdfImportedPage importPage = copy.getImportedPage(
                    new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            bos.flush();
            bos.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
