package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.io.provider.LockerPassFileReader;
import cleancode.studycafe.tobe.io.provider.SeatPassFileReader;
import cleancode.studycafe.tobe.model.pass.StudyCafePass;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeLockerPassesTest {
    @DisplayName("고정석을 구매한 이용자는 구매할 수 있는 사물함이 있다.")
    @Test
    void purchasableLockerTest() {
        //given
        LockerPassFileReader lockerPassFileReader = new LockerPassFileReader();
        StudyCafeLockerPasses studyCafeLockerPasses = lockerPassFileReader.getLockerPasses();

        StudyCafeSeatPass studyCafePass = StudyCafeSeatPass.of(StudyCafePassType.FIXED,12,700000,0.15);

        //when
        Optional<StudyCafeLockerPass> optionalStudyCafeLockerPass = studyCafeLockerPasses.findLockerPassBy(studyCafePass);

        //then
        assertThat(optionalStudyCafeLockerPass).isPresent();
    }
}
