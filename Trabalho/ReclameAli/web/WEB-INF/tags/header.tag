<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="baseUrl" required="false" rtexprvalue="true" %>
<%@attribute name="hyperlinks" required="false" rtexprvalue="true" type="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="container-fluid bg-dark fixed-top c-header">
  <nav class="navbar navbar-expand-lg navbar-dark" role="navigation">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
      <img src="${pageContext.request.contextPath}/img/reclame-ali-white.png" width="30" height="30" class="d-inline-block align-top" alt="Logo do sistema" />
      <span class="text-white-50 h4 c-title">Reclame Ali</span>
    </a>
    <div class="container">
      <ul class="navbar-nav text-white">
        <c:forEach var="h" items="${hyperlinks}">
          <li class="nav-item">
            <a href="${baseUrl}${h.url}" class="nav-link ${h.active ? "active" : ""}">
              <c:out value="${h.label}" />
            </a>
          </li>
        </c:forEach>
      </ul>
    </div>
    <div class="form-inline">
      <a href="${pageContext.request.contextPath}/" class="btn btn-sm btn-outline-danger text-white my-2 my-sm-0">
        <i class="fas fa-door-open"></i>
        Sair
      </a>
    </div>
  </nav>
</header>
