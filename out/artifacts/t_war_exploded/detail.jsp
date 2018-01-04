<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/9
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
this is detail page。

<script>
    var arr = "<%=request.getCookies()[3].getComment()%>";
    console.log(arr);
    console.log(document.cookie);
</script>
</body>
</html>
