package Simulator;

import java.util.*;

public class Group {
    private Sequence[] sequences = null;
    private int loops;

    public Group(Sequence[] sequences, int loops)
    {
        this.sequences = sequences;
        this.loops = loops;
    }

    public Sequence[] getSequences() {
        return sequences;
    }

    public int getLoops() {
        return loops;
    }
}