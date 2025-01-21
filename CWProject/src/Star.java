public class Star extends LineStrip {

    public Star(int n, float r, float cx, float cy) {
        setNumVertices(n * 2);
        closed = true;

        for (int i = 0; i < n * 2; i++) {
        	float radius = r / (1 + (i % 2));
            float angle = (float) (Math.PI * i / n - Math.PI / 2); 
            float x = cx + radius * (float) Math.cos(angle);
            float y = cy - radius * (float) Math.sin(angle);
            setVertex(i, x, y);
        }
    }
}