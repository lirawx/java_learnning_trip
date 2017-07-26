package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		String msg="成功";
		//创建modelandview对象
		ModelAndView mv = new ModelAndView();
		//设置值
		mv.addObject("msg",msg);
		mv.setViewName("hello");
		return mv;
	}

}
