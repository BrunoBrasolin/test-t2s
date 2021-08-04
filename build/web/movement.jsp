<%-- 
    Document   : container
    Created on : Aug 4, 2021, 5:39:14 PM
    Author     : bruno
--%>

<%@page import="br.com.t2s.Container"%>
<%@page import="br.com.t2s.Movement"%>
<%@include file="WEB-INF/jspf/header.jspf" %>


<%
    if (request.getParameter("container") != null) {
        String container = request.getParameter("container");
        int movement_type = Integer.parseInt(request.getParameter("type"));
        String start_date = request.getParameter("start_date");
        String end_date = request.getParameter("end_date");

        Movement.insert(container, movement_type, start_date, end_date);
    }

    if (request.getParameter("delete_movement") != null) {
        Movement.delete(Long.parseLong(request.getParameter("delete_movement")));
    }
%>


<main class="ui container">
    <h2>Listagem de Movimentações</h2>
    <table class="ui celled table">
        <thead>
        <th>Container</th>
        <th>Tipo</th>
        <th>Data de início</th>
        <th>Data de término</th>
        <th>Deletar</th>
        </thead>

        <tbody>
            <% for (Movement m : Movement.getList()) {%>
            <tr>
                <td><%= m.getContainer()%></td>
                <td><%= Movement.showMovementName(m.getMovement_type()) %></td>
                <td><%= m.getStart_date()%></td>
                <td><%= m.getEnd_date()%></td>
                <td>
                    <form method="POST">
                        <input type="hidden" value="<%= m.getRowid()%>" name="delete_movement" />
                        <button class="ui button basic negative" type="submit">Deletar</button>
                    </form>
                </td>
            </tr>
            <%}%>
        </tbody>
    </table>

    <hr>

    <h2>Cadastrar nova movimentação</h2>
    <form class="ui form" method="POST">
        <div class="field">
            <label for="container">Container</label>
            <select name="container" id="container">
                <% for (Container c : Container.getList()) {%>
                <option value="<%= c.getContainer()%>"><%= c.getContainer()%></option>
                <% }%>
            </select>
        </div>
        <div class="field">
            <label for="type">Tipo</label>
            <select name="type" id="type">
                <option value="0">Embarque</option>
                <option value="1">Desmbarque</option>
                <option value="2">Gate In</option>
                <option value="3">Gate Out</option>
                <option value="4">Posicionamento Pilha</option>
                <option value="5">Pesagem</option>
                <option value="6">Scanner</option>
            </select>
        </div>
        <div class="field">
            <label for="start_date">Data de início</label>
            <input type="date" id="start_date" name="start_date" required />
        </div>
        <div class="field">
            <label for="end_date">Data de término</label>
            <input type="date" id="end_date" name="end_date" required />
        </div>

        <button class="ui button primary" type="submit">Cadastrar</button>

    </form>
</main>

<%@include file="WEB-INF/jspf/footer.jspf" %>
