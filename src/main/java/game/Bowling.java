package game;

public class Bowling implements IBowling {

    private Frame[] bowlingFrames = new Frame[10];
    private Frame currentFrame;

    public static final int LAST_FRAME_INDEX = 9;

    private int prevFrameIndex = -999;
    private int currentFrameIndex = 0;
    private int score = 0;

    @Override
    public boolean roll(int pin) {

        if(currentFrameIndex > LAST_FRAME_INDEX) {
            if(currentFrame.isLastFrame && !currentFrame.isSpare || !currentFrame.isStrike)
                throw new IllegalStateException("게임종료");
        }

        setCurrentFrame();

        this.score += currentFrame.addPin(pin);

        // 스패어 보너스 체크
        checkBonus();

        checkEndFrame();

        return false;
    }

    @Override
    public int score() {
        return score;
    }

    public void plusPrevScore() {
        if(prevFrameIndex < 0)
            return;

        Frame prevFrame = bowlingFrames[prevFrameIndex];
        this.score += prevFrame.getBonusScore();
    }

    public void setCurrentFrame() {
        if(bowlingFrames[currentFrameIndex] == null) {

            if(currentFrame != null) {
                if(currentFrame.isLastFrame) {
                    return;
                }
            }

            bowlingFrames[currentFrameIndex] = new Frame(currentFrameIndex);
            currentFrame = bowlingFrames[currentFrameIndex];
        }
    }

    public int getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    private void checkEndFrame() {
        if(currentFrame.checkEndFrame()) {
            prevFrameIndex = currentFrameIndex;
            currentFrameIndex ++;
        }
    }

    private void checkBonus() {
        if(prevFrameIndex < 0)
            return;

        Frame prevFrame = bowlingFrames[prevFrameIndex];
        if(prevFrame.isSpare || prevFrame.isStrike) {
            prevFrame.addBouns(currentFrame.score);

            this.score += prevFrame.getBonusScore();
        }
    }

    public void clearGame() {
        bowlingFrames = new Frame[10];
        currentFrame = null;
        score = 0;
        currentFrameIndex = 0;
        prevFrameIndex = -999;
    }

    public boolean isSpare() {
        return currentFrame.isSpare;
    }

    public boolean isStrike() {
        return currentFrame.isStrike;
    }
}
