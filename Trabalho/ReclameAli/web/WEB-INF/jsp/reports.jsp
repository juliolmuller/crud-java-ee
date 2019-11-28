<%@taglib prefix="t" tagdir="/WEB-INF/tags" %> <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@page contentType="text/html"
pageEncoding="UTF-8" %>

<t:baseLayout>
  <%-- Cabeçalho da página --%>
  <t:header baseUrl="${baseUri}" hyperlinks="${headerLinks}" />

  <%-- Corpo da página --%>
  <main class="container c-main">
    <h1 class="mb-4">
      Relatórios
    </h1>

    <h5 style="margin:20px 8px 12px;">Gerar relatório com os dados dos usuários</h5>
    <a href="${pageContext.request.contextPath}/relatorio?action=empl" class="btn btn-outline-primary">Empregados</a><br /><br />

    <h5 style="margin:20px 8px 12px;">Gerar relatório com os três produtos com mais reclamações</h5>
    <a href="${pageContext.request.contextPath}/relatorio?action=top" class="btn btn-outline-primary">Top Reclamados</a><br /><br />

    <h5 style="margin:20px 8px 12px;">Gerar relatório dos tickets de todos os tickets de reclamação</h5>
    <form action="${pageContext.request.contextPath}/relatorio?action=recl" method="POST">
      <label>Selecione o status do ticket de reclamação</label><br /> <input type="radio" name="tStatus" value="todos" />Todos<br />
      <input type="radio" name="tStatus" value="abertos" />Abertos<br /> <input type="radio" name="tStatus" value="fechados" />Fechados<br />
      <button type="submit" class="btn btn-outline-primary">Ir</button>
    </form>

    <h5 style="margin:20px 8px 12px;">Gerar relatório de todos os tipos de tickets</h5>
    <form action="${pageContext.request.contextPath}/relatorio?action=tickts" method="POST">
      <label for="dataIni">Selecione a data de abertura inicial</label><br /> <input type="date" name="dataIni" id="dataIni" /><br />
      <label for="dataFim">Selecione a data de abertura final</label><br /> <input type="date" name="dataFim" id="dataFim" /><br />
      <button type="submit" class="btn btn-outline-primary">Ir</button>
    </form>
  </main>

  <%-- Rodapé da página --%>
  <t:footer />

</t:baseLayout>
