package game;

public class Bowling {

    public static final int MAX_FRAME = 10;

    private int score = 0;
    private int currentFrame = 1;
    private int frameCount = 0;

    private boolean isGameOver = false;

    public int roll(int score) {
        if(score < 0 || score > 10) {
            throw new IllegalArgumentException();
        }

        if(isGameOver) {
            throw new IllegalStateException("게임종료");
        }

        this.score += score;
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
        return score;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void clearGame() {
        score = 0;
        currentFrame = 1;
        frameCount = 0;

        isGameOver = false;
    }
}
