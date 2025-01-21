public class Rect extends LineStrip {

    public Rect(){
        setNumVertices(4);
        closed = true;
    }

    public Rect(float w, float h, float cx, float cy){
        this();
        Vec2[] verts = new Vec2[] {
        	new Vec2(cx - (w / 2), cy - (h / 2)), 
            new Vec2(cx + (w / 2), cy - (h / 2)), 
            new Vec2(cx + (w / 2), cy + (h / 2)), 
            new Vec2(cx - (w / 2), cy + (h / 2))};
        
        for(int i = 0; i < verts.length; i++) {
            this.setVertex(i, verts[i]);
        }
    }
}