/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import exceptions.LoginSampleException;
import functionLayer.LogicFacade;
import functionLayer.Product;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author oerte
 */
public class OrderRequest extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        HttpSession session = request.getSession();
        //length and width from carportFlatRoof or carportPointedRoof jsp page.
        double laengde = Double.parseDouble(request.getParameter("laengde"));
        double bredde = Double.parseDouble(request.getParameter("bredde"));
        //passing length to CarportCalulator in LogicFacade classe
        List<Product> stykliste = LogicFacade.CarportCalculater(laengde, bredde);
        // "stykliste is passed to a metode in LogicFacde, who calculates the total price of the carport
        double totalPriceOfCarport = LogicFacade.totalPriceOfCarport(stykliste);
        //Set styklisten and totalPriceOfCarport in session
        session.setAttribute("stykliste", stykliste);
        session.setAttribute("totalPriceOfCarport", totalPriceOfCarport);
        // Transfer towards orderRequest.jsp
        return "orderRequest";
    }

}