package com.wisely.highlight_springmvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice//声明一个控制器建言，组合了@Component注解，自动注册为Bean
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(value = Exception.class)//此处定义全局处理，通过value属性可过滤拦截条件，可以看到拦截所有的Exception
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error");//error页面
		modelAndView.addObject("errorMessage", exception.getMessage());
		return modelAndView;
	}
	
	@ModelAttribute//将键值对添加到全局，所有注解的@RequestMapping的方法可获得此键值对
	public void addAttributes(Model model) {
		model.addAttribute("msg", "额外信息");
	}
	
	@InitBinder//注解定制WebDataBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id");//忽略request参数的id,WebDataBinder配置参考其API文档
	}

}
