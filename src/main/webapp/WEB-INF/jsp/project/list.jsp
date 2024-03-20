<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar projetos</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <style>
        .actions-column {
            display: flex;
            align-items: center;
        }
        .actions-column a {
            margin-right: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Projetos</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Data de Início</th>
            <th>Previsão de Término</th>
            <th>Data Real de Término</th>
            <th>Orçamento Total</th>
            <th>Status</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="project" items="${projects}">
            <tr>
                <td>${project.id}</td>
                <td>${project.nome}</td>
                <td>${project.descricao}</td>
                <td>${project.dataInicio}</td>
                <td>${project.dataPrevisaoFim}</td>
                <td>${project.dataFim}</td>
                <td>${project.orcamento}</td>
                <td>${project.status}</td>
                <td class="actions-column">
                    <a href="projects/upsert?id=${project.id}">
                        <img src="https://cdnjs.cloudflare.com/ajax/libs/octicons/8.5.0/svg/pencil.svg" alt="Pencil" width="20" height="20" style="margin-right: 5px;">
                    </a>
                    <a href="#">
                        <img src="https://cdnjs.cloudflare.com/ajax/libs/octicons/8.5.0/svg/trashcan.svg" alt="Trash" width="20" height="20" style="margin-right: 5px;">
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="/" class="btn btn-link">Voltar</a>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
