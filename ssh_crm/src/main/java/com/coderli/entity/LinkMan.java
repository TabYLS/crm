package com.coderli.entity;

public class LinkMan {
	private Integer linkid;
	private String lkmName;
	private String lkmGender;
	private String lkmPhone;
	private String lkmMobile;

	public LinkMan() {
		// TODO Auto-generated constructor stub
	}

	// 所属的客户
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getLinkid() {
		return linkid;
	}

	public void setLinkid(Integer linkid) {
		this.linkid = linkid;
	}

	public String getLkmName() {
		return lkmName;
	}

	public void setLkmName(String lkmName) {
		this.lkmName = lkmName;
	}

	public String getLkmGender() {
		return lkmGender;
	}

	public void setLkmGender(String lkmGender) {
		this.lkmGender = lkmGender;
	}

	public String getLkmPhone() {
		return lkmPhone;
	}

	public void setLkmPhone(String lkmPhone) {
		this.lkmPhone = lkmPhone;
	}

	public String getLkmMobile() {
		return lkmMobile;
	}

	public void setLkmMobile(String lkmMobile) {
		this.lkmMobile = lkmMobile;
	}

	@Override
	public String toString() {
		return "LinkMan [linkid=" + linkid + ", lkmName=" + lkmName + ", lkmGender=" + lkmGender + ", lkmPhone="
				+ lkmPhone + ", lkmMobile=" + lkmMobile + "]";
	}

}
