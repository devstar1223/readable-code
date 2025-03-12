package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.StudyCafepassMachineConfig;
import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.StudyCafePasses;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafeFileHandler studyCafeFileHandler;

    public StudyCafePassMachine(StudyCafepassMachineConfig studyCafepassMachineConfig) {
        this.inputHandler = studyCafepassMachineConfig.getInputHandler();
        this.outputHandler = studyCafepassMachineConfig.getOutputHandler();
        this.studyCafeFileHandler = studyCafepassMachineConfig.getStudyCafeFileHandler();
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            StudyCafePasses studyCafePasses = StudyCafePasses.of(studyCafeFileHandler.readStudyCafePasses());

            List<StudyCafePass> purchasablePassList = studyCafePasses.getStudyCafePasses(studyCafePassType);
            outputHandler.showPassListForSelection(purchasablePassList);

            StudyCafePass selectedPass = inputHandler.getSelectPass(purchasablePassList);

            if (studyCafePassType == StudyCafePassType.HOURLY) {
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (studyCafePassType == StudyCafePassType.WEEKLY) {
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (studyCafePassType == StudyCafePassType.FIXED) {
                List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
                StudyCafeLockerPass lockerPass = lockerPasses.stream()
                    .filter(option ->
                        option.getPassType() == selectedPass.getPassType()
                            && option.getDuration() == selectedPass.getDuration()
                    )
                    .findFirst()
                    .orElse(null);

                boolean lockerSelection = false;
                if (lockerPass != null) {
                    outputHandler.askLockerPass(lockerPass);
                    lockerSelection = inputHandler.getLockerSelection();
                }

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(selectedPass, lockerPass);
                } else {
                    outputHandler.showPassOrderSummary(selectedPass, null);
                }
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

}
