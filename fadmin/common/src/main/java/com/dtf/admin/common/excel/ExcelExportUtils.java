package com.dtf.admin.common.excel;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import com.dtf.admin.common.utils.MapUtils;

/**
 * excel导出功能工具类，可导出大数据量excel
 * 测试1800万数据导出，耗时742秒，12分钟左右，无出现内存爆掉的情况，但是文件太大，导致打不开
 * 测试1000万数据导出，耗时416秒，7分钟左右，无出现内存爆掉的情况，文件可以打开，但是花了好久时间
 * 测试100万数据导出，耗时43秒，无出现内存爆掉的情况，文件可以打开
 * @author 飞不语
 *
 */
public class ExcelExportUtils {
	
	public static void main(String[] args) {
		//记录开始执行构建excel时间，便于最后展示生成excel总共花费的时间
		long startTimne = System.currentTimeMillis();	
		
		ExcelExportUtils exportUtils = new ExcelExportUtils();
		//1.设置第一列的标题及，后续取数据的code
		exportUtils.setTitles(new String[]{"第一列title","第二列title","第三列title"},
				new String[]{"code1","code2","code3"});
		//2.设置文件存放位置
		exportUtils.setExcelFilePath("D:/test.xls");
		try {
			//3.生成excel文件，及一些头部信息
			exportUtils.start();
			
			List dataList = new ArrayList();
			for (int i = 0; i < 100000; i++) {
				Map m1 = new HashMap();
				m1.put("code1", "code1"+i);
				m1.put("code2", "code2"+i);
				m1.put("code3", "code3"+i);
				dataList.add(m1);
			}
			//4.多次补入数据
			for (int i = 0; i < 1; i++) {
				exportUtils.appendData(dataList);
			}
			
			//5.生成excel文件的结尾符号及关闭文件输出流
			exportUtils.end();
			System.out.println("生成数据耗时："+(System.currentTimeMillis() - startTimne));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	//一个excel最多支持多少个sheet
	private int maxSheetCount = 300;
	//单sheet最大行数，office 2003是6万行，2007支持100万行
	private int maxOneSheetCount = 60000;
	
	private String excelFilePath = null;
	
	//数据条数
	private int dataCount = 0;
	
	//所有sheet开始的第一行，标题，类似：new String[]{"第一列title","第二列title","第三列title"}
	private String[] titleNames;
	
	private String[] titleCodes;
	//表格的列数
	private int columnNum;
	
	private int nowRowCount = 1;
	private int nowSheetCount = 0;
	
	//文件输入
	private PrintWriter writer;
	
	public void setTitles(String[] titleNames,String[] titleCodes) {
		
		if (titleNames == null || titleNames.length == 0
				|| titleCodes == null || titleCodes.length == 0) {
			throw new RuntimeException("titleCodes与titleNames不能为空，并且个数不能为0！");
		}
		
		if (titleCodes.length != titleNames.length) {
			throw new RuntimeException("titleCodes.length与titleNames.length必须一致！");
		}
		
		this.titleNames = titleNames;
		this.titleCodes = titleCodes;
		this.columnNum = titleNames.length;
	}
	
	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}

	public void checkBuild(){
		if (excelFilePath == null) {
			throw new RuntimeException("请先设置输出文件路径，例如：exportUtils.setExcelFilePath(\"D:/test.xls\");");
		}
		
		if (titleNames == null || titleNames.length == 0) {
			throw new RuntimeException("请先设置excel的头部title，"
					+ "例如：exportUtils.setTitles(new String[]{\"第一列title\",\"第二列title\",\"第三列title\"});");
		}
	}
	
	/**
	 * 生成excel文件，及一些头部信息
	 * @throws Exception
	 */
	public void start() throws Exception{
		//校验参数是否正确
		checkBuild();
		
		File file = new File(excelFilePath);
		writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)));
		
		StringTemplateGroup stGroup = new StringTemplateGroup("ExcelStringTemplate");
		
		//写入excel文件头部信息
		StringTemplate head =  stGroup.getInstanceOf("com/dtf/admin/common/excel/head");
		writer.println(head.toString());
		
	}
	
	/**
	 * 持续向excel内输出行数据
	 * 注意：
	 * 		1.在调用此方法前必须调用本类的start()方法
	 * 		2.在调用此方法后必须调用本类的end()方法
	 * @param data
	 */
	public void appendData(List data){
		
		for (int i = 0; i < data.size(); i++) {
			dataCount ++;
			nowRowCount ++;		//每添加一行数据，此值增加一次
			
			//如果当前行60001 > 60000 或者当前是第一行
			if (nowRowCount > maxOneSheetCount || nowRowCount == 2) {
				nowSheetCount ++;
				StringTemplate sheetHead = new StringTemplate("<Worksheet ss:Name=\"$sheetName$\">"+
				  "<Table ss:ExpandedColumnCount=\"$columnNum$\" ss:ExpandedRowCount=\"$rowNum$\" x:FullColumns=\"1\""+
				   " x:FullRows=\"1\" ss:DefaultColumnWidth=\"54\" ss:DefaultRowHeight=\"14.25\">");
				sheetHead.setAttribute("sheetName", nowSheetCount);		//sheet名称
				sheetHead.setAttribute("columnNum", columnNum);		//有多少列
				sheetHead.setAttribute("rowNum", maxOneSheetCount);			//有多少行
				writer.println(sheetHead.toString());
				
				//生成标题开始
				writer.print("<Row ss:AutoFitHeight=\"0\" ss:Height=\"22\">");
				for (String title : titleNames) {
					writer.print("<Cell ss:StyleID=\"s77\"><Data ss:Type=\"String\">"+title+"</Data></Cell>");
				}
			    writer.print("</Row>");
				//生成标题结束
			}
			
			StringBuffer sb = new StringBuffer("<Row ss:AutoFitHeight=\"0\">");
			Object obj = data.get(i);
			Map map = null;
			if (obj instanceof Map) {
				map = (Map) obj;
			}else{
				map = MapUtils.beanToMap(obj);
			}
			for (int j = 0; j < titleCodes.length; j++) {
				sb.append("<Cell><Data ss:Type=\"String\">$"+titleCodes[j]+"$</Data></Cell>");
			}
			sb.append("</Row>");
			StringTemplate sheetHead = new StringTemplate(sb.toString());
			
			sheetHead.setAttributes(map);
			
			writer.print(sheetHead.toString());
			
			
			if (nowRowCount == maxOneSheetCount) {
				nowRowCount = 1;
				writer.print("</Table></Worksheet>");
			}
		}
	}
	
	/**
	 * 生成excel文件的结尾符号及关闭文件输出流
	 */
	public void end(){
		
		if (nowRowCount < maxOneSheetCount && dataCount != 0) {
			writer.print("</Table></Worksheet>");
		}
		
		writer.print("</Workbook>");
		writer.flush();
		writer.close();
	}
	
}
