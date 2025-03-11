package cleancode.minesweeper.tobe.minesweeper.gamelevel;

public class Advanced implements GameLevel{

    @Override
    public int getRowSize() {
        return 20;
    }

    @Override
    public int getColSize() {
        return 24;
    }

    @Override
    public int gelLandMineCount() {
        return 99;
    }
}
