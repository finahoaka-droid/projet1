<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un Candidat</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .main-container {
            padding: 2rem;
        }
        .page-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 40px;
        }
        h1 {
            font-size: 28px;
            font-weight: 600;
            color: white;
            margin: 0;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.3);
        }
        .breadcrumb {
            font-size: 14px;
            color: #666;
        }
        .breadcrumb a {
            color: #666;
            text-decoration: none;
        }
        .breadcrumb a:hover {
            text-decoration: underline;
        }
        .form-container {
            background: white;
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            max-width: 600px;
            margin: 0 auto;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-label {
            font-weight: 600;
            color: #333;
            margin-bottom: 8px;
        }
        .form-control {
            border-radius: 8px;
            border: 1px solid #ddd;
            padding: 12px;
            font-size: 14px;
        }
        .form-control:focus {
            border-color: #667eea;
            box-shadow: 0 0 0 0.2rem rgba(102, 126, 234, 0.25);
        }
        .btn-primary {
            background: linear-gradient(45deg, #667eea, #764ba2);
            border: none;
            border-radius: 25px;
            padding: 12px 30px;
            font-weight: 600;
            transition: all 0.3s ease;
        }
        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
        .btn-secondary {
            background: rgba(255,255,255,0.9);
            border: none;
            border-radius: 25px;
            padding: 12px 30px;
            font-weight: 600;
            color: #333;
        }
        .error-message {
            color: #dc3545;
            font-size: 14px;
            margin-top: 5px;
        }
        .success-message {
            color: #28a745;
            font-size: 14px;
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <div class="container main-container">
        <div class="page-header">
            <h1>
                <i class="fas fa-users"></i>
                Ajouter un Candidat
            </h1>
            <div class="breadcrumb">
                <a href="<c:url value='/'/>">Accueil</a> / 
                <a href="<c:url value='/candidats'/>">Candidats</a> / 
                Ajouter
            </div>
        </div>

        <div class="form-container">
            <form action="<c:url value='/candidats/save'/>" method="post">
                <div class="form-group">
                    <label for="nom" class="form-label">
                        <i class="fas fa-user"></i> Nom
                    </label>
                    <input type="text" class="form-control" id="nom" name="nom" required>
                    <small class="text-muted">Le nom du candidat</small>
                </div>

                <div class="form-group">
                    <label for="prenom" class="form-label">
                        <i class="fas fa-user"></i> Prénom
                    </label>
                    <input type="text" class="form-control" id="prenom" name="prenom" required>
                    <small class="text-muted">Le prénom du candidat</small>
                </div>

                <div class="d-flex justify-content-between">
                    <a href="<c:url value='/candidats'/>" class="btn btn-secondary">
                        <i class="fas fa-arrow-left"></i> Annuler
                    </a>
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-save"></i> Enregistrer
                    </button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
