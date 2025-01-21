import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SVG extends Elem {
    private ViewBox viewBox;

    public SVG() {
        this.viewBox = new ViewBox();
        newAttrib("xmlns", "http://www.w3.org/2000/svg");
        newAttrib("version", "1.1");
        newAttrib("viewBox", viewBox.toString());
    }

    @Override
    public String getTag() {
        return "svg";
    }

    public void toFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(this.toString());
            System.out.println("SVG file saved to "+fileName);
            System.out.println(this.toString());
        } catch (Exception e) {
            System.err.println("Error writing SVG file");
        }
    }

    public void addShape(ArrayList<Shape> shapes) {
    	for (int i = 0; i < shapes.size(); i++) {
            addContent(shapes.get(i));
        }
    }
}
