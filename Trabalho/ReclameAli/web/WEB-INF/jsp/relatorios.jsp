<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"  %>

<t:baseLayout>

    <%-- Cabeçalho da página --%>
    <t:header baseUrl="${baseUri}" hyperlinks="${headerLinks}" />

    <%-- Corpo da página --%>
    <main class="container c-main">
        <h1 class="mb-4">
            Bem-vindo(a), <strong><c:out value="${userCredentials.firstName}" /></strong>
        </h1>

        Gerar relatório com os dados dos usuários<br/>
        <a href="/relatorio?action=empl">Empregados</a><br/><br/>

        Gerar relatório com os três produtos com mais reclamações<br/>
        <a href="/relatorio?action=top">Top Reclamados</a><br/><br/>

        Gerar relatório dos tickets de todos os tickets de reclamação<br/>
        <form action='/relatorio?action=recl' method='POST'>
            <label>Selecione o status do ticket de reclamação</label><br/>
            <input type='radio' name='tStatus' value='todos'/>Todos<br/>
            <input type='radio' name='tStatus' value='abertos'/>Abertos<br/>
            <input type='radio' name='tStatus' value='fechados'/>Fechados<br/>
            <input type='submit' value='foi'/>
        </form><br/><br/>

        Gerar relatório de todos os tipos de tickets<br/>
        <form action='/relatorio?action=tickts' method='POST'>
            <label for='dataIni'>Selecione a data de abertura inicial</label><br/>
            <input type='date' name='dataIni' id='dataIni'/><br/>
            <label for='dataFim'>Selecione a data de abertura final</label><br/>
            <input type='date' name='dataFim' id='dataFim'/><br/>
            <input type='submit' value='foi'/>
        </form>

        <%-- Apresentar informações gerais ao usuário --%>
        <div class="mt-5">
            <div class="d-flex">
                <c:forEach var="f" items="${feed}">
                    <div class="card border border-dark c-card">
                        <div class="card-body text-center c-card-body">
                            <p class="card-text display-2 text-${f.color}">
                                ${f.icon}<strong>${f.main}</strong>
                            </p>
                            <p class="card-text h5 text-${f.color}">
                                ${f.text}
                            </p>
                        </div>
                        <a href="${baseUri}${f.link}" class="stretched-link h-0"></a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </main>

    <%-- Rodapé da página --%>
    <t:footer />

</t:baseLayout>
