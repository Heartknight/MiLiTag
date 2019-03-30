package com.mili.util;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletResponse;

import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelHelper {
	
	
	/**
	 * 根据导入的文件生成sql语句
	 * @param fileName excel完整路径名
	 * @param sql   一直到values的sql
	 * @return  sql语句
	 */
	public static String imports(String fileName,String sql){
		// 导入已存在的Excel文件，获得只读的工作薄对象
		FileInputStream fis=null;
		Workbook wk=null;
		try {
			fis = new FileInputStream(fileName);
			wk = Workbook.getWorkbook(fis);
			// 获取第一张Sheet表
			Sheet sheet = wk.getSheet(0);
			// 获取总行数
			int rowNum = sheet.getRows();
			int colNum = sheet.getColumns();
			for (int r = 1; r < rowNum; r++) {
				String str = "(";
				for (int c = 0; c < colNum; c++) {
					if (sheet.getCell(c, r).getType() == CellType.NUMBER) {
						str += ((NumberCell) sheet.getCell(c, r)).getValue() + " ";
					} else if (sheet.getCell(c, r).getType() == CellType.DATE) {
						str += "'" + ((DateCell) sheet.getCell(c, r)).getDate() + "'";
					} else {
						str += "'" + sheet.getCell(c, r).getContents() + "'";
					}
					if (c < colNum - 1) {
						str += ",";
					}
				}
				str += ")";
				if (r < rowNum - 1) {
					str += ",";
				}
				sql += str;
			}
			return sql;
		} catch (Exception e) {
		}
		return null;
		
	} 

	// alt+shift+j
	/**
	 * 导出到excel
	 * 
	 * @param response  响应对象
	 * @param fileName  excel文件名
	 * @param sheetName  excel 中sheet的名字
	 * @param titleName  表格的标题
	 * @param cloumnNames 表格中的所有列名
	 * @param rs  要导出的数据对象
	 * @param sqlNames  对应数据库的列名
	 */
	public static void Export(HttpServletResponse response, String fileName, String sheetName, String titleName,
			String[] cloumnNames, ResultSet rs) {
		WritableWorkbook wk = null;

		try {
			OutputStream output = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment;           filename=" + fileName);
			response.setContentType("application/msexcel");
			// 创建可写入的Excel工作薄，且内容将写入到输出流，并通过输出流输出给客户端浏览
			wk = Workbook.createWorkbook(output);
			/// 创建可写入的Excel工作表
			WritableSheet sheet = wk.createSheet(sheetName, 0);
			// 把单元格（column, row）到单元格（column1, row1）进行合并。
			// mergeCells(column, row, column1, row1);
			sheet.mergeCells(0, 0, cloumnNames.length-1, 0);// 单元格合并方法
			// 创建WritableFont 字体对象，参数依次表示黑体、字号12、粗体、非斜体、不带下划线、亮蓝色
			WritableFont titleFont = new WritableFont(WritableFont.createFont("黑体"), 12, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.LIGHT_BLUE);
			// 创建WritableCellFormat对象，将该对象应用于单元格从而设置单元格的样式
			WritableCellFormat titleFormat = new WritableCellFormat();
			// 设置字体格式
			titleFormat.setFont(titleFont);
			// 设置文本水平居中对齐
			titleFormat.setAlignment(Alignment.CENTRE);
			// 设置文本垂直居中对齐
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 设置背景颜色
			titleFormat.setBackground(Colour.GRAY_25);
			// 设置自动换行
			titleFormat.setWrap(true);
			// 添加Label对象，参数依次表示在第一列，第一行，内容，使用的格式
			Label lab_00 = new Label(0, 0, titleName, titleFormat);
			// 将定义好的Label对象添加到工作表上，这样工作表的第一列第一行的内容为‘学员考试成绩一览表’并应用了titleFormat定义的样式
			sheet.addCell(lab_00);
			WritableCellFormat cloumnTitleFormat = new WritableCellFormat();
			cloumnTitleFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD, false));
			cloumnTitleFormat.setAlignment(Alignment.CENTRE);
			for (int i = 0; i < cloumnNames.length; i++) {
				Label lab_01 = new Label(i, 1, cloumnNames[i], cloumnTitleFormat);
				sheet.addCell(lab_01);
			}
			int i = 2;// 从第三行开始写入数据
			while (rs.next()) {
				for(int j=0;j<cloumnNames.length;j++){
					sheet.addCell(new Label(j, i, rs.getString(j+1)));
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wk.write();
				// 操作完成时，关闭对象，释放占用的内存空间
				wk.close();
			} catch (Exception e2) {
			}
		}

	}
}
