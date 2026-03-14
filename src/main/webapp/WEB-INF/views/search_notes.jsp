<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recherche de Notes</title>
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
        .search-container {
            background: white;
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            margin-bottom: 30px;
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
        .results-container {
            background: white;
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
        }
        .table-container {
            overflow-x: auto;
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
        .badge-primary {
            background: #007bff;
            color: white;
        }
        .badge-success {
            background: #28a745;
            color: white;
        }
        .badge-warning {
            background: #ffc107;
            color: #212529;
        }
        .badge-info {
            background: #17a2b8;
            color: white;
        }
        .note-value {
            font-weight: 600;
            color: #495057;
            font-size: 16px;
        }
        .empty-results {
            text-align: center;
            padding: 60px 20px;
            color: #666;
        }
        .empty-results i {
            font-size: 48px;
            color: #ccc;
            display: block;
            margin-bottom: 16px;
        }
        .empty-results h3 {
            color: #333;
            margin-bottom: 8px;
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
        .stats-box {
            background: #f8f9fa;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 20px;
        }
        .stats-box h6 {
            color: #495057;
            margin-bottom: 10px;
        }
        .stats-box .stat-item {
            display: flex;
            justify-content: space-between;
            margin-bottom: 5px;
        }
        .stats-box .stat-label {
            color: #6c757d;
        }
        .stats-box .stat-value {
            font-weight: 600;
            color: #333;
        }
    </style>
</head>
<body>
    <div class="container main-container">
        <div class="page-header">
            <h1>
                ETU3611
            </h1>
            <div class="breadcrumb">
                <a href="<c:url value='/'/>">Accueil</a> / Recherche de Notes
            </div>
        </div>

        <div class="search-container">
            <form action="<c:url value='/search/notes'/>" method="get">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="idCandidat" class="form-label">
                                <i class="fas fa-user"></i> ID Candidat
                            </label>
                            <input type="number" class="form-control" id="idCandidat" name="idCandidat" 
                                   value="${param.idCandidat}" required min="1" placeholder="ex: 1">
                            <small class="text-muted">L'identifiant du candidat</small>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="idMatiere" class="form-label">
                                <i class="fas fa-book"></i> ID Matière
                            </label>
                            <input type="number" class="form-control" id="idMatiere" name="idMatiere" 
                                   value="${param.idMatiere}" required min="1" placeholder="ex: 1">
                            <small class="text-muted">L'identifiant de la matière</small>
                        </div>
                    </div>
                </div>
                
                <div class="d-flex justify-content-between">
                    <a href="<c:url value='/'/>" class="btn btn-secondary">
                        <i class="fas fa-arrow-left"></i> Retour à l'accueil
                    </a>
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-search"></i> Rechercher
                    </button>
                </div>
            </form>
        </div>

        <c:if test="${not empty notes}">
            <div class="results-container">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h3>
                        <i class="fas fa-list"></i>
                        Résultats de la recherche
                    </h3>
                    <div>
                        <span class="badge badge-primary">${notes.size()} note(s) trouvée(s)</span>
                        <c:if test="${not empty difference}">
                            <span class="badge badge-info">Différence: ${difference}</span>
                        </c:if>
                        <!-- <c:if test="${not empty average}">
                            <span class="badge badge-success">Moyenne: ${average}</span>
                        </c:if> -->
                        <c:if test="${not empty resolution}">
                            <span class="badge badge-warning">Résolution: ${resolution}</span>
                        </c:if>
                        <c:if test="${not empty noteFinale}">
                            <span class="badge badge-danger">Note Finale: ${noteFinale}</span>
                        </c:if>
                    </div>
                </div>

                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>ID Note</th>
                                <th>Candidat</th>
                                <th>Matière</th>
                                <th>Correcteur</th>
                                <th>Note</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="note" items="${notes}">
                                <tr>
                                    <td><span class="badge">#${note.idNote}</span></td>
                                    <td><span class="badge badge-primary">#${note.idCandidat}</span></td>
                                    <td><span class="badge badge-success">#${note.idMatiere}</span></td>
                                    <td><span class="badge badge-warning">#${note.idCorrecteur}</span></td>
                                    <td><span class="note-value">${note.note}</span></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>

        <c:if test="${empty notes and not empty param.idCandidat and not empty param.idMatiere}">
            <div class="results-container">
                <div class="empty-results">
                    <i class="fas fa-search"></i>
                    <h3>Aucune note trouvée</h3>
                    <p>Aucune note n'a été trouvée pour le candidat #${param.idCandidat} et la matière #${param.idMatiere}.</p>
                    <p>Vérifiez que les identifiants sont corrects ou que des notes existent pour cette combinaison.</p>
                </div>
            </div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert-danger">
                <i class="fas fa-exclamation-circle"></i> ${error}
            </div>
        </c:if>
    </div>
</body>
</html>
