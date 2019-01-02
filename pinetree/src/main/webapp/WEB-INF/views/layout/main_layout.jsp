<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<%@ include file="includetaglib.jsp" %>
<%@ include file="includejs.jsp" %>
<title>PINETREE::<decorator:title default=""/></title>
<decorator:head/>
</head>
<body>
<page:applyDecorator name="header" />
<page:applyDecorator name="search" />
<decorator:body/>
<page:applyDecorator name="footer" />
</body>
</html>