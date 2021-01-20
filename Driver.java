import java.util.*;

public class Driver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter num of blocks in mm: ");
		int numblock = sc.nextInt();
		System.out.print("Enter memory access time: ");
		int accesstime = sc.nextInt();

		Memory memory = new Memory(numblock, accesstime);

		System.out.print("blocks in mm: " + memory.getMMSize());
	}
}
