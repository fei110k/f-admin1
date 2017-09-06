package com.dtf.admin.manage.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

public class SpecialCharsRequestWrapper extends HttpServletRequestWrapper {
	boolean isUpData = false;//判断是否是上传 上传忽略
	
	public SpecialCharsRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
        String contentType = servletRequest.getContentType ();
        if (null != contentType)
            isUpData =contentType.startsWith ("multipart");
	}

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values==null)  {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXSS(values[i]);
        }
        return encodedValues;
    }
    
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return stripXSS(value);
    }

    /**
     * 获取request的属性时，做xss过滤
     */
    @Override
    public Object getAttribute(String name) {
    	
        Object value = super.getAttribute(name);
        if("org.apache.catalina.core.DISPATCHER_REQUEST_PATH".equals(name)){
        	return value;
        }
        if (null != value && value instanceof String) {
            value = stripXSS((String) value);
        }
        return value;
    }

//		header中的数据，不需要过滤
//    @Override
//    public String getHeader(String name) {
//
//        String value = super.getHeader(name);
//        if (value == null)
//            return null;
//        return stripXSS(value);
//    }
	
    //spring mvc中通过@RequestParam 获取参数时，是调用的此方法
    @Override
    public Map<String, String[]> getParameterMap() {
    	Map<String, String[]> map = super.getParameterMap();
    	for (String[] values : map.values()) {  
    	    for (int i = 0; i < values.length; i++) {
    	    	values[i] = stripXSS(values[i]);
			}
    	}  
    	
        return map;
    }
    
    @Override
    public ServletInputStream getInputStream () throws IOException {
        if (isUpData){
            return super.getInputStream ();
        }else{

            final ByteArrayInputStream bais = new ByteArrayInputStream(inputHandlers(super.getInputStream ()).getBytes ());

            return new ServletInputStream() {

                @Override
                public int read() throws IOException {
                    return bais.read();
                }

                public boolean isFinished() {
                    return false;
                }

                public boolean isReady() {
                    return false;
                }

            };
        }

    }
    
    
    public String inputHandlers(ServletInputStream servletInputStream){
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader (servletInputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (servletInputStream != null) {
                try {
                    servletInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  stripXSS(sb.toString ());
    }
    
	private String stripXSS(String value) {
		if (value != null) {
//			value = HtmlUtils.htmlEscape(value);	//防止XSS攻击
			value = value.replaceAll("<", "＜").replaceAll(">", "＞");
			value = value.replaceAll("\'", "＇").replaceAll("\"", "＂");
			value = value.replaceAll("&", "＆").replaceAll(";", "；");
			value = value.replaceAll("=", "＝").replaceAll("%", "％");
//			value = value.replaceAll("\\\\", "＼").replaceAll("-", "－");
		}
		return value;
	}
}