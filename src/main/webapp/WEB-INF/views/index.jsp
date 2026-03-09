<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Système de Correction</title>
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
            text-align: center;
            margin-bottom: 60px;
        }
        h1 {
            font-size: 48px;
            font-weight: 700;
            color: white;
            margin: 0;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.3);
        }
        .subtitle {
            color: rgba(255,255,255,0.8);
            font-size: 18px;
            margin-top: 10px;
        }
        .cards-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 30px;
            margin-bottom: 40px;
        }
        .entity-card {
            background: white;
            border-radius: 15px;
            padding: 30px;
            text-align: center;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            transition: all 0.3s ease;
            text-decoration: none;
            color: inherit;
        }
        .entity-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 40px rgba(0,0,0,0.15);
        }
        .entity-icon {
            font-size: 48px;
            margin-bottom: 20px;
            color: #667eea;
        }
        .entity-title {
            font-size: 24px;
            font-weight: 600;
            color: #333;
            margin-bottom: 10px;
        }
        .entity-description {
            color: #666;
            font-size: 14px;
        }
        .stats-section {
            background: rgba(255,255,255,0.1);
            border-radius: 15px;
            padding: 30px;
            margin-bottom: 40px;
        }
        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
        }
        .stat-item {
            text-align: center;
            color: white;
        }
        .stat-number {
            font-size: 36px;
            font-weight: 700;
            margin-bottom: 5px;
        }
        .stat-label {
            font-size: 14px;
            opacity: 0.8;
        }
        @media (max-width: 768px) {
            .page-header {
                margin-bottom: 40px;
            }
            h1 {
                font-size: 36px;
            }
            .cards-grid {
                grid-template-columns: 1fr;
                gap: 20px;
            }
        }
    </style>
</head>
<body>
    <div class="container main-container">
        <div class="page-header">
            <h1>
                <i class="fas fa-graduation-cap"></i>
                Système de Correction
            </h1>
            <div class="subtitle">Gestion des candidats, correcteurs et évaluations</div>
        </div>

        <div class="stats-section">
            <div class="stats-grid">
                <div class="stat-item">
                    <div class="stat-number">
                        <i class="fas fa-users"></i>
                    </div>
                    <div class="stat-label">Candidats</div>
                </div>
                <div class="stat-item">
                    <div class="stat-number">
                        <i class="fas fa-chalkboard-teacher"></i>
                    </div>
                    <div class="stat-label">Correcteurs</div>
                </div>
                <div class="stat-item">
                    <div class="stat-number">
                        <i class="fas fa-book"></i>
                    </div>
                    <div class="stat-label">Matières</div>
                </div>
                <div class="stat-item">
                    <div class="stat-number">
                        <i class="fas fa-chart-line"></i>
                    </div>
                    <div class="stat-label">Notes</div>
                </div>
            </div>
        </div>

        <div class="cards-grid">
            <a href="<c:url value='/candidats'/>" class="entity-card">
                <div class="entity-icon">
                    <i class="fas fa-users"></i>
                </div>
                <div class="entity-title">Candidats</div>
                <div class="entity-description">Gérer les candidats aux évaluations</div>
            </a>

            <a href="<c:url value='/correcteurs'/>" class="entity-card">
                <div class="entity-icon">
                    <i class="fas fa-chalkboard-teacher"></i>
                </div>
                <div class="entity-title">Correcteurs</div>
                <div class="entity-description">Gérer les correcteurs et examinateurs</div>
            </a>

            <a href="<c:url value='/matieres'/>" class="entity-card">
                <div class="entity-icon">
                    <i class="fas fa-book"></i>
                </div>
                <div class="entity-title">Matières</div>
                <div class="entity-description">Gérer les matières d'évaluation</div>
            </a>

            <a href="<c:url value='/notes'/>" class="entity-card">
                <div class="entity-icon">
                    <i class="fas fa-chart-line"></i>
                </div>
                <div class="entity-title">Notes</div>
                <div class="entity-description">Consulter et gérer les notes</div>
            </a>

            <a href="<c:url value='/resolutions'/>" class="entity-card">
                <div class="entity-icon">
                    <i class="fas fa-tasks"></i>
                </div>
                <div class="entity-title">Résolutions</div>
                <div class="entity-description">Gérer les types de résolution</div>
            </a>

            <a href="<c:url value='/operateurs'/>" class="entity-card">
                <div class="entity-icon">
                    <i class="fas fa-user-cog"></i>
                </div>
                <div class="entity-title">Opérateurs</div>
                <div class="entity-description">Gérer les opérateurs système</div>
            </a>

            <a href="<c:url value='/parametres'/>" class="entity-card">
                <div class="entity-icon">
                    <i class="fas fa-cogs"></i>
                </div>
                <div class="entity-title">Paramètres</div>
                <div class="entity-description">Configurer les paramètres système</div>
            </a>
        </div>
    </div>
</body>
</html>
