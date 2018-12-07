/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import exceptions.FogException;
import functionLayer.SVGSide;
import functionLayer.SVGTop;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author oerte
 */
public class OrderRequestFlatRoof extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException {
        HttpSession session = request.getSession();
        
        
        //String degreeStr = request.getParameter("degree");
        //double degree = Double.parseDouble(degreeStr.substring(0, 2));
        //length and width from shed from carportFlatRoof or carportPointedRoof jsp page.
        boolean shedCheck;
        double shedLength = Double.parseDouble(request.getParameter("skurlaengde"));
        double shedWidth = Double.parseDouble(request.getParameter("skurbredde"));
        //length and width from carportFlatRoof or carportPointedRoof jsp page.
        double length = Double.parseDouble(request.getParameter("laengde"));
        double width = Double.parseDouble(request.getParameter("bredde"));
        String redskabsskur = request.getParameter("redskabsskur");
        // value from roof selector
        String roofMaterial = request.getParameter("Tag");
        //Checks if roof is pointed for SVG's sake
        boolean pointedRoof;
        
        if (redskabsskur == null) {
            shedCheck = false;
        } else {
            shedCheck = true;
        }

        //Set styklisten, bredde, længde and totalPriceOfCarport in session
        session.setAttribute("roofMaterial", roofMaterial);
        session.setAttribute("redskabsskur", redskabsskur);
        session.setAttribute("bredde", width);
        session.setAttribute("laengde", length);
        session.setAttribute("skurbredde", shedWidth);
        session.setAttribute("skurlaengde", shedLength);
        //session.setAttribute("degree", degree);

        //------------SVG-------------
        //Rules (Tempoarily)
        double height = 230;

        //Inserting svg of the carport
        
        //Carport fra toppen.
        SVGTop testSVG = new SVGTop(length, width, shedLength, shedWidth, shedCheck);
        request.setAttribute("drawingTop", testSVG.getMySVG());


        //Carport fra siden.
        SVGSide sSVG = new SVGSide(length, height, shedLength, shedWidth, shedCheck);
        request.setAttribute("drawingSide", sSVG.getMySVG());


        return "orderRequestFlatRoof";
    }

}
