import java.util.*;

public class Sequence {
    private ArrayList<String> data = new ArrayList<String>();
    private int loops;

    public Sequence(ArrayList<String> data, int loops) {
        this.data = data;
        this.loops = loops;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public int getLoop() {
        return loops;
    }
}