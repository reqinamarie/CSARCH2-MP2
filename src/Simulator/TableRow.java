package Simulator;

public class TableRow {
    private int data;
    private int blockNo;

    public TableRow(int blockNo, int data) {
        this.blockNo = blockNo;
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public int getBlockNo() {
        return blockNo;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setBlockNo(int blockNo) {
        this.blockNo = blockNo;
    }
}
