<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="project" value="${project}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Project Details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
    <body>
    <div class="container">
        <h1>Project Details</h1>
        <div class="row">
            <div class="col-md-6">
                <p><strong>ID:</strong> ${project.id}</p>
                <p><strong>Nome:</strong> ${project.nome}</p>
                <p><strong>Descrição:</strong> ${project.descricao}</p>
                <p><strong>Data início:</strong> ${project.dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))} </p>
                <p><strong>Data previsão fim:</strong> ${project.dataPrevisaoFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))} </p>
                <p><strong>Data fim:</strong> ${project.dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))} </p>
                <p><strong>Status:</strong> ${project.status}</p>
                <p><strong>Orçamento:</strong> ${project.orcamento}</p>
                <p><strong>Risco:</strong> ${project.risco}</p>
                <p><strong>Gerente:</strong> ${project.gerente != null ? project.gerente.nome : ""}</p>
                <p><strong>Membros:</strong></p>
                <ul>
                    <c:forEach var="membro" items="${project.membros}">
                        <li>${membro.nome}</li>
                    </c:forEach>
                </ul>
            </div>
        </div>

        <a href="/projects" class="btn btn-link">Voltar</a>
    </div>

</body>
</html>
