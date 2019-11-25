package com.qdtj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qdtj.domain.Investment;
import com.qdtj.service.impl.InvestmentServiceImpl;
import com.qdtj.util.ExcelUtil;

/**
 * 导出Excel
 * 
 * @author caiang
 *
 */
@Controller
public class DownloadActController {

	@Resource
	InvestmentServiceImpl investmentServiceImpl;
	
	private static Logger logger = Logger.getLogger(DownloadActController.class);

	@RequestMapping("/downExcel.do")
	public String download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = "excel文件";
		// 填充objects数据
		List<Object> objects = createData(request);
		List<Map<String, Object>> list = createExcelRecord(request,objects);
		String columnNames[] = new String( request.getParameter("columnNames").getBytes("iso-8859-1"), "UTF-8").split(",");// 列名
		String keys[] = request.getParameter("keys").split(",");// map中的key
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Object> createData(HttpServletRequest request) {
		//从session中拿出上次搜索的用户名,手机号码,投资标的,投资金额,投资时间的集合
		List objectList = (List)request.getSession().getAttribute(request.getParameter("page"));
		return objectList;
	}

	private List<Map<String, Object>> createExcelRecord(HttpServletRequest request,List<Object> objects) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "sheet1");
		listmap.add(map);
		//投资统计
		Investment investment = null;
		if(request.getParameter("page").equals("investmentList")){
			for (int j = 0; j < objects.size(); j++) {
				investment = (Investment)objects.get(j);
				Map<String, Object> mapValue = new HashMap<String, Object>();
				mapValue.put("userName", investment.getUserName());
				mapValue.put("phone", investment.getPhone());
				mapValue.put("invBidType", investment.getInvBidType());
				mapValue.put("invAmount", investment.getInvAmount());
				mapValue.put("invDate", investment.getInvDate());
				listmap.add(mapValue);
			}
		}
		return listmap;
	}

}
