package com.domain;

import java.util.List;

public class IndirectData {
	//存放数据实例
	private List<String> instanceString ;
	//存放剩余数组
	private byte[] surplus;
	public List<String> getInstanceString() {
		return instanceString;
	}
	public void setInstanceString(List<String> instanceString) {
		this.instanceString = instanceString;
	}
	public byte[] getSurplus() {
		return surplus;
	}
	public void setSurplus(byte[] surplus) {
		this.surplus = surplus;
	}
	public IndirectData(List<String> instanceString,byte[] surplus) {
		this.instanceString = instanceString;
		this.surplus = surplus;
	}
	
}
