﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE><s:property value="#saleVisit==null?'添加':'修改'" />拜访记录</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/layer/mobile/need/layer.css" type=text/css rel=stylesheet>	
<script type="text/javascript" src="${pageContext.request.contextPath }/js/my.js"></script>
<!-- 使用日期控件步骤1: 导入js和css -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/datepicker/jquery.datepick.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/datepicker/jquery.datepick.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/datepicker/jquery.datepick-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/layer/layer.js"></script>
<script type="text/javascript">
	
<!-- 使用日期控件步骤2: 当页面加载完成时,调用 datepick方法指定需要应用的文本框 -->
	$(document).ready(function(){
			// yy 已经代表了4位的年份
			// mm 代表月份
			// dd 代表日期
		$('#visit_time').datepick({dateFormat: 'yy-mm-dd'}); 
		$('#visit_nexttime').datepick({dateFormat: 'yy-mm-dd'});
	});
	
	function selectCust(){
		layer.open({
		  type: 2,
		  skin: 'layui-layer-rim', //加上边框
		  area: ['700px', '500px'], //宽高
		  content: '${pageContext.request.contextPath}/CustomerAction_getCustomerList?select=true'
		});
		
	};
	function confim(elementId,msg) {
		if($(elementId).val()=="" || $(elementId).val().trim() == "") {
			layer.tips( msg , elementId);
			return 0;
		}
	}
	function checkData() {
		if(confim('#cust_name','请选择所属客户！')==0) {
			return false;
		}
		if(confim('#visit_time','拜访时间不能为空！')==0) {
			return false;
		}
		if(confim('#visit_interviewee','被拜访人不能为空！')==0) {
			return false;
		}
		if(confim('#visit_addr','拜访地址不能为空！')==0) {
			return false;
		}
		if(confim('#visit_datail','拜访详情不能为空！')==0) {
			return false;
		}
		if(confim('#visit_nexttime','下次拜访时间不能为空！')==0) {
			return false;
		}

	}
	
	
</script>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1
		action="${pageContext.request.contextPath }/visit_updateVisit.action"
		method="post"  >
		<!-- 隐藏域回显当前编辑的拜访记录id -->
		<input  type="hidden"  name="vid" value="${visit.vid }"  />

		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：拜访记录管理 &gt; <s:property value="#saleVisit==null?'添加':'修改'" />拜访记录</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
						  
						    
							<TR>
								<td>所属用户：</td>
								<td>
									<select name="user.uid">
										<c:forEach items="${listUser}" var="user">
											<option value="${user.uid }" <c:if test="${user.uid==visit.user.uid }">selected</c:if>>${user.username } </option>
										</c:forEach>
									</select>
								</td>
								<td>所属客户 ：</td>
								<td >
									<select name="customer.cid">
										<c:forEach items="${listCustomer}" var="customer">
											<option value="${customer.cid }" <c:if test="${customer.cid==visit.customer.cid }">selected</c:if>>${customer.custName } </option>
										</c:forEach>
									</select>
								</td>
							</TR>
							<TR>
								<td>拜访地址：</td>
								<td>
								<INPUT class=textbox id="visit_addr" style="WIDTH: 180px" maxLength=50 name="vaddress" value="${visit.vaddress }"  />
								</td>
								<td>拜访内容：</td>
								<td>
								<INPUT class=textbox id="visit_addr" style="WIDTH: 180px" maxLength=50 name="vcontent" value="${visit.vcontent }" />
								</td>
							</TR>
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type="submit" onclick=""
														value="保存 " name=sButton2>
								</td>
							</tr>
							
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
