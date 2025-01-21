public class Moon extends Shape {
	
    private Attrib attribData;
    private float phase;

    public Moon() {
        this(1, 0.5f, 0, 0);
    }

    public Moon(float r, float phase, float cx, float cy) {
        attribData = newAttrib("d", "");
        setScale(r, r);
        setPos(cx, cy);
        this.phase = phase;
    }

    @Override
    public String getTag() {
        return "path";
    }

    @Override
    protected void updateAttribs() {
        super.updateAttribs();
        
        float angle = (float) (Math.PI * phase) / 2;
        Vec2 p = new Vec2((float) Math.cos(angle), (float) Math.sin(angle));

        transform(p);
        float r = getScale().x; 

        attribData.val = "M " + p.x + ", " + -p.y 
                + " A " + r + " " + r + " 0 1 0 " + p.x + ", " + p.y
                + " A " + r + " " + r + " 0 0 1 " + p.x + ", " + -p.y; 
    }
}
