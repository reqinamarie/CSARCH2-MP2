package Simulator;
import java.util.*;

public class Cache {

    private ArrayList<String> contents;
    private int size;
    private int block_size;
    private int num_blocks;
    private float access_time;
    private int mru;
    private int hits;
    private int miss;

    public Cache(int size, int block_size, int num_blocks, float access_time) {
        this.contents = new ArrayList<String>(num_blocks);
        this.size = size;
        this.block_size = block_size;
        this.num_blocks = num_blocks;
        this.access_time = access_time;
        this.mru = 0;
        this.hits = 0;
        this.miss = 0;
    } // constructor if cache memory size given is in blocks (size == num_blocks)

    public Cache(int size, int block_size, float access_time) {
        this.size = size;
        this.block_size = block_size;
        this.num_blocks = size / block_size;
        this.access_time = access_time;
        this.mru = 0;
        this.hits = 0;
        this.miss = 0;
        this.contents = new ArrayList<String>(this.num_blocks);
    }

    public int getSize() {
        return size;
    }

    public int getBlockSize() {
        return block_size;
    }

    public int getNumBlocks() {
        return num_blocks;
    }

    public float getAccessTime() {
        return access_time;
    }

    public int getMRU() {
        return mru;
    }

    public int getMiss() {
        return miss;
    }

    public int getHits() {
        return hits;
    }

    public boolean isFull() {
        if (contents.size() == num_blocks)
            return true;
        else
            return false;
    }

    public int find(String data) {
        if (contents.indexOf(data) == -1)
            miss++;
        else
            hits++;

        return contents.indexOf(data);
    }

    public void setMRU(int index) {
        this.mru = index;
    }

    public void setMRUBlock(String data) {
        contents.set(mru, data);
    }
}