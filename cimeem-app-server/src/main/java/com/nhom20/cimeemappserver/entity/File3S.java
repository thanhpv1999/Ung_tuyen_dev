package com.nhom20.cimeemappserver.entity;

public class File3S {
	private String fileName;
	private String linkDownload;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLinkDownload() {
		return linkDownload;
	}
	public void setLinkDownload(String linkDownload) {
		this.linkDownload = linkDownload;
	}
	public File3S(String fileName, String linkDownload) {
		super();
		this.fileName = fileName;
		this.linkDownload = linkDownload;
	}
	public File3S() {
		super();
	}
	
	
	
	
}