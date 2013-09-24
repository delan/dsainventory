import java.io.*;
import java.util.*;

public class InventoryApplication {
	public static InventoryApplicationBackend b;
	public static void main(String[] args) {
		b = new InventoryApplicationBackend();
		System.out.println("________________________________________");
		System.out.println("");
		System.out.println("   Data Structures and Algorithms 120");
		System.out.println("    Inventory application assignment");
		System.out.println("       Delan Azabani (#17065012)");
		System.out.println("________________________________________");
		runMainMenu();
	}
	public static int readInt() {
		InputStreamReader r = new InputStreamReader(System.in);
		StreamTokenizer t = new StreamTokenizer(r);
		String m = "Invalid: not an integer";
		while (true) {
			printPrompt();
			try {
				if (t.nextToken() == t.TT_NUMBER)
					return new Double(t.nval).intValue();
				else
					System.out.println(m);
			} catch (IOException e) {
				System.out.println(m);
			}
		}
	}
	private static void runMainMenu() {
		printMainMenu();
		while (true) {
			switch (readInt()) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 0:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid: not in menu");
				break;
			}
		}
	}
	private static void printMainMenu() {
		System.out.println("");
		System.out.println("(1) Load records from array");
		System.out.println("(2) Build binary search tree from array");
		System.out.println("(3) Search array");
		System.out.println("(4) Search tree");
		System.out.println("(5) Save data to file");
		System.out.println("(0) Quit");
	}
	private static void printPrompt() {
		System.out.print("\n> ");
	}
}
