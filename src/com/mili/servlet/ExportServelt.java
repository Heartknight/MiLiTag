package com.mili.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import com.mili.domain.Lable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.mili.util.C3P0Utils;


/**
 * Servlet implementation class ExportServelt
 */
@WebServlet("/ExportServelt")
public class ExportServelt extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WritableWorkbook wk =null;
		try {
			OutputStream output = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment;           filename=subject.xls");
			response.setContentType("application/msexcel");
			// 创建可写入的Excel工作薄，且内容将写入到输出流，并通过输出流输出给客户端浏览
			wk = Workbook.createWorkbook(output);
			/// 创建可写入的Excel工作表
			WritableSheet sheet = wk.createSheet("科目信息", 0);
			// 把单元格（column, row）到单元格（column1, row1）进行合并。
			// mergeCells(column, row, column1, row1);
			sheet.mergeCells(0, 0, 2, 0);// 单元格合并方法
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
			Label lab_00 = new Label(0, 0, "米砾标签", titleFormat);
			// 将定义好的Label对象添加到工作表上，这样工作表的第一列第一行的内容为‘学员考试成绩一览表’并应用了titleFormat定义的样式
			sheet.addCell(lab_00);
			WritableCellFormat cloumnTitleFormat = new WritableCellFormat();
			cloumnTitleFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD, false));
			cloumnTitleFormat.setAlignment(Alignment.CENTRE);
			
			//-------------------需要修改
			Label lab_01 = new Label(0, 1, "标签标题", cloumnTitleFormat);
			Label lab_02 = new Label(1, 1, "标签内容", cloumnTitleFormat);
			Label lab_03 = new Label(2, 1, "标记时间", cloumnTitleFormat);

			sheet.addCell(lab_01);
			sheet.addCell(lab_02);
			sheet.addCell(lab_03);
			
			// 1.获取核心类queryRunner
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			
			// 2.编写sql语句
			String sql = "select * from lable";
		// 3.执行查询操作
			List<Lable> lables = qr.query(sql, new BeanListHandler<Lable>(Lable.class));
			// 4.对结果集集合进行遍历
			
			
		int i = 2;	
	for(Lable lable :lables)
	{
		sheet.addCell(new Label(0, i, lable.getLtitle()));
		sheet.addCell(new Label(1, i, lable.getlContent()));
		sheet.addCell(new Label(2, i, lable.getLtime().toString()));
		i++;
	}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// 将定义的工作表输出到之前指定的介质中（这里是客户端浏览器）
				wk.write();
				// 操作完成时，关闭对象，释放占用的内存空间
				wk.close();
			} catch (Exception e2) {
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}



