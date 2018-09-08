package game;

public class Bowling {

    public static final int MAX_FRAME = 10;

    private int point = 0;
    private int currentFrame = 1;
    private int frameCount = 0;

    private boolean isGameOver = false;

    public int roll(int point) {
        if(point < 0 || point > 10) {
            throw new IllegalArgumentException();
        }

        if(isGameOver) {
            throw new IllegalStateException("게임종료");
        }

        this.point = point;
        frameCount ++;

        // 점수 2회 입력 시 프레임 변경
        if(frameCount == 2) {
            frameCount = 0;

            // 10프레임 종료
            if(currentFrame < MAX_FRAME) {
                ++ currentFrame;
            }
        }

        if(currentFrame > MAX_FRAME)
            isGameOver = true;

        return score();
    }

    public int score() {
        return point;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }
}
