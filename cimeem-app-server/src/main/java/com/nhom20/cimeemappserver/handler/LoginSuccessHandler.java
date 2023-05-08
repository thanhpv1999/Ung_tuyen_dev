package com.nhom20.cimeemappserver.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nhom20.cimeemappserver.entity.Users;
import com.nhom20.cimeemappserver.service.UserService;




@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	private UserService userService;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Users u= userService.getUsersByGmail(authentication.getName());
		String paymentUrl = "chua co tai khoan";
		com.google.gson.JsonObject job = new JsonObject();
		job.addProperty("message", "no-success");
		job.addProperty("data", paymentUrl);
		Gson gson = new Gson();
		response.getWriter().write(gson.toJson(job));
	}
}
