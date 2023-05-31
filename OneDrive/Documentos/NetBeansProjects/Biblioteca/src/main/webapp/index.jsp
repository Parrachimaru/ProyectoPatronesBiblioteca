<%@page import="java.util.List"%>
<%@page import="com.edu.unipiloto.libro.Libro"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : index
    Created on : 27/05/2023, 12:01:04 p. m.
    Author     : parra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
    <title>Lista de Libros</title>
</head>
<body>
    <h1>Lista de Libros</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Título</th>
                <th>Autor</th>
                <th>Editorial</th>
                <th>Disponibilidad</th>
            </tr>
        </thead>
        <tbody>
            <% List<Libro> listaLibros = (List<Libro>) request.getAttribute("listaLibros"); %>
            <% if (listaLibros != null && !listaLibros.isEmpty()) { %>
                <% for (Libro libro : listaLibros) { %>
                    <tr>
                        <td><%= libro.getId_libro() %></td>
                        <td><%= libro.getTitulo() %></td>
                        <td><%= libro.getAutor() %></td>
                        <td><%= libro.getEditorial() %></td>
                        <td><%= libro.getDisponibilidad() %></td>
                    </tr>
                <% } %>
            <% } else { %>
                <tr>
                    <td colspan="5">No se encontraron libros.</td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
   
</html>
