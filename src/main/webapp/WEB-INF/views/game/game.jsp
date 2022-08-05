<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="cybersoft.javabackend.java18.gamedoanso.utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <title>Đoán Số</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
    <a class="navbar-brand font-weight-bold" href="#">Trò Chơi Đoán Số</a>
    <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link font-weight-bold" href="<%=request.getContextPath() + UrlUtils.ROOT%>">Home<span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link font-weight-bold" href="<%=request.getContextPath() + UrlUtils.GAME%>">Game</a>
            </li>
            <li class="nav-item">
                <a class="nav-link font-weight-bold"
                   href="<%=request.getContextPath() + UrlUtils.XEP_HANG%>">Ranking</a>
            </li>
        </ul>
    </div>
    <div class="nav-item dropdown">
        <a class="nav-link dropdown-toggle font-weight-bold" href="#" role="button" data-toggle="dropdown"
           aria-expanded="false">
            ${sessionScope.currentUser.username}
        </a>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="<%=request.getContextPath() + UrlUtils.DANG_XUAT%>">Đăng xuất</a></li>
        </ul>
    </div>
</nav>
<form action="<%=request.getContextPath() + UrlUtils.NEW_GAME%>" method="post" class="mt-2 mr-5 float-right">
    <input type="text" name="game-session" value="${game.id}" hidden>
    <div class="form-row align-items-center">
        <button type="submit" class="btn btn-outline-success btn-lg">GAME MỚI</button>
    </div>
</form>
<div class="container">
    <div class="row justify-content-center mt-5 clearfix">
        <div class="col-md-8" ${game.isCompleted ? 'hidden': ''}>
            <h2 class="text text-primary text-center">MỜI BẠN ĐOÁN SỐ</h2>
        </div>
        <div class="col-md-8" ${game.isCompleted ? '': 'hidden'}>
            <h2 class="text text-success text-center">PINGO!!! PINGO!!! PINGO!!!</h2>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <form action="<%=request.getContextPath() + UrlUtils.GAME%>" method="post" ${game.isCompleted ? 'hidden': ''}>
                <input type="text" name="game-session" value="${game.id}" hidden>
                <div class="form-group form-row">
                    <label for="number"></label>
                    <input type="number" name="guess" class="form-control form-control-lg text-center col-4 offset-4" id="number" required ${game.isCompleted ? 'readonly': ''}>
                </div>
                <div class="form-row align-items-center">
                    <button type="submit" class="btn btn-outline-primary btn-lg col-4 offset-4">Đoán</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row justify-content-center mt-5">
        <div class="col-md-8">
            <table class="table table-borderless">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Số đoán</th>
                    <th scope="col">Kết quả</th>
                    <th scope="col">Thời gian</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="guess" items="${game.guess}" varStatus="loop">
                    <c:choose>
                        <c:when test="${guess.result == 0}">
                            <tr class="table-success">
                                <th scope="row">${loop.index + 1}</th>
                                <td>${guess.value}</td>
                                <td>PINGO!!!</td>
                                <td>${guess.timestamp}</td>
                            </tr>
                        </c:when>
                        <c:when test="${guess.result == -1}">
                            <tr class="table-danger">
                                <th scope="row">${loop.index + 1}</th>
                                <td>${guess.value}</td>
                                <td>Số vừa đoán bé hơn kết quả.</td>
                                <td>${guess.timestamp}</td>
                            </tr>
                        </c:when>
                        <c:when test="${guess.result == 1}">
                            <tr class="table-warning">
                                <th scope="row">${loop.index + 1}</th>
                                <td>${guess.value}</td>
                                <td>Số vừa đoán lớn hơn kết quả.</td>
                                <td>${guess.timestamp}</td>
                            </tr>
                        </c:when>
                    </c:choose>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
