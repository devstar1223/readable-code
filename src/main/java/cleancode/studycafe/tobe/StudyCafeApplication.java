package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.StudyCafepassMachineConfig;
import cleancode.studycafe.tobe.io.ConsoleInputHandler;
import cleancode.studycafe.tobe.io.ConsoleOutputHandler;
import cleancode.studycafe.tobe.io.CsvStudyCafeFileHandler;
import cleancode.studycafe.tobe.io.InputHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafepassMachineConfig studyCafepassMachineConfig = new StudyCafepassMachineConfig(
                new ConsoleInputHandler(),
                new ConsoleOutputHandler(),
                new CsvStudyCafeFileHandler()
        );

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(studyCafepassMachineConfig);
        studyCafePassMachine.run();
    }

}
