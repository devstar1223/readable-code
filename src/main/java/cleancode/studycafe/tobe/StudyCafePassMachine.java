package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.StudyCafepassMachineConfig;
import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.*;

import java.util.List;
import java.util.Optional;

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

            StudyCafePass selectedPass = getStudyCafePass(studyCafePassType);

            StudyCafeLockerPasses studyCafeLockerPasses = StudyCafeLockerPasses.of(studyCafeFileHandler.readLockerPasses());
            Optional<StudyCafeLockerPass> optionalStudyCafeLockerPass = studyCafeLockerPasses.getStudyCafeLockerPass(selectedPass);
            if(optionalStudyCafeLockerPass.isPresent()){
                StudyCafeLockerPass studyCafeLockerPass = optionalStudyCafeLockerPass.get();

                outputHandler.askLockerPass(studyCafeLockerPass);
                if (inputHandler.getLockerSelection()) {
                    outputHandler.showPassOrderSummary(selectedPass, studyCafeLockerPass);
                } else {
                    outputHandler.showPassOrderSummary(selectedPass, null);
                }
            }else{
                outputHandler.showPassOrderSummary(selectedPass, null);
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass getStudyCafePass(StudyCafePassType studyCafePassType) {
        StudyCafePasses studyCafePasses = StudyCafePasses.of(studyCafeFileHandler.readStudyCafePasses());

        List<StudyCafePass> purchasablePassList = studyCafePasses.getStudyCafePasses(studyCafePassType);
        outputHandler.showPassListForSelection(purchasablePassList);

        return inputHandler.getSelectPass(purchasablePassList);
    }

}
