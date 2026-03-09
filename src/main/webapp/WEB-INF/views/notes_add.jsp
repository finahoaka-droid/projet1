<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter une Note</title>
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
        .alert-success {
            background: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
            border-radius: 8px;
            padding: 12px;
            margin-bottom: 20px;
        }
        .alert-danger {
            background: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
            border-radius: 8px;
            padding: 12px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container main-container">
        <div class="page-header">
            <h1>
                <i class="fas fa-chart-line"></i>
                Ajouter une Note
            </h1>
            <div class="breadcrumb">
                <a href="<c:url value='/'/>">Accueil</a> / 
                <a href="<c:url value='/notes'/>">Notes</a> / 
                Ajouter
            </div>
        </div>

        <div class="form-container">
            <c:if test="${not empty success}">
                <div class="alert-success">
                    <i class="fas fa-check-circle"></i> ${success}
                </div>
            </c:if>
            
            <c:if test="${not empty error}">
                <div class="alert-danger">
                    <i class="fas fa-exclamation-circle"></i> ${error}
                </div>
            </c:if>

            <form action="<c:url value='/notes/save'/>" method="post">
                <div class="form-group">
                    <label for="idCandidat" class="form-label">
                        <i class="fas fa-user"></i> ID Candidat
                    </label>
                    <input type="number" class="form-control" id="idCandidat" name="idCandidat" required min="1">
                    <small class="text-muted">L'identifiant du candidat</small>
                </div>

                <div class="form-group">
                    <label for="idMatiere" class="form-label">
                        <i class="fas fa-book"></i> ID Matière
                    </label>
                    <input type="number" class="form-control" id="idMatiere" name="idMatiere" required min="1">
                    <small class="text-muted">L'identifiant de la matière</small>
                </div>

                <div class="form-group">
                    <label for="idCorrecteur" class="form-label">
                        <i class="fas fa-chalkboard-teacher"></i> ID Correcteur
                    </label>
                    <input type="number" class="form-control" id="idCorrecteur" name="idCorrecteur" required min="1">
                    <small class="text-muted">L'identifiant du correcteur</small>
                </div>

                <div class="form-group">
                    <label for="note" class="form-label">
                        <i class="fas fa-star"></i> Note
                    </label>
                    <input type="number" class="form-control" id="note" name="note" required 
                           step="0.01" min="0" max="20" placeholder="ex: 15.50">
                    <small class="text-muted">La note (entre 0 et 20, avec 2 décimales maximum)</small>
                </div>

                <div class="d-flex justify-content-between">
                    <a href="<c:url value='/notes'/>" class="btn btn-secondary">
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
