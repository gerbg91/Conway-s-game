<%-- 
    Document   : visor
    Author     : Ger
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="index" scope="session" class="Entidad.datos"  />
<!DOCTYPE html>
<html>
    <head>
        <title>Conway´s Game</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/style.css" rel="stylesheet" type="text/css"/> 
    </head>
    <body>
        <div id="banner"><p>Conway´s Game</p></div>
        <% if (request.getParameter("tiempoGeneracion") == null) { %>
        <div id="jugada">
            <form method="POST" action="visor.jsp" >
                <b>Avanzar X generacion</b>:<input type="number" name="tiempoGeneracion" value="1" id="int1"/>          
                <input type="submit" value="Jugar"/> 
            </form>
        </div>
        <% index.primigenea_Generacion();%>
        <%} else { %>
        <div id="jugada">
            <form method="POST" action="visor.jsp">
                <b>Avanzar X generacion</b>:<input type="number" name="tiempoGeneracion" value="1" id="int1"/>          
                <input type="submit" value="Jugar"/> 
            </form>
        </div>
            <%! private int hacer_X_Generaciones;%>
            <%  hacer_X_Generaciones = Integer.parseInt(request.getParameter("tiempoGeneracion"));%>
            <jsp:setProperty name="index" property="avance" value="<%= hacer_X_Generaciones%>" />

            <% index.evolucion_De_Generaciones();%>
            <%}%>
            <table border="1">
                <%! private int fila, columna;
                                                 %> 
                <%  for (fila = 1; fila < index.getX() - 1; fila++) {%>
                <tr>   
                    <%  for (columna = 1; columna < index.getY() - 1; columna++) {%>
                    <% if (index.getCelulas()[fila][columna] == 1) {%>
                    <td class="viva">    
                        <%} else { %>   
                    <td class="muerta"> 
                        <% } %>
                    </td>                
                    <% } %>
                </tr>    
                <% }%>    
            </table> 
        </div>
    </body>
</html>