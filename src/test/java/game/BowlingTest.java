package game;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

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
 * 한자리 점수 입력 1
 * 입력후 점수 출력 1 -> 1
 * 입력 후 스코어 호출
 *
 * 숫자외 점수 입력 체크 a -> IllegalArgumentException
 * 0점 미만 점수 입력 -> IllegalArgumentException
 * 10초과 점수 입력 -> IllegalArgumentException
 *
 * 점수입력 2회 후 스코어 호출
 * 점수입력 20회 후 스코어 호출
 * 10프레임 종료 후 점수입력 시 IllegalStateException
 * 10프레임 마지막까지 스패어나 스트라이크시 roll 1회 추가
 * 각 프레임의 첫번째 roll에서 스트라이크시 해당 프레임 종료
 *
 * 스패어 처리 했을경우 1회 추가 점수
 * 스트라이크일때 2회 추가 점수
 * 스트라이크(10점)일때 한프레임 종료
 * 스트라이크인데 21번째 일경우 추가 점수 없음
 *
 * 최저점수 0점 테스트
 * 최고점수 300점 테스트
 *
 */
public class BowlingTest {

    @Test
    void insertRoll() {
        assertThat(Bowling.roll(1)).isEqualTo(1);
    }
}
