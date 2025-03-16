package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.io.provider.SeatPassFileReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
class StudyCafeSeatPassesTest {
  @DisplayName("이용자가 선택한 타입에 맞는 좌석 목록이 2개이상 있다.")
  @Test
  void test() {
      //given
      SeatPassFileReader seatPassFileReader = new SeatPassFileReader();
      StudyCafeSeatPasses studyCafeSeatPasses = seatPassFileReader.getSeatPasses();

      //when
      List<StudyCafeSeatPass> studyCafeSeatPassList = studyCafeSeatPasses.findPassBy(StudyCafePassType.FIXED);

      //then
      assertThat(studyCafeSeatPassList).hasSizeGreaterThan(1);
  }
}
