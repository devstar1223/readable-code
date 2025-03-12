package cleancode.studycafe.tobe.model;

import java.util.List;

public class StudyCafePasses {
    private final List<StudyCafePass> studyCafePasses;

    private StudyCafePasses(List<StudyCafePass> studyCafePassList){
        this.studyCafePasses = studyCafePassList;
    }

    public static StudyCafePasses of(List<StudyCafePass> studyCafePassList){
        return new StudyCafePasses(studyCafePassList);
    }

    public List<StudyCafePass> getStudyCafePasses(StudyCafePassType studyCafePassType){
        return studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == studyCafePassType)
                .toList();
    }
}
