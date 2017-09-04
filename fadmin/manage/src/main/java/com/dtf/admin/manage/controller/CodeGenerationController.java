package com.dtf.admin.manage.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dtf.admin.common.utils.CodeGenerationUtils;
import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.manage.service.CodeGenerationBo;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/CodeGeneration")
public class CodeGenerationController {
	
	Log log = LogFactory.getLog(CodeGenerationController.class);
	
	@Autowired
	private CodeGenerationBo codeGenerationBo;
	
	@ResponseBody
	@RequestMapping(value="/queryAllTable")
	public PageInfo queryAllTable(@RequestParam Map<String, Object> param) {
		return codeGenerationBo.queryAllTable(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/codeGeneration")
	public Map codeGeneration(@RequestParam Map<String, Object> param) {
		Map req = new HashMap();
		List list = codeGenerationBo.codeGeneration(param);
		String msg = "";
		try {
			String voTemplatePath = "\\common\\src\\main\\java\\com\\dtf\\admin\\common\\codeGeneration\\VO.template";
			if ("true".equals(MapUtils.getString(param, "generate_vo"))) {
				CodeGenerationUtils.generationVO(
					MapUtils.getString(param, "code_path")+voTemplatePath, 
					MapUtils.getString(param, "vo_path"), 
					list,
					MapUtils.getString(param, "table_name")
				);
				msg += "VO生成成功，见："+MapUtils.getString(param, "vo_path")+"\br";
			}
		} catch (Exception e) {
			msg += "VO生成失败，Exception："+e.getLocalizedMessage()+"\n";
			e.printStackTrace();
		}
		
		try {
			String voTemplatePath = "\\common\\src\\main\\java\\com\\dtf\\admin\\common\\codeGeneration\\VO.template";
			if ("true".equals(MapUtils.getString(param, "generate_vo"))) {
				CodeGenerationUtils.generationVO(
					MapUtils.getString(param, "code_path")+voTemplatePath, 
					MapUtils.getString(param, "vo_path"), 
					list,
					MapUtils.getString(param, "table_name")
				);
				msg += "VO生成成功，见："+MapUtils.getString(param, "vo_path")+"\br";
			}
		} catch (Exception e) {
			msg += "VO生成失败，Exception："+e.getLocalizedMessage()+"\n";
			e.printStackTrace();
		}
		
		
		req.put("msg", msg);
		return req;
	}
	
}
