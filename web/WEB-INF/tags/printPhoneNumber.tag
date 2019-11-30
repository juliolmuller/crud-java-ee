<%@tag import="br.com.beibe.utils.Converter"%>
<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="value" required="true" rtexprvalue="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    if (value instanceof java.lang.String) {
        request.setAttribute("phone", br.com.beibe.utils.Converter.toPhone(value));
    }
%>
<c:out value="${phone}" />