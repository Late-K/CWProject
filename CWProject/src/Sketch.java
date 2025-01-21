import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class Sketch {

    public ArrayList<Shape> shapes; 
    public SVG svg;

    public Sketch() {
        this.shapes = new ArrayList<>();
        this.svg = new SVG();
    }
    
    public void fromFile(String dir) {
    	 shapes.clear();
        try {
            var scanIn = new Scanner(new File(dir));
            
            while (scanIn.hasNextLine()) {
                String row = scanIn.nextLine().trim();
                
                if (row.isEmpty() || row.startsWith("#")) {
                    continue;
                }

                String[] slice = row.split(" ");
                String shapeType = slice[0];
                
                switch (shapeType) {
                    case "line":
                        createLine(slice);
                        break;
                    case "circle":
                        createCircle(slice);
                        break;
                    case "arc":
                        createArc(slice);
                        break;
                    case "rect":
                        createRect(slice);
                        break;
                    case "square":
                        createSquare(slice);
                        break;
                    case "ngon":
                        createNgon(slice);
                        break;
                    case "star":
                        createStar(slice);
                        break;
                    case "moon":
                        createMoon(slice);
                        break;
                    default:
                        System.err.println("Unknown shape type: "+shapeType);
                        break;
                }
            }
            scanIn.close();
        } catch (Exception e) {
            System.err.println("Sketch file not found: "+dir);
        }
    }

    private void createCircle(String[] slice) {
        float radius = Float.parseFloat(slice[1]);
        float x = Float.parseFloat(slice[2]);
        float y = Float.parseFloat(slice[3]);

        Circle circle = new Circle(radius, x, y);
        OptionalAttribs(slice, 4, circle);
        
        circle.updateAttribs();
        shapes.add(circle);
    }
    
    private void createArc(String[] slice) {
        float radius = Float.parseFloat(slice[1]);
        float arc_angle = Float.parseFloat(slice[2]);
        float arc_length = Float.parseFloat(slice[3]);
        float x = Float.parseFloat(slice[4]);
        float y = Float.parseFloat(slice[5]);

        Arc arc = new Arc(radius, arc_angle, arc_length, x, y);
        OptionalAttribs(slice, 6, arc);

        arc.updateAttribs();
        shapes.add(arc);
    }
    
    private void createRect(String[] slice) {
        float w = Float.parseFloat(slice[1]);
        float h = Float.parseFloat(slice[2]);
        float x = Float.parseFloat(slice[3]);
        float y = Float.parseFloat(slice[4]);

        Rect rect = new Rect(w, h, x, y);
        OptionalAttribs(slice, 5, rect);
 
        rect.updateAttribs();
        shapes.add(rect);
    }
    
    private void createSquare(String[] slice) {
        float w = Float.parseFloat(slice[1]);
        float x = Float.parseFloat(slice[2]);
        float y = Float.parseFloat(slice[3]);

        Rect square = new Rect(w, w, x, y);
        OptionalAttribs(slice, 4, square);

        square.updateAttribs();
        shapes.add(square);
    }
    
    private void createNgon(String[] slice) {
        int sides = (int) Float.parseFloat(slice[1]);
        float r = Float.parseFloat(slice[2]);
        float x = Float.parseFloat(slice[3]);
        float y = Float.parseFloat(slice[4]);

        RegPolygon ngon = new RegPolygon(sides, r, x, y);
        OptionalAttribs(slice, 5, ngon);

        ngon.updateAttribs();
        shapes.add(ngon);
    }
    
    private void createStar(String[] slice) {
        int points = (int) Float.parseFloat(slice[1]);
        float r = Float.parseFloat(slice[2]);
        float x = Float.parseFloat(slice[3]);
        float y = Float.parseFloat(slice[4]);

        Star star = new Star(points, r, x, y);
        OptionalAttribs(slice, 5, star);

        star.updateAttribs();
        shapes.add(star);
    }
    
    private void createMoon(String[] slice) {
        int r = (int) Float.parseFloat(slice[1]);
        float phase = Float.parseFloat(slice[2]);
        float x = Float.parseFloat(slice[3]);
        float y = Float.parseFloat(slice[4]);

        Moon moon = new Moon(r, phase, x, y);
        OptionalAttribs(slice, 5, moon);

        moon.updateAttribs();
        shapes.add(moon);
    }
    
	private void createLine(String[] slice) {
            float x1 = Float.parseFloat(slice[1]);
            float y1 = Float.parseFloat(slice[2]);
            float x2 = Float.parseFloat(slice[3]);
            float y2 = Float.parseFloat(slice[4]);

            Line line = new Line(x1, y1, x2, y2);
            OptionalAttribs(slice, 5, line);
                    
            line.updateAttribs();
            shapes.add(line);
    }
	
    private void OptionalAttribs(String[] slice, int startIndex, Shape shape) {
        float strokeWidth = 1;
        int strokeColor = 255;    
        int fillColor = 0;      

        for (int i = startIndex; i < slice.length; i++) {
            if (slice[i].matches("^[0-9a-fA-F]{6,8}$")) {
                if (strokeColor == 255) { 
                    strokeColor = (int) Long.parseLong(slice[i], 16);
                } else {
                    fillColor = (int) Long.parseLong(slice[i], 16);
                }
            } else {
            	if (strokeWidth == 1) { 
            		strokeWidth = Float.parseFloat(slice[i]);
            	}
            }
        }
        shape.setStrokeWidth(strokeWidth);
        shape.setStroke(strokeColor);
        shape.setFill(fillColor);
    }



	public void render(String dir, String baseName) {
		dir = baseName+".txt";
		fromFile(dir);
		svg.clearContent();
		svg.addShape(shapes);
		svg.toFile(baseName+".svg");
	}
}
