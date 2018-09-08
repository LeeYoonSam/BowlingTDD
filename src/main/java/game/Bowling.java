package game;

public class Bowling {
    private int point = 0;

    public void roll(int point) {
        this.point = point;
    }

    public int score() {
        return point;
    }
}
