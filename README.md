# CSARCH2-MP2 User's Manual
### The application simulates a Full Associative Cache Mapping Function with MRU (Most Recently Used) as the Replacement Algorithm. It is able to support the following:
- Sequential that runs a single time
- Sequential with loop
- Sequential with varying loops
- Sequential with single-level nested loops
- Sequential with disjointed sequences (e.g. blocks 0-5, then blocks 10-15)

## **Pages**
### 1. Input Page

The user is expected to input the following information to perform the cache simulation:
- **Block Size**: Size of a block in words.
- **Main Memory Size**: Size of the Main Memory. May be either in blocks or words.

- **Main Memory Access Time**: Main Memory Access Time represented in nanoseconds.
- **Cache Memory Size**: Size of the Cache Memory Size. May be either in blocks or words.

- **Cache Access Time**: Cache Access Time represented in nanoseconds.

- **Read Type**: Read type of the cache. May either be Load-Through or No Load-Through.
    - **Load-Through**: The desired information can optionally be sent to the processor prior to completion of the cache line fill.
    - **No Load-Through**: The information is read from the main memory and a block or cache line is copied from the main memory onto the cache (cache line fill). The data is then transferred from the cache to the CPU.
    
- **Next Button**: Go to the Sequence Page (Enabled once all the fields are filled)

### 2. Sequence Page

- **Number of Sequence Groups**: This determines the number of sequence groups in the simulation. Generally, this is useful for creating a group of sequences (where each can have varying number of loops) that will be looped *n* times; this allows support for nested loop. 

- **Create Button**: Creates/updates number of sequence groups in the sequence group input area of the page.

- **Sequence**: A data sequence is represented as a string of characters. One inner loop is also supported per data sequence. **Repetitions** refer to the number of times the line sequence will be looped/repeated.
  
Refer to [DataSequenceRepresentation.png](.png) for an example of accepted data sequences. 


- **Add Sequence Button**: Adds a new sequence field in the sequence group.

- **Remove Sequence Button**: Removes endmost sequence field in the sequence group.

- **Begin Simulate**: Run simulation. 

### 3. Output Page
Once the necessary fields have been filled up, the application will display the following information:
- **Hit Rate**: Percentage of memory accesses found in the cache memory.
  
- **Miss Rate**: Percentage of memory accesses not found in the cache.
  
- **Miss Penalty**: Extra time required to fetch a block from the Main Memory into the cache memory.  

- **Average Access Time**:  Average memory access time retrieved by the processor.
  
- **Total Access Time**: Total memory access time with cache of the program.
  
- **Cache Contents**: Outputs a table(?) to represent the cache memory and its contents.

## **Authors**
- [Bianca Joy Benedictos](https://www.facebook.com/biancajoyrb/)
- [Kenneth Loquinte](https://www.facebook.com/loquintek)
- [Regina Masilang](https://www.facebook.com/ReginaMasiIang/)
- [Mikayla Tejada](https://www.facebook.com/kikay30) 



