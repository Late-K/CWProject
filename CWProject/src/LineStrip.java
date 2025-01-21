// Sequence of points connected with line segments
// https://www.w3schools.com/graphics/svg_polyline.asp
// https://www.w3schools.com/graphics/svg_polygon.asp
public class LineStrip extends Shape {

	private Attrib attribPoints;
	private float[] vertices; // 2D position data packed as [x1, y1, x2, y2, ...]
	protected boolean closed = false;

	public LineStrip(){
		attribPoints = newAttrib("points", "");	
	}

	// Returns "polygon" if closed, otherwise "polyline"
	@Override
	public String getTag(){
		// Your implementation...
		if(closed) {
			return "polygon";
		} else {
			return "polyline";
		}
	}

	// (This method is finished. No need to edit.)
	@Override
	protected void updateAttribs(){
		super.updateAttribs();

		String s = "";
		var p = new Vec2();
		for(int i=0; i<getNumVertices(); i++){
			p.set(getVertex(i,0), getVertex(i,1));
			transform(p);
			p.negY(); // SVG has flipped y-axis. We negate y so +y is up.
			s += p.x + "," + p.y + " ";
		}
		attribPoints.val = s;
	}

	protected void setNumVertices(int n){
		// Your implementation...
		vertices = new float[n * 2];
	}

	public int getNumVertices(){
		// Your implementation...
		return vertices.length / 2;
	}
	
	// Set vertex at index
	protected void setVertex(int i, float x, float y){
		// Your implementation...
		vertices[i * 2] = x;
        vertices[i * 2 + 1] = y;
	}
	
	// Set vertex at index
	protected void setVertex(int i, Vec2 p){
		// Your implementation...
		vertices[i * 2] = p.x;
        vertices[i * 2 + 1] = p.y;
	}

	// Get vertex component (0:x or 1:y) at index
	protected float getVertex(int i, int comp){
		// Your implementation...
		return vertices[i * 2 + comp];
	}
	
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isClosed() {
        return closed;
    }
}