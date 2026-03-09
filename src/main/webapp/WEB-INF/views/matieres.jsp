<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Matières</title>
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
        .table-container {
            background: #fff;
            border: 1px solid #e9ecef;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th {
            background: #f8f9fa;
            padding: 12px 16px;
            text-align: left;
            font-weight: 600;
            color: #333;
            border-bottom: 1px solid #e9ecef;
            font-size: 14px;
        }
        td {
            padding: 16px;
            border-bottom: 1px solid #e9ecef;
            font-size: 14px;
        }
        tr:last-child td {
            border-bottom: none;
        }
        tr:hover {
            background: #f8f9fa;
        }
        .badge {
            background: #e9ecef;
            color: #333;
            padding: 4px 8px;
            border-radius: 4px;
            font-size: 12px;
            font-weight: 500;
        }
        .btn-light {
            background: rgba(255,255,255,0.9);
            border: none;
            border-radius: 25px;
            padding: 0.5rem 2rem;
        }
        .empty-message {
            text-align: center;
            padding: 60px 20px;
            color: #666;
        }
        .empty-message i {
            font-size: 48px;
            color: #ccc;
            display: block;
            margin-bottom: 16px;
        }
        .empty-message h3 {
            color: #333;
            margin-bottom: 8px;
        }
        .alert {
            border-radius: 8px;
            padding: 12px;
            margin-bottom: 20px;
        }
        .alert-success {
            background: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .alert-danger {
            background: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
        @media (max-width: 768px) {
            .page-header {
                flex-direction: column;
                gap: 16px;
                align-items: flex-start;
            }
            .table-container {
                overflow-x: auto;
            }
        }
    </style>
</head>
<body>
    <div class="container main-container">
        <div class="page-header">
            <h1>
                <i class="fas fa-book"></i>
                Gestion des Matières
            </h1>
            <div class="breadcrumb">
                <a href="<c:url value='/'/>">Accueil</a> / Matières
            </div>
        </div>

        <c:if test="${not empty success}">
            <div class="alert alert-success">
                <i class="fas fa-check-circle"></i> ${success}
            </div>
        </c:if>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                <i class="fas fa-exclamation-circle"></i> ${error}
            </div>
        </c:if>

        <div class="table-container">
            <c:choose>
                <c:when test="${not empty matieres}">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nom</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="matiere" items="${matieres}">
                                <tr>
                                    <td><span class="badge">#${matiere.idMatiere}</span></td>
                                    <td>${matiere.nom}</td>
                                    <td>
                                        <a href="<c:url value='/matieres/edit/${matiere.idMatiere}'/>" class="btn btn-sm btn-warning me-1">
                                            <i class="fas fa-edit"></i>
                                        </a>
                                        <a href="<c:url value='/matieres/delete/${matiere.idMatiere}'/>" class="btn btn-sm btn-danger" 
                                           onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette matière?')">
                                            <i class="fas fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="empty-message">
                        <i class="fas fa-book"></i>
                        <h3>Aucune matière</h3>
                        <p>Aucune matière trouvée pour le moment.</p>
                        <a href="<c:url value='/matieres/add'/>" class="btn btn-primary">
                            <i class="fas fa-plus"></i> Ajouter une matière
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        
        <div class="text-center mt-4">
            <a href="<c:url value='/matieres/add'/>" class="btn btn-primary">
                <i class="fas fa-plus"></i> Ajouter une matière
            </a>
            <a href="<c:url value='/'/>" class="btn btn-light ms-2">
                <i class="fas fa-home"></i> Retour à l'accueil
            </a>
        </div>
    </div>
</body>
</html>
