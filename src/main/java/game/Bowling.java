package game;

public class Bowling {
    private int point = 0;

    public int roll(int point) {
        if(point < 0) {
            throw new IllegalArgumentException();
        }

        this.point = point;

        return score();
    }

    public int score() {
        return point;
    }
}
