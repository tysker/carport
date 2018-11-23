/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer;

/**
 *
 * @author Jesper
 */
public class CreateSVG {

    private String mySVG = null;
    private StringBuilder sb = new StringBuilder();

    public CreateSVG(double length, double width) {
        double width1 = length + 200;
        double height1 = width + 100;
        this.sb = sb.append("<SVG width=\"" + width1 + "\" height=\"" + height1 + "\">");
        //ADD ALL METHODS FOR SVG DRAWING FROM TOP
        sb.append(createRemme(length, width));
        sb.append(createLægter(length, width));
        sb.append(createStolper(length, width));
        sb.append(createLenghtText(length, width));
        sb.append(createWidthText(length, width));

        sb.append("</SVG>");
        this.mySVG = sb.toString();

    }

    public String getMySVG() {
        return mySVG;
    }

    private String createLægter(double length, double width) {
        StringBuilder sb = new StringBuilder();

        //Calculates the quantity of current material
        double qty = Math.ceil(length / 50);
        qty -= 2;
        //We create a variable to store where the last lægte will be placed
        double lastLægte = length - 40;
        width += 25;

        sb.append("<rect x=\"30\" y=\"40\" height=\"5\" width=\"" + width + "\" style=\"stroke: #292929; fill:none;\"/>");
        sb.append("<rect x=\"30\" y=\"" + lastLægte + "\" height=\"5\" width=\"" + width + "\" style=\"stroke: #292929; fill:none;\"/>");

        length -= 80;
        double delta = length / (qty + 1); //68
        double y = delta + 40;
        for (int i = 0; i < qty; i++) {
            System.out.println(qty);
            sb.append("<rect x=\"30\" y=\"" + y + "\" height=\"5\" width=\"" + width + "\" style=\"stroke: #292929; fill:none;\"/>");
            y += delta;
<<<<<<< HEAD

=======
>>>>>>> fbdd9d80c24b4e16bed0f8229b31cd656253dfc8
        }
        return sb.toString();
    }

    private String createRemme(double length, double width) {
        StringBuilder sb = new StringBuilder();

        if (width >= 510) {
            double placering = width / 2;
            sb.append("<rect x=\"" + (placering + 40) + "\" y=\"0\" height=\"" + length + "\" width=\"5\" style=\"stroke: #292929; fill:none; stroke-width: 1.5;\"/>");
        }

        sb.append("<rect x=\"40\" y=\"0\" height=\"" + length + "\" width=\"5\" style=\"stroke: #292929; fill:none; stroke-width: 1.5;\"/>");
        sb.append("<rect x=\"" + (width + 40) + "\" y=\"0\" height=\"" + length + "\" width=\"5\" style=\"stroke: #292929; fill:none; stroke-width: 1.5;\"/>");

        sb.append("<rect x=\"45\" y=\"0\" height=\"5\" width=\"" + width + "\" style=\"stroke: #292929; fill:none; stroke-width: 1.5;\"/>");
        sb.append("<rect x=\"40\" y=\"" + length + "\" height=\"5\" width=\"" + (width + 5) + "\" style=\"stroke: #292929; fill:none; stroke-width: 1.5;\"/>");

        return sb.toString();
    }

    private String createStolper(double length, double width) {
        StringBuilder sb = new StringBuilder();
        double y = length - 10;
        double x = width + 30;
        double y1 = length / 2;
        //420, 390

        if (length <= 450) {
            sb.append("<rect x=\"45\" y=\"" + y + "\" height=\"10\" width=\"10\" style=\"stroke: #292929; fill:1;\"/>");
            sb.append("<rect x=\"" + x + "\" y=\"" + y + "\" height=\"10\" width=\"10\" style=\"stroke: #292929; fill:1;\"/>");
            sb.append("<rect x=\"45\" y=\"5\" height=\"10\" width=\"10\" style=\"stroke: #292929; fill:1;\"/>");
            sb.append("<rect x=\"" + x + "\" y=\"5\" height=\"10\" width=\"10\" style=\"stroke: #292929; fill:1;\"/>");
        } else if (length > 450) {
            sb.append("<rect x=\"45\" y=\"" + y + "\" height=\"10\" width=\"10\" style=\"stroke: #292929; fill:1;\"/>");
            sb.append("<rect x=\"" + x + "\" y=\"" + y + "\" height=\"10\" width=\"10\" style=\"stroke: #292929; fill:1;\"/>");
            sb.append("<rect x=\"45\" y=\"5\" height=\"10\" width=\"10\" style=\"stroke: #292929; fill:1;\"/>");
            sb.append("<rect x=\"" + x + "\" y=\"5\" height=\"10\" width=\"10\" style=\"stroke: #292929; fill:1;\"/>");

            //De 2 ekstra stolper i midten på hver side.
            sb.append("<rect x=\"45\" y=\"" + y1 + "\" height=\"10\" width=\"10\" style=\"stroke: #292929; fill:1;\"/>");
            sb.append("<rect x=\"" + x + "\" y=\"" + y1 + "\" height=\"10\" width=\"10\" style=\"stroke: #292929; fill:1;\"/>");
        }

        return sb.toString();

    }

    private String createLenghtText(double length, double width) {
        StringBuilder sb = new StringBuilder();
        double x1 = width + 70;
        double x = width + 80;
        double y = length / 2;

        sb.append("<line x1=\"" + x1 + "\" y1=\"0\" x2=\"" + x1 + "\" y2=\"" + length + "\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />");
        sb.append("<text x=\"" + x + "\" y=\"" + y + "\" fill=\"red\">" + length + " cm" + "</text>");

        return sb.toString();
    }

    private String createWidthText(double length, double width) {
        StringBuilder sb = new StringBuilder();
        double y = length + 15;
        double x = width + 40;
        double xText = (width / 2) + 40;
        double yText = length + 35;

        // y1 og y2 skal være det samme, bestemmer hvor langt nede den skal ligge
        // x1 er hvor linjen skal starte og x2 er hvor linjen skal slutte
        sb.append("<line x1=\"40\" y1=\"" + y + "\" x2=\"" + x + "\" y2=\"" + y + "\" style=\"stroke:rgb(255,0,0);stroke-width:2\"/>");
        sb.append("<text x=\"" + xText + "\" y=\"" + yText + "\" fill=\"red\">" + width + " cm" + "</text>");

        return sb.toString();
    }

}
