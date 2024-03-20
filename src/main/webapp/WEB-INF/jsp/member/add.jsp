<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seleção de Funcionários</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
    <div class="container">
        <h2>Seleção de Funcionários</h2>
        <div class="row">
            <div class="col-md-6">
                <h4>Funcionários Disponíveis</h4>
                <form id="availableEmployeesForm">
                    <ul class="list-group">
                        <c:forEach var="member" items="${members}">
                            <li class="list-group-item">
                                <input type="checkbox" name="availableEmployees" value="${member.id}">
                                ${member.nome}
                            </li>
                        </c:forEach>
                    </ul>
                </form>
            </div>
        </div>
        <div class="mt-3">
            <button class="btn btn-primary" onclick="sendSelectedEmployees()">Enviar Funcionários Selecionados</button>
        </div>
        <a href="/" class="btn btn-link">Voltar</a>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script>
        function sendSelectedEmployees() {
            let selectedIds = [];
            const checkboxes = document.querySelectorAll('input[name="availableEmployees"]:checked');
            checkboxes.forEach(function(checkbox) {
                selectedIds.push(checkbox.value);
            });

            sendPatchRequest(selectedIds);
        }

        function sendPatchRequest(selectedIds) {
            const url = `/projects/${id}`;

            const requestBody = {
                membros: selectedIds
            };

            $.ajax({
                type: 'PATCH',
                url: url,
                contentType: 'application/json',
                data: JSON.stringify(requestBody),
                success: function(response) {
                    console.log('PATCH request successful');
                    // Handle success response here
                },
                error: function(xhr, status, error) {
                    console.error('Error sending PATCH request:', error);
                    // Handle error response here
                }
            });
        }
    </script>
</body>
</html>
