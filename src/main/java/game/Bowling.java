package game;

public class Bowling {
    private int point = 0;
    private int currentFrame = 1;
    private int frameCount = 0;

    public int roll(int point) {
        if(point < 0 || point > 10) {
            throw new IllegalArgumentException();
        }

        this.point = point;
        frameCount ++;

        if(frameCount == 2) {
            frameCount = 0;
            currentFrame ++;
        }

        return score();
    }

    public int score() {
        return point;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }
}
