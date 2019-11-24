<%@tag import="java.time.format.DateTimeFormatter"%>
<%@tag import="java.time.LocalDateTime"%>
<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="value" required="true" rtexprvalue="true" type="java.time.LocalDateTime" %>
<%@attribute name="pattern" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    request.setAttribute("date", value.format(formatter));
%>
<c:out value="${date}" />