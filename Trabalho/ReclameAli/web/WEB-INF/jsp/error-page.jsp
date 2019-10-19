<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>

<t:baseLayout>
  <div class="container">
    <h1>Falhou, bicho!</h1>
    <h4>${exception == null}</h4>
    <h4>${exception.message}</h4>
  </div>
</t:baseLayout>
