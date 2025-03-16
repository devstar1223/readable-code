package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;
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

    @DisplayName("고정석을 구매할경우에만 기간에 맞는 사물함을 구매할 수 있다.")
    @Test
    void LockerPassPurchasableTest() {
        //given
        StudyCafeSeatPass weeklyPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 4, 150000, 0.1);
        StudyCafeSeatPass fixedPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);
        StudyCafeLockerPass studyCafeLockerPassFourWeek = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);
        StudyCafeLockerPass studyCafeLockerPassTwelveWeek = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 30000);

        //when
        boolean unPurchasableLockerPass = weeklyPass.isSameDurationType(studyCafeLockerPassFourWeek);
        boolean purchasableLockerPass = fixedPass.isSameDurationType(studyCafeLockerPassFourWeek);
        boolean notMatchPurchasableLockerPass = fixedPass.isSameDurationType(studyCafeLockerPassTwelveWeek);

        //then
        assertThat(unPurchasableLockerPass).isEqualTo(false);
        assertThat(purchasableLockerPass).isEqualTo(true);
        assertThat(notMatchPurchasableLockerPass).isEqualTo(false);
    }

    @DisplayName("좌석 이용권의 기간과 좌석타입이 사물함 이용권과 동일한지 확인한다.")
    @Test
    void cafePassDurationAndTypeSameLockerPass() {
        //given
        StudyCafeSeatPass studyCafeSeatPassWeekly = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 12, 400000, 0.15);
        StudyCafeSeatPass studyCafeSeatPassFixed = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700000, 0.15);
        StudyCafeLockerPass studyCafeLockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED,12,30000);

        //when
        boolean isSame = studyCafeSeatPassWeekly.isSameDurationType(studyCafeLockerPass);
        boolean isSame2 = studyCafeSeatPassFixed.isSameDurationType(studyCafeLockerPass);

        //then
        assertThat(isSame).isFalse();
        assertThat(isSame2).isTrue();
    }
}
