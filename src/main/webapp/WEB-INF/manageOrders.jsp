<%-- 
    Document   : manageOrders
    Created on : 07-12-2018, 00:02:50
    Author     : Jesper
--%>
<%@page import="functionLayer.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="functionLayer.Product"%>
<%@page import="functionLayer.LogicFacade"%>
<%@page import="functionLayer.Carport"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./CSS/headerCSS.css">
        <link rel="stylesheet" href="./CSS/selectBoxes.css">
        <link rel="stylesheet" href="./CSS/tableDesign.css">
        <link rel="stylesheet" href="./CSS/manageOrderTable">
        <title>Manage Orders</title>
    </head>
    <body>
        <%List<Carport> allOrders = (List) session.getAttribute("allOrders"); %>
        
        <!-- -----------------HEADER---------------------------------- -->
        <% User user = (User) session.getAttribute("user"); %>
        
       <%if(user == null) { %>
        <header>
            <img id="fog" src="./IMAGES/FogLogo.png" alt="logo">
            <a class="active" href="/FrontController?command=toNavigator&site=registration">Sign up</a>
            <a class="active" href="/FrontController?command=toNavigator&site=login">Login</a>
            <a class="active" href="/FrontController?command=toNavigator&site=home">Home</a>
        </header>
        
        <%} else if(user.getRole().equals("admin")) {%> 
            <header>
                <img id="fog" src="./IMAGES/FogLogo.png" alt="logo">
                <a class="active" href="/FrontController?command=toNavigator&site=logout">Sign out</a>
                <a class="active" href="/FrontController?command=Admin">Admin</a>
                <a class="active" href="/FrontController?command=manageOrders">Se Ordre</a>
                <a class="active" href="/FrontController?command=toNavigator&site=home">Home</a>
            </header>
        <%} else if(user.getRole().equals("customer")) { %>
            <header>
                <img id="fog" src="./IMAGES/FogLogo.png" alt="logo">
                <a class="active" href="/FrontController?command=toNavigator&site=logout">Sign out</a>
                <a class="active" href="">Profile</a>
                <a class="active" href="/FrontController?command=toNavigator&site=home">Home</a>
            </header>
        <%}%>

        <!-- -----------------SECTION 1: Choose what order to view------------------------------------>
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="manageOrders">
            <div class="container">
                <div class="control-group">
                    <select name="selectOrder">
                        <option value="allOrdre">Alle Ordre</option>
                        <option value="pending">Pending Odre</option>
                        <option value="approved">Godkente Odre</option>
                        <option value="paid">Betalte Odre</option>
                        <option value="cancelled">Anulleret Ordre</option>
                    </select>
                    <button type="submit" name="seOrdre"> Se Ordre</button>
                </div>
            </div>
        </form>

        <!-- -----------------SECTION 2.0: Display orders------------------------------------>
        <% for (Carport list : allOrders) {%>
        <form action="FrontController" method="POST">
        <input type="hidden" name="command" value="showProductList">
        <div class="container">
            <div class="control-group">
                <% if (list.getRoof().equals("FLAT")) {%>
                <h4>CARPORT MED FLADT TAG | Odre ID: <%=list.getId()%> | Oprettet: <%=list.getDate()%></h4>
                <% } else {%>
                <h4>CARPORT MED SPIDST TAG | Odre ID: <%=list.getId()%> | Oprettet: <%=list.getDate()%></h4>
                <%}%>
                <h4>Carport beskrivelse:</h4>
                <text>Længde: <%=list.getCarport_length()%>cm</text>
                <br>
                <br>
                <text>Bredde: <%=list.getCarport_width()%>cm</text>
                <br>
                <br>
                <text>Grader: <%=list.getDegrees()%></text>
                <br>
                <br>
                <text>Tag materiale: <%=list.getRoofMaterial()%></text>
                <br>
                <br>
                <text>Total pris ex moms: <%=list.getTotal_price()%></text>
                <br>
                <br>
                <text>Status på odre: <%=list.getStatus_of_order()%></text>
                <br>
                <br>
                <text>Skurs længde: <%=list.getShed().getShed_length()%></text>
                <br>
                <br>
                <text>Skurs bredde: <%=list.getShed().getShed_width()%></text>
                <br>
                <br>
                <text>Kunde id: <%=list.getCustomer_id()%></text>
                <br>
                <br>
                <input type="hidden" name="carportID" value="<%= list.getId() %>">
                    <button type="submit" name="seeProductList">Se styklisten</button>
                </form>
                
                <!-- -----------------SECTION 2.1: Customer info on order ------------------------------------>
                    
                <form action="FrontController" method="POST">
                    <input type="hidden" name="command" value="seeCustomerInfo">
                    <input type="hidden" name="customerID" value="<%= list.getCustomer_id() %>">
                    <button type="submit" name="seeCustomerInfo">Se kunde oplysninger</button>
                </form>

                <!-- -----------------SECTION 2.2: Update order status------------------------------------>           


                <form action="FrontController" method="POST">
                    <div class="control-groupManageOrders">
                        <h4>Ændre status på odren</h4>
                        <br>
                        <input type="checkbox" name="status" value="approved"> Approved
                        <br>
                        <br>
                        <input type="checkbox" name="status" value="paid"> Paid
                        <br>
                        <br>
                        <input type="checkbox" name="status" value="cancelled"> Cancel
                        <br>
                        <br>
                        <input type="hidden" name="id" value="<%= list.getId()%>">
                        <input type="hidden" name="command" value="updateOrderStatus">
                        <button type="submit" name="updateStatus">Opdater order status</button>
                </form>
            </div>
        </div>
    </div>
    <%}%>         

</body>
</html>