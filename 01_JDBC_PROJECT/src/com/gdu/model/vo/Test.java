package com.gdu.model.vo;

import java.sql.Date;

public class Test {
	private int testNo;
	private String testName;
	private Date testDate;  //java.sql.Date를 import 해야함.
	
	public Test() {}

	public Test(int testNo, String testName, Date testDate) {
		super();
		this.testNo = testNo;
		this.testName = testName;
		this.testDate = testDate;
	}

	public int getTestNo() {
		return testNo;
	}

	public void setTestNo(int testNo) {
		this.testNo = testNo;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	@Override
	public String toString() {
		return "Test [testNo=" + testNo + ", testName=" + testName + ", testDate=" + testDate + "]";
	}
	
	
}
