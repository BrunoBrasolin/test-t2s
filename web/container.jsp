<%-- 
    Document   : container
    Created on : Aug 4, 2021, 5:39:14 PM
    Author     : bruno
--%>

<%@page import="br.com.t2s.Container"%>
<%@include file="WEB-INF/jspf/header.jspf" %>


<%
    if (request.getParameter("container") != null) {
        String container = request.getParameter("container");
        String client = request.getParameter("client");
        int type = Integer.parseInt(request.getParameter("type"));
        int status = Integer.parseInt(request.getParameter("status"));
        int category = Integer.parseInt(request.getParameter("category"));
        
        Container.insert(container, client, type, status, category);
    }
    
    if (request.getParameter("delete_container") != null) {
        Container.delete(request.getParameter("delete_container"));
    }
%>


<main class="ui container">
    <h2>Listagem de Container</h2>
    <table class="ui celled table">
        <thead>
        <th>Container</th>
        <th>Cliente</th>
        <th>Tipo</th>
        <th>Status</th>
        <th>Categoria</th>
        <th>Deletar</th>
        </thead>

        <tbody>
            <% for (Container c : Container.getList()) {%>
            <tr>
                <td><%= c.getContainer()%></td>
                <td><%= c.getClient()%></td>
                <td><%= c.getType() == 0 ? "20" : "40"%></td>
                <td><%= c.getStatus() == 0 ? "Vazio" : "Cheio"%></td>
                <td><%= c.getCategory() == 0 ? "Importação" : "Exportação"%></td>
                <td>
                    <form method="POST">
                        <input type="hidden" value="<%= c.getContainer()%>" name="delete_container" />
                        <button class="ui button basic negative" type="submit">Deletar</button>
                            
                    </form>
                </td>
            </tr>
            <%}%>
        </tbody>
    </table>

    <hr>

    <h2>Cadastrar novo Container</h2>
    <form class="ui form" method="POST">
        <div class="field">
            <label for="container">Número do container</label>
            <input type="text" name="container" id="container" pattern="[A-Z]{4}[0-9]{7}" required />
        </div>
        <div class="field">
            <label for="client">Nome do cliente</label>
            <input type="text" name="client" id="client" required />
        </div>
        <div class="field">
            <label for="type">Tipo</label>
            <select name="type" id="type">
                <option value="0">20</option>
                <option value="1">40</option>
            </select>
        </div>
        <div class="field">
            <label for="status">Status</label>
            <select name="status" id="status">
                <option value="0">Vazio</option>
                <option value="1">Cheio</option>
            </select>
        </div>
        <div class="field">
            <label for="category">Categoria</label>
            <select name="category" id="category">
                <option value="0">Importação</option>
                <option value="1">Exportação</option>
            </select>
        </div>

        <button class="ui button primary" type="submit">Cadastrar</button>

    </form>
</main>

<%@include file="WEB-INF/jspf/footer.jspf" %>
