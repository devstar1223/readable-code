package cleancode.minesweeper.tobe.io.sign;

import cleancode.minesweeper.tobe.cell.CellSnapshot;

public class EmptyCellSignProvider implements CellSignProvidable{

    private static final String EMPTY_SIGN = "■";

    @Override
    public String provide(CellSnapshot cellSnapshot) {
        return EMPTY_SIGN;
    }
}
