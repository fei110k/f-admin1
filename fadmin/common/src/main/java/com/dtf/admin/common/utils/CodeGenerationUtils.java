package com.dtf.admin.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerationUtils {
	
	public static boolean generationVO(String templatePath,String voPath,List list,String table_ame) throws Exception{
		File tFile = new File(templatePath);
		
		String templateStr = readeFile(tFile);
		String VOName = StringUtil.captureName(StringUtil.underlineToCamel(table_ame));
		templateStr = templateStr.replace("${VOName}",VOName);
		//组装private私有属性-----------------------------
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			Map m = (Map) list.get(i);
			String column_name = MapUtils.getString(m, "column_name");
			String column_comment = MapUtils.getString(m, "column_comment");
			String data_type = MapUtils.getString(m, "data_type");
			
			column_name = column_name.toLowerCase();
			
			if (StringUtil.isNotEmpty(column_comment)) {
				sb.append("\t//"+column_comment+"\n");
			}
			sb.append("\tprivate String "+column_name+";\n");
		}
		templateStr = templateStr.replace("${privateStr}", sb.toString());
		
		//组装public get set方法-----------------------------
		sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			Map m = (Map) list.get(i);
			String column_name = MapUtils.getString(m, "column_name");
			String column_comment = MapUtils.getString(m, "column_comment");
			String data_type = MapUtils.getString(m, "data_type");
			
			String dataType = "String";
			column_name = column_name.toLowerCase();
			
			sb.append("\tpublic "+dataType+" get"+StringUtil.captureName(column_name)+"(){\n");
			sb.append("\t\treturn "+column_name+";\n");
			sb.append("\t}\n\n");
			
			sb.append("\tpublic void set"+StringUtil.captureName(column_name)+"("+dataType+" "+column_name+"){\n");
			sb.append("\t\tthis."+column_name+" = "+column_name+";\n");
			sb.append("\t}\n\n");
		}
		
		templateStr = templateStr.replace("${privateMethod}", sb.toString());
		
		writeTxtFile(templateStr, new File(voPath+"\\"+VOName+".java"));
		
		return true;
	}
	
	
	public static void main(String[] args) throws Exception {
//		List list = new ArrayList();
//		
//		Map m1 = new HashMap();
//		m1.put("column_name", "user_id");
//		m1.put("column_comment", "用户ID");
//		m1.put("data_type", "varchar");
//		list.add(m1);
//		
//		Map m2 = new HashMap();
//		m2.put("column_name", "age_nbr");
//		m2.put("column_comment", "年纪");
//		m2.put("data_type", "int");
//		list.add(m2);
//		
//		Map m3 = new HashMap();
//		m3.put("column_name", "age");
//		m3.put("column_comment", "年纪1");
//		m3.put("data_type", "int");
//		list.add(m3);
//		
//		generationVO("E:\\workspaces\\fadmin\\fadmin\\common\\src\\main\\java\\com\\dtf\\admin\\common\\codeGeneration\\VO.template", 
//				"E:\\workspaces\\fadmin\\fadmin\\common\\src\\main\\java\\com\\dtf\\admin\\manage\\vo\\", list,"sys_staff2");
		
	}
	
	/**
	 * 创建文件
	 * @param fileName
	 * @return
	 */
	public static boolean createFile(File fileName) throws Exception {
		boolean flag = false;
		try {
			if (!fileName.exists()) {
				fileName.createNewFile();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 将内容写到文件中
	 * @param content
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static boolean writeTxtFile(String content, File fileName) throws Exception {
		createFile(fileName);
		RandomAccessFile mm = null;
		boolean flag = false;
		FileOutputStream o = null;
		try {
			o = new FileOutputStream(fileName);
			o.write(content.getBytes("UTF-8"));
			o.close();
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mm != null) {
				mm.close();
			}
		}
		return flag;
	}
	
	
	/**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String readeFile(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
