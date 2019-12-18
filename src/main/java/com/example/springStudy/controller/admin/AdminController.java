package com.example.springStudy.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	@RequestMapping("login.do")
	public String login() {
		return "admin/login";
	}
	
	@RequestMapping("login_check.do")
	public ModelAndView login_check(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
        String message = validate(id, pw);
        ModelAndView modelAndView = null;

        if (message != null) {
        	modelAndView = new ModelAndView("admin/login");
        	modelAndView.addObject("result", "error");
        	modelAndView.addObject("message", message);
            return modelAndView;
        } else {
        	String status = getUserInfo(id, pw);
        	
        	if(status.equals("9")) {
        		modelAndView = new ModelAndView("admin/login");
            	modelAndView.addObject("result", "fail");
            	modelAndView.addObject("message", "ログインに失敗しました");
            	return modelAndView;
        	} 
        }
        
        modelAndView = new ModelAndView("admin/admin");
        modelAndView.addObject("result", "success");
        modelAndView.addObject("id", id);
        modelAndView.addObject("message", "ログインに成功しました");
		
		return modelAndView;
	}
	
	private String validate(String id, String pw) {
		if (isEmpty(id)) {
			String arg = "IDを入力して下さい";
			return arg;
		}

		if (isEmpty(pw)) {
			String arg = "パスワードを入力して下さい";
			return arg;
		}
		
		return null;
	}
	
	private boolean isEmpty(String param) {
        if (param == null || param.equals("")) {
            return true;
        }
        
        return false;
	}
	
	private String getUserInfo(String id, String pw) {
		String ret = "9";
		
		if(id.equals("aaa") && pw.equals("aaa")) {
			ret = "0";
		} 
		
		return ret;
	}
}
