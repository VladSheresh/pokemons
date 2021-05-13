<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    <link rel="stylesheet" href="css/style.css">
    <title>Hello, world!</title>


</head>


<body style="background: url(https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png) 100% 100% no-repeat fixed;">
<div class="container-fluid">
    <!--
    <div class="row">
        <div class="col col-xl-6 col-md-12 alert alert-success alert-dismissible fade show" role="alert">
            <h4 class="alert-heading">Hello, world! <span class="badge badge-warning">New</span></h4>
            <p>A simple success alert—check it out!</p>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>

        <div class="col col-xl-6 col-md-12 alert alert-warning alert-dismissible fade show" role="alert">
            <strong>Holy guacamole!</strong> You <a href="#" class="badge badge-primary alert-link disabled"
                                                    tabindex="-1" role="button" aria-disabled="true">an example link</a>
            should check in on some of those fields below.
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </div>

    <button type="button" class="btn btn-primary btn-block">
        Notifications <span class="badge badge-pill badge-light">4</span>
    </button>

    <button type="button" class="btn btn-primary btn-block active btn-lg " disabled>
        Notifications <span class="badge badge-pill badge-light">4</span>
    </button>
    <a href="https://getbootstrap.com/docs/4.6/components/badge/" class="badge badge-primary">Primary</a>
    <div class="btn-group" role="group" aria-label="Basic example">
        <button type="button" class="btn btn-secondary">Left</button>
        <button type="button" class="btn btn-info">Middle</button>
        <button type="button" class="btn btn-primary">Right</button>
    </div>
    -->

    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01"
                aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarColor01">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Features</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Pricing</a>
                </li>
                <li class="nav-item" id="about">
                    <a class="nav-link" href="#">About</a>
                </li>
            </ul>
            <form class="form-inline">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>

    <nav class="navbar navbar-light bg-light">
        <form class="form-inline">
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">@</span>
                </div>
                <input type="text" class="form-control" placeholder="Username" aria-label="Username"
                       aria-describedby="basic-addon1">
            </div>
        </form>
    </nav>

    <!--
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group pd-2" role="group" aria-label="First group">
                <button type="button" class="btn btn-secondary">1</button>
                <button type="button" class="btn btn-secondary">2</button>
                <button type="button" class="btn btn-secondary">3</button>
                <button type="button" class="btn btn-secondary">4</button>
            </div>
            <div class="btn-group ml-2" role="group" aria-label="Second group">
                <button type="button" class="btn btn-secondary">5</button>
                <button type="button" class="btn btn-secondary">6</button>
                <button type="button" class="btn btn-secondary">7</button>
            </div>
            <div class="btn-group" role="group" aria-label="Third group">
                <button type="button" class="btn btn-secondary">8</button>
            </div>
        </div>
        -->
    <!--        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-xl-4 ml-1 mr-1">-->
    <!--            <div class="row row-cols-2 card text-white border-0 bg-transparent mr-auto ml-auto">-->
    <div class="row row-cols-2 text-white w-75 bg-dark mr-auto ml-auto" style="border-radius: 10px">
        <div class="col">
            <div class="card-header text-center ">
                ${first.name}
            </div>
            <img src="${first.pngImageUrl}"
                 class="card-img-top" alt="...">
        </div>
        <div class="col">
            <div class="card-header text-center ">
                ${second.name}
            </div>
            <img src="${second.pngImageUrl}"
                 class="card-img-top" alt="...">
        </div>
        <c:forEach var="key" items="${first.stats.keySet()}">

            <div class="col-12 text-center">
                    ${key}
            </div>
            <div class="col text-center <c:if test="${first.stats.get(key) > second.stats.get(key)}"> lower-stat </c:if>">
                    ${first.stats.get(key)}
            </div>
            <div class="col text-center greater-stat">
                    ${second.stats.get(key)}
            </div>

        </c:forEach>
        <div class="col-12 text-center">
            HP
        </div>
        <div class="col text-center <c:if test="${first.stats.get('hp') > second.stats.get('hp')}"> lower-stat </c:if>">
            ${first.stats.get("hp")}
        </div>
        <div class="col text-center greater-stat">
            ${second.stats.get("hp")}
        </div>

        <div class="col-12 text-center">
            damage
        </div>
        <div class="col text-center greater-stat">
            ${first.stats.get("attack")}
        </div>
        <div class="col text-center lower-stat <c:if test="true">asd</c:if>">
            ${second.stats.get("attack")}
        </div>

        <div class="col-12 text-center">
            defense
        </div>
        <div class="col text-center greater-stat">
            ${first.stats.get("defense")}
        </div>
        <div class="col text-center lower-stat">
            ${second.stats.get("defense")}
        </div>

        <div class="card-footer text-muted">
            2 days ago
        </div>
    </div>
    <!--            </div>-->

    <!--        </div>-->

    <button onclick="clearLocalStorage()">Create button</button>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
        integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
        crossorigin="anonymous"></script>
</html>