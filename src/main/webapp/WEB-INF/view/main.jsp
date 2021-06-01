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

    <title>Pokemon collection</title>


    <style>
        .header-h1 {
            text-align: center;
            margin-bottom: .5rem;
        }
        .header-h1 h1 {
            display: inline-block;
            background: #0097a7;
            color: #fff;
            margin-bottom: 0;
            padding: .5rem 1rem .625rem 1rem;
            font-size: 1.5rem;
            text-transform: uppercase;
            border-radius: 30px;
        }
    </style>
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

    <nav class="navbar navbar-expand-md navbar-dark bg-dark" style="margin-bottom: 20px; position:sticky; top:0; z-index: 2">
        <a class="navbar-brand" href="/hello">Home</a>
        <a class="navbar-brand" href="/loadPokemons">Update</a>
        <a class="navbar-brand" style="cursor: pointer; border-radius: 10px" onclick="alert('select 2 pokemon')" id="cmp">Compare</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarColor01">
            <ul class="navbar-nav mr-auto">
                <c:forEach  var="type" items="${types}">
                    <li class="nav-item">
                        <a class="nav-link" href="/hello?type=${type}">${type}</a>
                    </li>
                </c:forEach>
            </ul>
            <form class="form-inline">

                <input list="ice-cream-flavors" id="idPokemon" name="ice-cream-choice" placeholder="Search" aria-label="Search" style="
    display: block;
    /* width: 75%; */
    height: calc(1.5em + .75rem + 2px);
    padding: .375rem .75rem;
    font-size: 1rem;
    font-weight: 4;
    line-height: 1.5;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    border-radius: .25rem;
    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
    margin-right: 10px;
">
                <datalist id="ice-cream-flavors" >
                    <c:forEach var="name" items="${names}">
                        <option value="${name}">
                    </c:forEach>
                </datalist>
             </form>
            <button id="searchPokemon" class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
        </div>
    </nav>
    <c:if test="${list.isEmpty()}">
        <div class="header-h1" style="text-align: center">
            <h1>Nothing found</h1>
            <img src="https://i.pinimg.com/236x/3d/1f/f5/3d1ff5d9122e416d4f9ca65d1b98c736.jpg" height="100px" width="100px" >
        </div>
         </c:if>
    <c:if test="${!list.isEmpty()}">
    <div class="header-h1" style="text-align: center; margin-bottom: 20px;">
        <h1>Pokemon collection</h1>
    </div>
    </c:if>
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-xl-4 ml-1 mr-1"  style="position:relative; z-index: 1">
    <c:forEach var="pokemon" items="${list}">
        <div class="col card text-white border-0 bg-transparent" >
            <div class="w-75 bg-dark mr-auto ml-auto mb-3" style="border-radius: 10px; ">
                <div class="custom-control custom-switch">
                    <input type="checkbox" onclick="check()" class="custom-control-input custom-switch-id-element" id="${pokemon.name}">
                    <label class="custom-control-label" for="${pokemon.name}">Compare</label>
                </div>
                <div class="card-header text-center " >
                        ${pokemon.name}
                </div>
                <img src="${pokemon.pngImageUrl}" class="card-img-top" alt="...">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item bg-dark" >HP ${pokemon.hp}</li>
                    <li class="list-group-item bg-dark">attack ${pokemon.attack}</li>
                    <li class="list-group-item bg-dark">defense ${pokemon.defense}</li>
                </ul>
                <ul class="list-group list-group-flush">
                    <c:forEach var="type" items="${pokemon.types}">
                        <li class="list-group-item bg-dark" >${type.name}</li>
                    </c:forEach>
                </ul>
                <div class="card-body">
                <a href="/hello?idEvolution=${pokemon.name}" class="btn btn-primary">evolution</a>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
    <c:if test="${!list.isEmpty()}">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-2 row-cols-xl-2 ml-1 mr-1 text-cen ">
        <button class="border-0" onclick="document.location='${back}'" type="button" class="btn btn-default">Previous</button>
        <button class="border-0" onclick="document.location='${next}'"  type="button" class="btn btn-default">Next</button>
    </div>
    </c:if>
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

<script src="js/scripts.js"></script>
</html>