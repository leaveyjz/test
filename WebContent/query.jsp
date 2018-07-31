<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查询商品页面</title>
</head>
<body>
    
    <!-- web.jsp 应该吧数据传递给productService,但是service不能接受http请求,因此java出现了servlet -->
	<form action="<%=request.getContextPath()%>/ProductServlet" method="get">
		商品名称:<input type="text" name="keyword" /><br />
		<button type="submit">提交商品</button>
		<input type="hidden" name="type" value="query"/>
	</form>
	<table>
		<tr>
			<th>编号</th>
			<th>名称</th>
			<th>价格</th>
			<th>备注</th>
			<th>日期</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${requestScope.prolist}" var="product" varStatus="num">
			<tr>
				<td>${num.count}</td>
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td>${product.remark}</td>
				<td>${product.date}</td>
				<td>
				    <a href="<%=request.getContextPath()%>/ProductServlet?id=${product.id}&type=getById">更新</a>
				    <a href="<%=request.getContextPath()%>/ProductServlet?id=${product.id}&type=delete">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>