package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeLockerPassTest {
    @DisplayName("좌석 이용권의 타입과 사물함 이용권의 타입이 같은지 확인한다.")
    @Test
    void studyCafePassTypeSameTest() {
        //given
        StudyCafeLockerPass studyCafeLockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);
        StudyCafeSeatPass weeklyPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 4, 150000, 0.1);
        StudyCafeSeatPass fixedPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);

        //when
        boolean isSame = studyCafeLockerPass.isSamePassType(fixedPass.getPassType());
        boolean isSame2 = studyCafeLockerPass.isSamePassType(weeklyPass.getPassType());

        //then
        assertThat(isSame).isEqualTo(true);
        assertThat(isSame2).isEqualTo(false);
    }
}
