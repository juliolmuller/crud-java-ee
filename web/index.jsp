<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="role" value="${userCredentials.role}" />
<c:choose>
  <c:when test="${role != null}">
    <c:redirect url="/${role}" />
  </c:when>
  <c:otherwise>
    <c:redirect url="/entrar" />
  </c:otherwise>
</c:choose>
