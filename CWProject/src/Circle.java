// SVG circle element
// https://www.w3schools.com/graphics/svg_circle.asp
public class Circle extends Shape{

	private Attrib attribX;
	private Attrib attribY;
	private Attrib attribR;

	public Circle(){
		// Create attributes via Shape.newAttrib() and assign to members above. For example, here is the first one:
		attribX = newAttrib("cx", "");
		// Finish implementation...
		attribY = newAttrib("cy", "");
		attribR = newAttrib("r", "");
		
	}

	public Circle(float r, float cx, float cy){
		this();
		setScale(r, r);
		setPos(cx,-cy);
	}

	@Override
	public String getTag() {
		return "circle";
	}

    @Override
    protected void updateAttribs() {
        super.updateAttribs();

        attribX.val = String.valueOf(getPos().x);
        attribY.val = String.valueOf(getPos().y);
        attribR.val = String.valueOf(getScale().x); 
    }
}
