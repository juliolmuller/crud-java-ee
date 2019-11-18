<%@tag import="br.com.beibe.utils.Converter"%>
<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="value" required="true" rtexprvalue="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    if (value instanceof java.lang.String) {
        request.setAttribute("cpf", br.com.beibe.utils.Converter.toCpf(value));
    }
%>
<c:out value="${cpf}" />