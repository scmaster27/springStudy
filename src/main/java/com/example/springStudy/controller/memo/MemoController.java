package com.example.springStudy.controller.memo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.springStudy.model.memo.dto.MemoDTO;
import com.example.springStudy.service.memo.MemoService;

@Controller
@RequestMapping("/memo/*")
public class MemoController {
	@Inject
	MemoService memoService;
	
	@RequestMapping("csvDownload.do")
	public ModelAndView csvDownload(ModelAndView mav, HttpServletRequest request, HttpServletResponse response) {
		List<MemoDTO> items = memoService.list();
		mav.setViewName("memo/memo_list");
		mav.addObject("list", items);
		
		StringBuilder sb = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		sb.append("No, 氏名, メモ, 日付");
		sb.append(newLine);
		
		for(MemoDTO item : items) {
			String idx = String.valueOf(item.getIdx());
			String writer = item.getWriter();
			String memo = item.getMemo();
			String post_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getPost_date());
			
			sb.append(idx + ", " + writer + ", " + memo + ", " + post_date);
			sb.append(newLine);
		}
		
		ClassPathResource resource = new ClassPathResource("memoList.csv");
		
		OutputStream output = null;
		try {
		    output = new FileOutputStream(resource.getFile());
		    byte[] by = sb.toString().getBytes();
		    output.write(by);
		} catch (Exception e) {
            e.getStackTrace();
		} finally {
			if(output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	    try {
	        InputStream inputStream = new FileInputStream(resource.getFile());
	        response.setHeader("Content-Disposition", "attachment; filename=" + ".memoList.csv"); 
	        FileCopyUtils.copy(inputStream, response.getOutputStream());
	        response.flushBuffer();
	        inputStream.close();
	    } catch (Exception e){
	        e.printStackTrace();
	    } 

		return mav;
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		List<MemoDTO> items = memoService.list();
		mav.setViewName("memo/memo_list");
		mav.addObject("list", items);
		return mav;
	}
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute MemoDTO dto) {
		memoService.insert(dto.getWriter(), dto.getMemo());
		return "redirect:/memo/list.do";
	}
	
	@RequestMapping("view/{idx}")
	public ModelAndView view(@PathVariable int idx, ModelAndView mav) {
		mav.setViewName("memo/view");
		mav.addObject("dto", memoService.memo_view(idx));
		return mav;
	}
	
	@RequestMapping("update/{idx}")
	public String update(@PathVariable int idx, MemoDTO dto) {
		memoService.update(dto);
		return "redirect:/memo/list.do";
	}
	
	@RequestMapping("delete/{idx}")
	public String delete(@PathVariable int idx) {
		memoService.delete(idx);
		return "redirect:/memo/list.do";
	}
}
