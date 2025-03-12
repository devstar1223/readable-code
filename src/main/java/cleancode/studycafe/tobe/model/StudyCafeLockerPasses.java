package cleancode.studycafe.tobe.model;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {
    private final List<StudyCafeLockerPass> studyCafeLockerPasses;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> studyCafeLockerPassList){
        this.studyCafeLockerPasses = studyCafeLockerPassList;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> studyCafeLockerPassList){
        return new StudyCafeLockerPasses(studyCafeLockerPassList);
    }

    public Optional<StudyCafeLockerPass> getStudyCafeLockerPass(StudyCafePass studyCafePass){
        return studyCafeLockerPasses.stream()
                .filter(option ->
                        option.getPassType() == studyCafePass.getPassType()
                                && option.getDuration() == studyCafePass.getDuration()
                )
                .findFirst();
    }
}
