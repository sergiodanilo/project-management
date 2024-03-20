<%@ page import="com.group.code.projectmanagement.model.enums.ProjectRiskEnum" %>
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
        <%@ include file="../common/navigation.jsp" %>

        <h2>Projetos</h2>
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
                <th>Risco</th>
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
                    <td>${project.status.descricao}</td>
                    <td>
                         <span class="badge badge-${project.risco == ProjectRiskEnum.BAIXO ? "success" : project.risco == ProjectRiskEnum.MEDIO ? "warning" : "danger"}">
                            ${project.risco == ProjectRiskEnum.BAIXO ? "Baixo" : project.risco == ProjectRiskEnum.MEDIO ? "Médio" : "Alto"}
                        </span>
                    </td>

                    <td class="actions-column">
                        <a href="projects/view/${project.id}">
                            <img src="https://cdn-icons.flaticon.com/svg/3917/3917132.svg?token=exp=1710953540~hmac=53b08ce5e069c53f11ebd3d291690052"
                                 alt="Magnifier" width="20" height="20" style="margin-right: 5px;">
                        </a>
                        <a href="projects/upsert?id=${project.id}">
                            <img src="https://www.flaticon.com/svg/vstatic/svg/3917/3917376.svg?token=exp=1710952557~hmac=f9938c4517a4c21d5640a61b41e366b5"
                                 alt="Pencil" width="20" height="20" style="margin-right: 5px;">
                        </a>
                        <a href="projects/delete/${project.id}">
                            <img src="https://www.flaticon.com/svg/vstatic/svg/3917/3917378.svg?token=exp=1710952577~hmac=fbbd400fe490066d642f9c36d8e32a65"
                                 alt="Trash" width="20" height="20" style="margin-right: 5px;">
                        </a>
                        <a href="members/add/${project.id}">
                            <img src="https://www.flaticon.com/svg/vstatic/svg/5069/5069162.svg?token=exp=1710952558~hmac=5016da66a8dd00aafd7e8196c4b5103a"
                                 alt="People" width="20" height="20" style="margin-right: 5px;">
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div>
            <c:if test="${projects.isEmpty()}">
                <label>Sem projetos cadastrados</label>
            </c:if>
        </div>

        <a href="/" class="btn btn-link">Voltar</a>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
