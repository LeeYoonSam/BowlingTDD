package game;

public class Frame {
    public static final int MAX_PIN = 10;

    int pin = 0;
    int score = 0;
    int bonusScore = 0;
    int frameIndex;
    int bonusCount = 0;

    boolean isFirstRoll = true;
    boolean isSpare = false;
    boolean isStrike = false;
    boolean isLastFrame = false;
    boolean endFrame = false;

    public Frame(int frameIndex) {
        this.frameIndex = frameIndex;

        if(frameIndex == 9)
            isLastFrame = true;
    }

    int addPin(int pin) {
        this.pin = pin;

        addScore(pin);
        checkPin();

        return pin;
    }

    private void addScore(int pin) {

        if(pin < 0 || pin > MAX_PIN) {
            throw new IllegalArgumentException("정수 값 범위 초과");
        }

        // 핀은 10개를 넘어갈수 없다.
        if(getScore() + pin > MAX_PIN) {
            if(isLastFrame && (isSpare || isStrike)) {
                // 마지막 프레임에서 스패어나 스트라이크면 핀개수 초과 무시
            } else {
                throw new IllegalArgumentException("핀 개수 10개 초과");
            }
        }

        // 마지막 프레임 예외
        if(isLastFrame && isStrike || isSpare) {
            isStrike = false;
            isSpare = false;
        }

        this.score += pin;
    }

    void addBouns(int bonus) {
        if(bonusCount > 0) {
            this.bonusScore += bonus;
            bonusCount --;
        }
    }

    int getScore() {
        return score;
    }

    int getBonusScore() {
        return bonusScore;
    }

    private void checkPin() {

        if(!isFirstRoll) {
            endFrame = true;
        }

        if(score == MAX_PIN) {
            if(isFirstRoll) {
                isStrike = true;
                bonusCount = 2;
            } else {
                isSpare = true;
                bonusCount = 1;
            }

            if(isLastFrame)
                endFrame = false;
            else
                endFrame = true;
        }

        isFirstRoll = false;
    }

    boolean checkEndFrame() {
        return endFrame;
    }
}
