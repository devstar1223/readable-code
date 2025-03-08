package cleancode.minesweeper.tobe;

public class Cell {

    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□";
    private static final String EMPTY_SIGN = "■";

    private int nearbyLandMineCount;
    private boolean isLandmine;
    private boolean isFlagged;
    private boolean isOpened;

    private Cell(int nearbyLandMineCount, boolean isLandmine,boolean isFlagged,boolean isOpened) {
        this.nearbyLandMineCount = nearbyLandMineCount;
        this.isLandmine = isLandmine;
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    public static Cell of(int nearbyLandMineCount, boolean isLandmine, boolean isFlagged, boolean isOpened){
        return new Cell(nearbyLandMineCount,isLandmine,isFlagged,isOpened);
    }

    public static Cell create() {
        return of(0,false,false,false);
    }

    public void turnOnLandMine() {
        this.isLandmine = true;
    }

    public void updateNearbyLandMineCount(int count) {
        this.nearbyLandMineCount = count;
    }

    public void flag() {
        this.isFlagged = true;
    }

    public void open() {
        this.isOpened = true;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isLandMine() {
        return isLandmine;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean hasLandMineCount() {
        return this.nearbyLandMineCount != 0;
    }

    public String getSign() {
        if (isOpened){
            if(isLandmine){
                return LAND_MINE_SIGN;
            }
            if (hasLandMineCount()){
                return String.valueOf(nearbyLandMineCount);
            }
            return EMPTY_SIGN;
        }

        if(isFlagged){
            return FLAG_SIGN;
        }

        return UNCHECKED_SIGN;
    }
}
