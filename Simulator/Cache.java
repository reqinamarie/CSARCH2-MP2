package Simulator;

import java.util.*;

public class Cache {

    private ArrayList<Integer> contents;
    private int size;
    private int block_size;
    private int num_blocks;
    private float access_time;
    private int mru;
    private int hits;
    private int miss;

    public Cache(int size, int block_size, int num_blocks, float access_time) {
        this.contents = new ArrayList<Integer>(num_blocks);
        this.size = size * block_size;
        this.block_size = block_size;
        this.num_blocks = num_blocks;
        this.access_time = access_time;
        this.mru = 0;
        this.hits = 0;
        this.miss = 0;
    } // constructor if cache memory size given is in blocks (size == num_blocks in
      // input)

    public Cache(int size, int block_size, float access_time) {
        this.size = size;
        this.block_size = block_size;
        this.num_blocks = size / block_size;
        this.access_time = access_time;
        this.mru = 0;
        this.hits = 0;
        this.miss = 0;
        this.contents = new ArrayList<Integer>(this.num_blocks);
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

    public ArrayList<Integer> getContent() {
        return contents;
    }

    public void fetch(int data) {
/*
        System.out.println("New Fetch");
        System.out.println("Current MRU: " + mru);
        System.out.println("Num blocks: " + num_blocks);
*/
        int index = this.find(data);

        if (this.isFull()) {
            if (index == -1) { // Full, did not find
                setMRUBlock(data);
            }

            else { // Full, found
                setMRU(index);
            }
        }

        else {
            if (index == -1) { // not full, did not find
                addMRUBlock(data);

                if (this.num_blocks - this.mru == 1) { // occupied last empty block
                    setMRU(this.mru);
                }

                else {
                    setMRU(this.mru + 1);
                }

            }

            else { // found, not full cache
                   // do nothing
            }
        }
    }

    private boolean isFull() {
        if (contents.size() == num_blocks)
            return true;
        else
            return false;
    }

    private int find(int data) {

        if (contents.indexOf(data) == -1)
            miss++;
        else
            hits++;

        return contents.indexOf(data);
    }

    private void setMRU(int index) {
        this.mru = index;
    }

    private void setMRUBlock(int data) {
        contents.set(mru, data);
    }

    private void addMRUBlock(int data) {
        contents.add(data);
    }
}