package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
    private static final boolean HORIZONTAL = false;
    private static final boolean VERTICAL = true;
    private Node root;

    private class Node {
        private Point p;
        private boolean orientation;
        private Node leftBottom;
        private Node rightTop;

        Node(Point p, boolean orientation) {
            this.p = p;
            this.orientation = orientation;
        }
    }

    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = add(p, root, HORIZONTAL);
        }
    }

    private Node add(Point p, Node n, boolean orientation) {
        if (n == null) {
            return new Node(p, orientation);
        }
        if (p.equals(n.p)) {
            return n;
        }
        int compare = comparePoints(p, n.p, orientation);
        if (compare < 0) {
            n.leftBottom = add(p, n.leftBottom, !orientation);
        } else {
            n.rightTop = add(p, n.rightTop, !orientation);
        }
        return n;
    }

    private int comparePoints(Point a, Point b, boolean orientation) {
        if (orientation == HORIZONTAL) {
            return Double.compare(a.getX(), b.getX());
        } else {
            return Double.compare(a.getY(), b.getY());
        }
    }

    @Override
    public Point nearest(double x, double y) {
        return nearest(root, new Point(x, y), root.p);
    }

    private Point nearest(Node n, Point closest, Point coord) {
        if (n == null) {
            return coord;
        }
        if (Point.distance(n.p, closest) < Point.distance(coord, closest)) {
            coord = n.p;
        }
        if (n.orientation == HORIZONTAL && n.p.getY() > closest.getY()
                || n.orientation == VERTICAL && n.p.getX() > closest.getX()) {
            coord = nearest(n.leftBottom, closest, coord);
            coord = nearest(n.rightTop, closest, coord);
        } else {
            coord = nearest(n.rightTop, closest, coord);
            coord = nearest(n.leftBottom, closest, coord);
        }
        return coord;
    }

    public static void main(String[] args) {

    }
}
