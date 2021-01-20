import java.util.*;

public class Cache{

    private ArrayList<String> contents = new ArrayList<String>();
    private int size;
    private int block_size;
    private int num_blocks;
    private float access_time;
    private int mru;

    public Cache(ArrayList<String> contents, int size, int block_size, int num_blocks, float access_time) {
        this.contents = contents;
        this.size = size;
        this.block_size = block_size;
        this.num_blocks = num_blocks;
        this.access_time = access_time;
        this.mru = 0;	
    } //constructor if cache memory size given is in blocks (size == num_blocks)
    
    public Cache(ArrayList<String> contents, int size, int block_size, float access_time){
        this.contents = contents;
        this.size = size;
        this.block_size = block_size;
        this.num_blocks = size / block_size;
        this.access_time = access_time;
        this.mru = 0;
    }
    
    public int getSize() {
        return size;
    }
    
    public int getBlockSize() {
        return block_size; 
    }
    
    public int getNumBlocks () {
        return num_blocks;
    }
    
    public float getAccessTime () {
        return access_time; 
    }
    
    public int getMRU () {
        return mru;
    }
    
    public boolean isFull () {
        if (contents.size() == num_blocks)
            return true;
        else return false;
    }
    
    public int find (int data) {
        return contents.indexOf(data);
    }
    
    public void setMRU (int index) {
        this.mru = index;
    }
    
    public void setMRUBlock (String data) {
        contents.set(mru, data);
    }
}