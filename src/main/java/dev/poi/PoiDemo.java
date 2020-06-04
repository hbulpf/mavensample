
package dev.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import dev.utils.StringUtils;

/**
 * PoiDemo
 * 参考:
 * 1. excel处理：https://recomm.cnblogs.com/blogpost/7278869
 * 2. word处理: https://www.w3cschool.cn/apache_poi_word/apache_poi_word_overview.html
 */
public class PoiDemo {
    public static void main(String[] args) {

        try {
            exportExcel2007("F:\\tmp\\员工信息.xlsx");
            exportExcel2003("F:\\tmp\\员工信息.xls");
            importExcel2007("F:\\tmp\\员工信息.xlsx");
            exportWord2003("F:\\tmp\\a.doc");
            exportWord2007("sample.docx", "F:\\tmp\\output.docx");
        } catch (FileNotFoundException e) {
            StringUtils.printHr("写入失败", e.getMessage());
        } catch (IOException e) {
            StringUtils.printHr("写入失败", e.getMessage());
        }

    }

    /**
     * 导出excel2007及以上版本
     *
     * @throws IOException
     * @param filepath
     */
    public static void exportExcel2007(String filepath) throws IOException {
        // 创建工作簿 类似于创建Excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建 sheetname页名
        XSSFSheet sheet = workbook.createSheet("员工信息");
        // 创建字体，设置其为红色、粗体
        XSSFFont font = workbook.createFont();
        font.setColor(HSSFFont.COLOR_RED);
        font.setBold(true);
        // 创建格式
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);

        // 给第3列设置为20个字的宽度
        sheet.setColumnWidth(3, 20 * 256);
        // 给第4列设置为20个字的宽度
        sheet.setColumnWidth(4, 20 * 256);
        // 创建一行,下标从0开始
        XSSFRow row = sheet.createRow(0);
        // 创建这行中的列,下标从0开始 （表头）
        XSSFCell cell = row.createCell(0);
        // 应用格式
        cell.setCellStyle(cellStyle);
        cell.setCellType(CellType.STRING);
        // 给cell 0下表赋值
        cell.setCellValue("姓名");
        // 创建这行中的列,并给该列直接赋值
        row.createCell(1).setCellValue("年龄");
        row.createCell(2).setCellValue("性别");
        row.createCell(3).setCellValue("生日");
        row.createCell(4).setCellValue("手机号");
        // 设置表里内容
        row = sheet.createRow(1);
        row.createCell(0).setCellValue("T");
        row.createCell(1).setCellValue("保密");
        row.createCell(2).setCellValue("男");
        row.createCell(3).setCellValue("保密");
        row.createCell(4).setCellValue("12121212121");

        row = sheet.createRow(2);
        row.createCell(0).setCellValue("T");
        row.createCell(1).setCellValue("18");
        row.createCell(2).setCellValue("女");
        row.createCell(3).setCellValue("2000-01-01");
        row.createCell(4).setCellValue("12121212122");
        File file = new File(filepath);
        FileOutputStream stream = new FileOutputStream(file);
        workbook.write(stream);
        StringUtils.printHr("excel文件写入成功");
        stream.close();
    }

    /**
     * 导出excel2003
     *
     * @throws IOException
     * @param filepath
     */
    public static void exportExcel2003(String filepath) throws IOException {
        // 创建工作簿 类似于创建Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建 sheetname页名
        HSSFSheet sheet = workbook.createSheet("员工信息");
        // 创建一行,下标从0开始
        HSSFRow row = sheet.createRow(0);
        // 创建这行中的列,下标从0开始 （表头）
        HSSFCell cell = row.createCell(0);
        // 给cell 0下表赋值
        cell.setCellValue("姓名");
        // 创建这行中的列,并给该列直接赋值
        row.createCell(1).setCellValue("年龄");
        row.createCell(2).setCellValue("性别");
        row.createCell(3).setCellValue("生日");
        row.createCell(4).setCellValue("手机号");
        // 设置表里内容
        row = sheet.createRow(1);
        row.createCell(0).setCellValue("T");
        row.createCell(1).setCellValue("保密");
        row.createCell(2).setCellValue("男");
        row.createCell(3).setCellValue("保密");
        row.createCell(4).setCellValue("12121212121");

        row = sheet.createRow(2);
        row.createCell(0).setCellValue("T");
        row.createCell(1).setCellValue("18");
        row.createCell(2).setCellValue("女");
        row.createCell(3).setCellValue("2000-01-01");
        row.createCell(4).setCellValue("12121212122");
        File file = new File(filepath);
        FileOutputStream stream = new FileOutputStream(file);
        StringUtils.printHr("excel文件写入成功");
        workbook.write(stream);
        stream.close();
    }

    /**
     * 导入Excel2007
     *
     * @throws IOException
     * @param filepath
     */
    public static void importExcel2007(String filepath) throws IOException {
        File file = new File(filepath);
        StringUtils.printHr("读入excel文件");
        // 获得该文件的输入流
        FileInputStream stream = new FileInputStream(file);
        // 多态 抛异常
        Workbook sheets = new XSSFWorkbook(stream);
        // 获取一个工作表(sheet页)，下标从0开始
        Sheet sheet = sheets.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            // 获取行数
            Row row = sheet.getRow(i);
            // 获取单元格 取值
            String value1 = row.getCell(0).getStringCellValue();
            String value2 = row.getCell(1).getStringCellValue();
            String value3 = row.getCell(2).getStringCellValue();
            String value4 = row.getCell(3).getStringCellValue();
            String value5 = row.getCell(4).getStringCellValue();

            System.out.println(String.format("%s,%s,%s,%s,%s", value1, value2, value3, value4, value5));
        }
        StringUtils.printHr("excel文件处理成功");
        sheets.close();
        stream.close();
    }

    /**
     * 导出 Word2003
     *
     * @param filepath
     * @throws IOException
     */
    public static void exportWord2003(String filepath) throws IOException {
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(new File(filepath));

        // create Paragraph
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("At tutorialspoint.com, we strive hard to " + "provide quality tutorials for self-learning "
            + "purpose in the domains of Academics, Information "
            + "Technology, Management and Computer Programming Languages.");
        document.write(out);
        out.close();
        System.out.println("create paragraph.docx written successfully");
    }

    /**
     * 导出 Word2007
     * 
     * @param templatePath
     * @param outputPath
     * @throws IOException
     */
    public static void exportWord2007(String templatePath, String outputPath) throws IOException {
        // 准备工作，生成docx对象
        InputStream is = new FileInputStream(templatePath);
        XWPFDocument docx = new XWPFDocument(is);

        // 获取所有的段
        List<XWPFParagraph> paragraphs = docx.getParagraphs();
        // 遍历所有的段
        for (int i = 0; i < paragraphs.size(); i++) {
            // 获取该段所有的文本对象
            List<XWPFRun> runs = paragraphs.get(i).getRuns();
            for (int j = 0; j < runs.size(); j++) {
                XWPFRun run = runs.get(j);
                // 假如该文本对象里包含${name},则使用run.setText(str.replace("${name}","HelloKity"),0)替换
                if (run.toString().contains("${name}")) {
                    String str = run.toString();
                    run.setText(str.replace("${name}", "HelloKity"), 0);
                }
            }
        }

        // 获取所有的Table
        List<XWPFTable> tables = docx.getTables();
        // 定位到第一个Table
        XWPFTable table = tables.get(0);
        // Table增加一行
        table.createRow();
        // 定位到新加的行，一开始只有两行，变成三行，定位为2
        XWPFTableRow row = table.getRow(2);
        // 添加内容
        row.getCell(0).setText("这里是3行1列");

        // 获取表格
        List<XWPFTable> otherTables = docx.getTables();
        // 定位到第一个表格
        XWPFTable anotherTable = otherTables.get(1);
        // 遍历该表格所有的行
        for (int i = 0; i < anotherTable.getRows().size(); i++) {
            XWPFTableRow tmpRow = anotherTable.getRow(i);
            // 遍历该行所有的列
            for (int j = 0; j < tmpRow.getTableCells().size(); j++) {
                XWPFTableCell cell = tmpRow.getTableCells().get(j);
                // 获取该格子里所有的段
                List<XWPFParagraph> paragraphs2 = cell.getParagraphs();
                for (XWPFParagraph p : paragraphs2) {
                    // 遍历该格子里的段
                    List<XWPFRun> runs = p.getRuns();
                    for (XWPFRun run : runs) {
                        // 遍历该段里的所有文本
                        String str = run.toString();
                        // 如果该段文本包含${like}，则替换
                        if (str.equals("${like}")) {
                            run.setText("MyFavorite", 0);
                        }
                    }
                }
            }
        }

        // 输出到write.docx
        OutputStream os = new FileOutputStream(outputPath);
        docx.write(os);
        StringUtils.printHr("word文件写入成功");
        is.close();
        os.close();
    }
}
