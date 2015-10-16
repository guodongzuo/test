<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript">
	function query(){
		document.getElementById("form1").action="<%=request.getContextPath()%>/UpdateServlet?method=query";
		document.getElementById("form1").submit();
	}
	
	function update(){
		document.getElementById("form1").action="<%=request.getContextPath()%>/UpdateServlet?method=update";
		document.getElementById("form1").submit();
	}
	
	<%if(null!=request.getAttribute("message") && !"".equals(request.getAttribute("message"))){%>
		alert("<%=request.getAttribute("message")%>");
	<%}%>
</script>
</head>
<body>
<form action="/UpdateServlet" id="form1" method="post">
定单号：<input type="text" name="order" value="<%=request.getAttribute("order")==null?"":request.getAttribute("order")%>"> 
  电话：<input type="text" name="phone" value="<%=request.getAttribute("phone")==null?"":request.getAttribute("phone")%>" readonly="readonly"> 
 名称：<input type="text" name="name" value="<%=request.getAttribute("name")==null?"":request.getAttribute("name")%>" readonly="readonly">
 金额：<input type="text" name="balance" value="<%=request.getAttribute("balance")==null?"":request.getAttribute("balance")%>">
<input type="button" value="查找" onclick="query()" />
<input type="button" value="修改" onclick="update()" />
</form>
</body>
</html>
