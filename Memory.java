import java.util.*;

public class Memory{
    private int num_blocks;
    private float access_time;

    /*
        num_blocks = main memory size in blocks
        access_time = main memory access time
    */
    public Memory(int num_blocks, float access_time){
        this.num_blocks = num_blocks;
        this.access_time = access_time;
    }

    /*
        num_words = main memory size in words
        block_size = words per block
        access_time = main memory access time
    */
    public Memory(int num_words, int block_size, float access_time){
        this.num_blocks = num_words/block_size;
        this.access_time = access_time;
    }

    public int getMMSize(){
        return num_blocks;
    }

    public float getAccessTime(){
        return access_time;
    }
}
