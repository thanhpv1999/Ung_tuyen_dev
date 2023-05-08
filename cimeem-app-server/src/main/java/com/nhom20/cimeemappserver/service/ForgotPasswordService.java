package com.nhom20.cimeemappserver.service;

import com.nhom20.cimeemappserver.entity.ForgotPassword;

public interface ForgotPasswordService {
	public void addCodeEmail(ForgotPassword forgotPassword);
	public ForgotPassword find(String id);
	public void addNewMail(ForgotPassword forgotPassword);
}
