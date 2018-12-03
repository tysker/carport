<%-- 
    Document   : index
    Created on : 13-11-2018, 10:25:42
    Author     : Jesper
--%>

<%@page import="functionLayer.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Fog</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./CSS/headerCSS.css">
        <link rel="stylesheet" href="./CSS/selectBoxes.css">
        <link rel="icon" href=""
    </head>
    <body>
        <% User user = (User) session.getAttribute("user"); %>
        
        <%if(user == null) { %>
        <header>
            <img id="fog" src="./IMAGES/FogLogo.png" alt="logo">
            <a class="active" href="#SignUp">Sign up</a>
            <a class="active" href="/FrontController?command=toLogin">Login</a>
            <a class="active" href="/index.jsp">Home</a>
            <a class="active" href="/FrontController?command=Admin">Admin</a>
            <!-- <input type='submit' value="Log Out">-->
        </header>
        <%} else {%> 
        <header>
            <img id="fog" src="./IMAGES/FogLogo.png" alt="logo">
            <a class="active" href="/FrontController?command=toLogin">Profile</a>
            <a class="active" href="/index.jsp">Home</a>
            <a class="active" href="/FrontController?command=Admin">Admin</a>
            <!-- <input type='submit' value="Log Out">-->
        </header>
        <%}%>
        <div class="containerTextIndex">
            <div class="control-groupTextIndex">
                <h1 class="title">Velkommen til Fog Quick-byg carport !</h1>
                <text>Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en skitsetegning på en carport  </text>
                <br>
                <text>indenfor vores standardprogram, der tilpasses dine specifikke ønsker.</text>
                <br>
                <br>
                Vælg for neden hvilken type carport du ønsker
                <br>
                <br>
                Spidst tag eller fladt tag.
        </div>

    <center>
        <form action="FrontController" method="post">
            <div class="containerPic">                
                <div class="overlay overlayFade">
                    <div class="text">
                        <h1>Spidst tag</h1>
                        Quick-byg carport med spidst tag
                    </div>
                </div>
                <input type="hidden" name="command" value="PointedRoof">
                <input type="image" src="./IMAGES/spidsTag.jpg" class="image" value="Spidst Tag">
            </div>
        </form></center>
    <center>
        <form action="FrontController" method="post">
            <div class="containerPic">
                <div class="overlay overlayFade">
                    <div class="text">
                        <h1>Fladt tag</h1>
                        Quick-byg carport med fladt tag
                    </div>
                </div>
                <input type="hidden" name="command" value="FlatRoof">
                <input type="image" src="./IMAGES/fladtTag.png" class="image" value="Fladt tag">
            </div>
        </form>
    </center>
        </div>
        
        


</body>
</html>
