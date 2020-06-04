package spr.ex;

public class Padding {
    public final int top, right, bottom, left;

    public Padding() {
        this(0);
    }

    public Padding(int all) {
        this(all, all, all, all);
    }

    public Padding(int tops, int sides) {
        this(tops, sides, tops, sides);
    }

    public Padding(int top, int sides, int bottom) {
        this(top, sides, bottom, sides);
    }

    public Padding(int top, int right, int bottom, int left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public boolean isZero() {
        return top == 0 && right == 0 && bottom == 0 && left == 0;
    }
}
