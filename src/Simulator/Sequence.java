package Simulator;

import java.util.*;

public class Sequence {
    private ArrayList<Integer> data = new ArrayList<Integer>();
    private int loops;

    public Sequence(ArrayList<Integer> data, int loops) {
        this.data = data;
        this.loops = loops;
    }

    public ArrayList<Integer> getData() {
        return data;
    }

    public int getLoop() {
        return loops;
    }
}