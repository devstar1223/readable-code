package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeSeatPassTest {
    @DisplayName("이용권 가격에서 할인될 가격을 계산한다.")
    @Test
    void discountPriceTest() {
        //given
        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 12, 400000, 0.15);

        //when
        int discountPrice = studyCafeSeatPass.getDiscountPrice();

        //then
        assertThat(discountPrice).isEqualTo(60000);
    }
}
