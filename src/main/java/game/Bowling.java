package game;

public class Bowling {

    public static final int LAST_FRAME = 10;
    public static final int MAX_ONE_FRAME_COUNT = 2;
    public static final int MAX_SCORE = 10;

    private int currentFrame = 1;

    private int score = 0;
    private int frameScore = 0;
    private int frameCount = 0;

    private boolean isSpare = false;
    private boolean isStrike = false;
    private boolean isLastFrameBonus = false;

    private boolean isGameOver = false;

    public int roll(int score) {
        if(score < 0 || score > MAX_SCORE) {
            throw new IllegalArgumentException("정수 값 범위 초과");
        }

        if(isGameOver) {
            throw new IllegalStateException("게임종료");
        }

        if(!addFrameScore(score)) {
            throw new IllegalArgumentException("프레입 합계 10점 초과");
        }

        this.score += score;

        checkEndFrame();

        return score();
    }

    private void checkEndFrame() {
        // 점수 2회 입력 시 프레임 변경
        if(frameCount == MAX_ONE_FRAME_COUNT) {
            currentFrame ++;

            System.out.println("currentFrame: " + getCurrentFrame());
            if(currentFrame > LAST_FRAME) {
                if(!isLastFrameBonus) {
                    isGameOver = true;
                }
            }
        }
    }

    private boolean addFrameScore(int score) {
        if(frameCount == MAX_ONE_FRAME_COUNT) {
            clearFrame();
        }

        if(isLastFrameBonus) {
            frameScore += score;
            isLastFrameBonus = false;

            isGameOver = true;

            return true;
        }

        frameScore += score;
        System.out.println("frameScore: " + frameScore);

        frameCount ++;

        if(frameScore > MAX_SCORE) {
            return false;
        }

        if(frameScore == MAX_SCORE) {

            if(frameCount == 1) {
                isStrike = true;
                frameCount = MAX_ONE_FRAME_COUNT;
            } else {
                isSpare = true;
            }

            if(currentFrame == LAST_FRAME) {
                isLastFrameBonus = true;
            }
        }

        return true;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public int score() {
        return score;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    private void clearFrame() {
        frameCount = 0;
        frameScore = 0;
        isSpare = false;
        isStrike = false;
    }

    public void clearGame() {
        score = 0;
        frameScore = 0;
        currentFrame = 1;
        frameCount = 0;

        isSpare = false;
        isLastFrameBonus = false;
        isGameOver = false;
    }
}
