<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Criar Projeto</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>
    <div class="container">
        <h1>Criar Projeto</h1>
        <form action="${pageContext.request.contextPath}/projects/create" method="post">
        <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" class="form-control" id="nome" name="nome" value="${project.nome}" required>
            </div>
            <div class="form-group">
                <label for="descricao">Descrição:</label>
                <textarea class="form-control" id="descricao" name="descricao" rows="3" value="${project.descricao}" required></textarea>
            </div>
            <div class="form-group">
                <label for="dataInicio">Data de Início:</label>
                <input type="date" class="form-control" id="dataInicio" name="dataInicio" value="${project.dataInicio}" required>
            </div>
            <div class="form-group-md">
                <label for="dataPrevisaoFim">Previsão de Término:</label>
                <input type="date" class="form-control" id="dataPrevisaoFim" name="dataPrevisaoFim" value="${project.dataPrevisaoFim}" required>
            </div>
            <div class="form-group">
                <label for="dataFim">Data Real de Término:</label>
                <input type="date" class="form-control" id="dataFim" name="dataFim" value="${project.dataFim}" required>
            </div>
            <div class="form-group">
                <label for="orcamento">Orçamento Total:</label>
                <input type="number" step="0.01" class="form-control" id="orcamento" name="orcamento" value="${project.orcamento}" required>
            </div>
            <div class="form-group">
                <label for="status">Status:</label>
                <select class="form-control" id="status" name="status" value="${project.status}" required>
                    <option value="">[Selecione]</option>
                    <c:forEach var="status" items="${status}">
                        <option value="${status}">${status.descricao}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="idGerente">Gerente Responsável:</label>
                <select class="form-control" id="idGerente" name="idGerente" value="${project.idGerente}" required>
                    <option value="">[Selecione]</option>
                    <c:forEach var="manager" items="${managers}">
                        <option value="${manager.id}">${manager.nome}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
            <a href="/" class="btn btn-link">Voltar</a>
        </form>
    </div>

    <!-- Bootstrap JS e dependências -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
