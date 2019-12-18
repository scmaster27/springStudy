package com.example.springStudy.controller.memo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.springStudy.model.memo.dto.MemoDTO;
import com.example.springStudy.service.memo.MemoService;

@Controller
@RequestMapping("/memo/*")
public class MemoController {
	@Inject
	MemoService memoService;
	
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		List<MemoDTO> items = memoService.list();
		mav.setViewName("memo/memo_list");
		mav.addObject("list", items);
		return mav;
	}
}
