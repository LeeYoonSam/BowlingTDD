package game;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Bowling
 * 볼링 점수 계산 - http://stparkms.tistory.com/62
 *
 * roll(int n)과 score()를 구현
 * 내부적으로 각 프레임(1-10)의 점수를 관리할 것
 * spare, strike 점수 구현
 * 범위를 초과했거나 횟수를 초과하여 roll을 호출하면 IllegalArgumentException과 IllegalStateException 발생
 * 최저 점수와 최대 점수까지 테스트에 반드시 반영
 * 새 테스트 통과마다 commit 해서 github에 올릴 것
 *
 *
 * ############ Story ############
 * ### 점수입력 ###
 * 한자리 점수 입력 1
 * 입력후 점수 출력 1 -> 1
 * 입력후 점수 출력 2 -> 2
 * 입력 후 스코어 호출
 *
 * ### 입력 예외 ###
 * 숫자 외 점수 입력 체크 a -> IllegalArgumentException
 * 0점 미만 점수 입력 -> IllegalArgumentException
 * 10초과 점수 입력 -> IllegalArgumentException
 *
 * ### 프레임 확인 ###
 * 점수 2번 입력 -> 1프레임
 * 점수 4번 입력 -> 2프레임
 * 점수 20번 입력 -> 10프레임
 * 10프레임 종료 후 점수입력 시 IllegalStateException
 *
 * ### 게임 종료 ###
 * 점수 20번 입력 -> 게임종료
 *
 * ### 총합 계산 ###
 * 프레임당 점수 합계 표시
 * 프레임당 스코어 최대합 확인 - 1프레임(1회, 2회)값 10점 이하 입력 -> 합계 표시
 * 프레임당 스코어 최대합 확인 - 1프레임(1회, 2회)값 10점 이상 입력 -> IllegalArgumentException
 * 프레임당 스코어 총합 10점 -> 스패어 처리
 * 10프레임 후 총합 표시
 * 10프레임 마지막까지 스패어나 스트라이크시 roll 1회 추가
 * 각 프레임의 첫번째 roll에서 스트라이크시 해당 프레임 종료
 *
 * 각 프레임당 합계 10점넘으면 IllegalStateException
 *
 * ### 추가점수 계산 ###
 * 스패어 처리 했을경우 1회 추가 점수
 * 스트라이크일때 2회 추가 점수
 * 스트라이크(10점)일때 한프레임 종료
 * 스트라이크인데 21번째 일경우 추가 점수 없음
 *
 * ### 최저/최고 점수 테스트 ###
 * 최저점수 0점 테스트
 * 최고점수 300점 테스트
 *
 */
public class BowlingTest {

    private static Bowling bowling;

    @Test
    void insertRoll() {
        bowling = new Bowling();

        bowling.roll(1);
        assertThat(bowling.score()).isEqualTo(1);

        bowling.roll(2);
        assertThat(bowling.score()).isEqualTo(3);
    }

    @Test
    void checkValidateArguments() {
        bowling = new Bowling();

        // 숫자 외 점수 입력 체크 -> int만 받으므로 입력 되지 않음


        // 0점 미만 점수 입력
        assertThat(bowling.roll(0)).isEqualTo(0);

        try {
            bowling.roll(-1);
        } catch (Exception e) {
            // you can check exception type
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }

        try {
            bowling.roll(-2);
        } catch (Exception e) {
            // you can check exception type
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }

        // 10점 이상 점수 입력
        assertThat(bowling.roll(10)).isEqualTo(10);

        try {
            bowling.roll(11);
        } catch (Exception e) {
            // you can check exception type
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }

        try {
            bowling.roll(12);
        } catch (Exception e) {
            // you can check exception type
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }

        try {
            bowling.roll(1000);
        } catch (Exception e) {
            // you can check exception type
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void checkFrame() {
        bowling = new Bowling();

        // 점수 2번 입력 -> 1프레임
        assertThat(bowling.getCurrentFrame()).isEqualTo(1);
        bowling.roll(1);

        assertThat(bowling.getCurrentFrame()).isEqualTo(1);
        bowling.roll(1);


        // 점수 4번 입력 -> 2프레임
        assertThat(bowling.getCurrentFrame()).isEqualTo(2);
        bowling.roll(0);
        assertThat(bowling.getCurrentFrame()).isEqualTo(2);
        bowling.roll(0);

        // 점수 20번 입력 -> 10프레임
        assertThat(bowling.getCurrentFrame()).isEqualTo(3);
        bowling.roll(0);
        bowling.roll(0);

        bowling.roll(0);
        bowling.roll(0);

        bowling.roll(0);
        bowling.roll(0);

        bowling.roll(0);
        bowling.roll(0);

        bowling.roll(0);
        bowling.roll(0);

        bowling.roll(0);
        bowling.roll(0);

        bowling.roll(0);
        bowling.roll(0);

        assertThat(bowling.getCurrentFrame()).isEqualTo(10);
        bowling.roll(0);
        bowling.roll(0);


        // 10프레임 종료 후 점수입력 시 IllegalStateException
        try {
            bowling.roll(0);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalStateException.class);
        }

        try {
            bowling.roll(1);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalStateException.class);
        }
    }

    // ### 총합 계산 ###
    @Test
    void sumScore() {
        bowling = new Bowling();

        // * 프레임당 점수 합계 표시
        assertThat(bowling.roll(1)).isEqualTo(1);
        assertThat(bowling.roll(1)).isEqualTo(2);

        // * 10프레임 후 총합 표시
        assertThat(bowling.roll(1)).isEqualTo(3);
        assertThat(bowling.roll(1)).isEqualTo(4);

        assertThat(bowling.roll(1)).isEqualTo(5);
        assertThat(bowling.roll(1)).isEqualTo(6);

        assertThat(bowling.roll(1)).isEqualTo(7);
        assertThat(bowling.roll(1)).isEqualTo(8);

        assertThat(bowling.roll(1)).isEqualTo(9);
        assertThat(bowling.roll(1)).isEqualTo(10);

        assertThat(bowling.roll(1)).isEqualTo(11);
        assertThat(bowling.roll(1)).isEqualTo(12);

        assertThat(bowling.roll(1)).isEqualTo(13);
        assertThat(bowling.roll(1)).isEqualTo(14);

        assertThat(bowling.roll(1)).isEqualTo(15);
        assertThat(bowling.roll(1)).isEqualTo(16);

        assertThat(bowling.roll(1)).isEqualTo(17);
        assertThat(bowling.roll(1)).isEqualTo(18);

        assertThat(bowling.getCurrentFrame()).isEqualTo(10);
        assertThat(bowling.roll(1)).isEqualTo(19);
        assertThat(bowling.roll(1)).isEqualTo(20);

        try {
            bowling.roll(1);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalStateException.class);
        }


        // * 프레임당 스코어 최대합 확인 - 1프레임(1회, 2회)값 10점 이하 입력 -> 합계 표시
        bowling.clearGame();
        bowling.roll(5);
        bowling.roll(5);
        assertThat(bowling.score()).isEqualTo(10);

        bowling.clearGame();
        bowling.roll(0);
        bowling.roll(0);
        assertThat(bowling.score()).isEqualTo(0);

        // * 프레임당 스코어 최대합 확인 - 1프레임(1회, 2회)값 10점 이상 입력 -> IllegalArgumentException
        bowling.clearGame();
        try {
            bowling.roll(5);
            bowling.roll(6);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }

        bowling.clearGame();
        bowling.roll(0);
        assertThat(bowling.roll(10)).isEqualTo(10);

        try {
            bowling.roll(10);
            bowling.roll(10);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void checkSpare() {
        bowling = new Bowling();

        // * 프레임당 스코어 총합 10점 -> 스패어 처리
        bowling.roll(0);
        bowling.roll(1);
        assertThat(bowling.isSpare()).isEqualTo(false);

        bowling.roll(10);
        bowling.roll(0);
        assertThat(bowling.isSpare()).isEqualTo(true);

        bowling.roll(5);
        bowling.roll(5);
        assertThat(bowling.isSpare()).isEqualTo(true);

        bowling.roll(1);
        bowling.roll(2);
        assertThat(bowling.isSpare()).isEqualTo(false);
    }

    @Test
    void checkLastFrameBonus() {
        bowling = new Bowling();

        // * 10프레임 마지막까지 스패어나 스트라이크시 roll 1회 추가
        for(var i = 0; i < 9; i ++) {
            bowling.roll(0);
            bowling.roll(0);
        }

        bowling.roll(9);
        bowling.roll(1);

        // 10프레임에서 스패어 처리로 보너스 1회 체크 / IllegalStateException이 나오면 안됨
        bowling.roll(5);

        // 10프레임에서 보너스 획득 실패
        bowling.clearGame();
        for(var i = 0; i < 9; i ++) {
            bowling.roll(0);
            bowling.roll(0);
        }

        // 마지막 프레임
        bowling.roll(5);
        bowling.roll(3);

        // 마지막 프레임 스패어 처리 실패 시 IllegalStateException
        try {
            bowling.roll(10);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalStateException.class);
        }


        // * 각 프레임의 첫번째 roll에서 스트라이크시 해당 프레임 종료
    }
}
