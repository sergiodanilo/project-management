<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error 404 - Page Not Found</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f8f9fa;
            color: #343a40;
            font-family: Arial, sans-serif;
        }
        .error-container {
            margin-top: 100px;
            text-align: center;
        }
        h1 {
            font-size: 64px;
            margin-bottom: 20px;
        }
        p {
            font-size: 18px;
            margin-bottom: 40px;
        }
        a.btn {
            margin-top: 20px;
            font-size: 18px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row error-container">
        <div class="col-md-12">
            <h1>Error 404</h1>
            <p>Desculpa, você não pode remover um projeto com status INICIADO, EM ANDAMENTO ou ENCERRADO!</p>
            <a href="/" class="btn btn-primary">Go to Homepage</a>
        </div>
    </div>
</div>
</body>
</html>
