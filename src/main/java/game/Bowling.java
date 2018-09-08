package game;

public class Bowling {

    public static final int MAX_FRAME = 10;

    private int currentFrame = 1;

    private int score = 0;
    private int frameScore = 0;
    private int frameCount = 0;

    private boolean isSpare = false;
    private boolean isGameOver = false;

    public int roll(int score) {
        if(score < 0 || score > 10) {
            throw new IllegalArgumentException("정수 값 범위 초과");
        }

        if(isGameOver) {
            throw new IllegalStateException("게임종료");
        }

        endFrameCheck();

        if(!addFrameScore(score)) {
            throw new IllegalArgumentException("프레입 합계 10점 초과");
        }

        this.score += score;
        frameCount ++;

        return score();
    }

    public void endFrameCheck() {
        // 점수 2회 입력 시 프레임 변경
        if(frameCount == 2) {

            clearFrame();

            // 10프레임 종료
            if(currentFrame < MAX_FRAME) {
                ++ currentFrame;
            }
        }

        if(currentFrame > MAX_FRAME)
            isGameOver = true;
    }

    public boolean addFrameScore(int score) {
        if(frameScore > 10)
            return false;

        frameScore += score;
        System.out.println("frameScore: " + frameScore);

        if(frameScore == 10)
            isSpare = true;

        return true;
    }

    public boolean isSpare() {
        return isSpare;
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

    public void clearFrame() {
        frameCount = 0;
        frameScore = 0;
        isSpare = false;
    }

    public void clearGame() {
        score = 0;
        frameScore = 0;
        currentFrame = 1;
        frameCount = 0;

        isSpare = false;
        isGameOver = false;
    }
}
