
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="index" scope="session" class="Entidad.datos"  />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>JSP Page</title>
    </head>
    <body>     
        <%! private double semilla; %>
        <%! private boolean a = true; %>
        <%! private int x, y;%>
        <%! private String born, stayAlive;%>
        <%
            x = Integer.parseInt(request.getParameter("x"));
            y = Integer.parseInt(request.getParameter("y"));
            semilla = Double.parseDouble(request.getParameter("semilla"));
            born = request.getParameter("born");
            stayAlive = request.getParameter("stayAlive");
        %>
        <jsp:setProperty name="index" property="x" value="<%=x%>" />
        <jsp:setProperty name="index" property="y" value="<%=y%>" />
        <jsp:setProperty name="index" property="semilla" value="<%=semilla%>" />
        <jsp:setProperty name="index" property="born" value="<%=born%>" />
        <jsp:setProperty name="index" property="stayAlive" value="<%=stayAlive%>" />
        <% index.primigenea_Generacion();%>
        <% if (a == true) %>
        <jsp:forward page="visor.jsp" /> 
    </body>
</html>
