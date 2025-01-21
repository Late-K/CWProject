public class RegPolygon extends LineStrip {

    public RegPolygon(int n, float r, float cx, float cy) {
        setNumVertices(n);
        closed = true;

        for (int i = 0; i < n; i++) {
            double angle = 2 * Math.PI * i / n - Math.PI /2;
            float x = cx + r * (float) Math.cos(angle);
            float y = cy - r * (float) Math.sin(angle);
            setVertex(i, x, y);
        }
    }
}
