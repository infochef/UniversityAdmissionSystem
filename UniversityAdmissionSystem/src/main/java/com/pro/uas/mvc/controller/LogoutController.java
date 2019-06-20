package com.pro.uas.mvc.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.pro.uas.service.UniversityServiceJPAImpl;

@Controller
public class LogoutController {
	
	@Autowired
	UniversityServiceJPAImpl ser;
	
	@GetMapping("/logout")
	public String getLogout(HttpServletRequest req,HttpServletResponse resp)
	{
		HttpSession session=req.getSession(false);
		if(session!=null)
		{
			session.invalidate();
			Cookie []cookie = req.getCookies();
			if(cookie!=null)
			{
				for(Cookie cookie1:cookie)
				{
					String name = cookie1.getName();
					if(name.equalsIgnoreCase("JSESSIONID"))
					{
						cookie1.setMaxAge(0);
						resp.addCookie(cookie1);
					}
					return "Login";
				}
			}
		}
		return "Login";
	}
}



