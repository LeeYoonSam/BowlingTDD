package game;

public class Bowling {

    public static final int MAX_FRAME = 10;

    private int currentFrame = 1;

    private int score = 0;
    private int frameScore = 0;
    private int frameCount = 0;

    private boolean isGameOver = false;

    public int roll(int score) {
        if(score < 0 || score > 10) {
            throw new IllegalArgumentException("정수 값 범위 초과");
        }

        if(isGameOver) {
            throw new IllegalStateException("게임종료");
        }

        if(!addFrameScore(score)) {
            throw new IllegalArgumentException("프레입 합계 10점 초과");
        }

        this.score += score;
        frameCount ++;

        // 점수 2회 입력 시 프레임 변경
        if(frameCount == 2) {
            frameCount = 0;
            frameScore = 0;

            // 10프레임 종료
            if(currentFrame < MAX_FRAME) {
                ++ currentFrame;
            }
        }

        if(currentFrame > MAX_FRAME)
            isGameOver = true;

        return score();
    }

    public boolean addFrameScore(int score) {
        if(frameScore > 10)
            return false;

        frameScore += score;

        return true;
    }

    public int getFrameScore() {
        return frameScore;
    }

    public int score() {
        return score;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void clearGame() {
        score = 0;
        frameScore = 0;
        currentFrame = 1;
        frameCount = 0;

        isGameOver = false;
    }
}
