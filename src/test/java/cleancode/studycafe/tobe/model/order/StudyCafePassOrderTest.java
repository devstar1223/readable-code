package cleancode.studycafe.tobe.model.order;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafePassOrderTest {
    @DisplayName("이용자가 지불할 최종 가격을 알 수 있다.")
    @Test
    void getTotalPriceTest() {
        //given
        StudyCafeSeatPass weeklyPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 4, 150000, 0.1);
        StudyCafeSeatPass fixedPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);
        StudyCafeLockerPass studyCafeLockerPassFourWeek = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);

        StudyCafePassOrder studyCafePassOrder = StudyCafePassOrder.of(weeklyPass, null);
        StudyCafePassOrder studyCafePassWithLockerPassOrder = StudyCafePassOrder.of(fixedPass, studyCafeLockerPassFourWeek);

        //when
        int passOrderTotalPrice = studyCafePassOrder.getTotalPrice();
        int withLockerPassOrderTotalPrice = studyCafePassWithLockerPassOrder.getTotalPrice();

        //then
        assertThat(passOrderTotalPrice).isEqualTo(weeklyPass.getPrice() - weeklyPass.getDiscountPrice());
        assertThat(withLockerPassOrderTotalPrice).isEqualTo(fixedPass.getPrice()+ studyCafeLockerPassFourWeek.getPrice() - fixedPass.getDiscountPrice());
    }
}
