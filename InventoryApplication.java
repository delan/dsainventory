import java.io.*;
import java.util.*;

public class InventoryApplication {
	public static InventoryApplicationBackend B;
	public static void main(String[] args) {
		B = new InventoryApplicationBackend();
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
		String m = "\nInvalid: not an integer";
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
	public static String readLine() {
		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(r);
		while (true) {
			printPrompt();
			try {
				return b.readLine();
			} catch (IOException e) {
				System.out.println("\nInvalid: try again");
			}
		}
	}
	private static void runMainMenu() {
		boolean done1 = false;
		while (true) {
			printMainMenu();
			switch (readInt()) {
			case 1:
				if (!done1) {
					loadRecordsIntoArray();
					done1 = true;
				} else {
					System.out.println(
						"\nData already loaded."
					);
				}
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
				System.out.println("\nInvalid: not in menu");
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
	private static void loadRecordsIntoArray() {
		String n;
		String l;
		FileInputStream s = null;
		InputStreamReader r;
		BufferedReader b;
		boolean bad = false;
		long t = 0, i = 0;
		System.out.println("\nEnter file name:");
		n = readLine();
		System.out.println("");
		try {
			s = new FileInputStream(n);
			r = new InputStreamReader(s);
			b = new BufferedReader(r);
			t = System.nanoTime();
			System.out.print("Loading... 0");
			while ((l = b.readLine()) != null) {
				boolean success = B.addWarehouseItemByLine(l);
				if (success) {
					System.out.print(
						"\rLoading... " + (++i)
					);
				} else {
					bad = true;
				}
			}
			t = System.nanoTime() - t;
			s.close();
		} catch (IOException e) {
			if (s != null) {
				try {
					s.close();
				} catch (IOException z) {
					// Tough luck!
				}
			}
			System.out.println("\nFile error: " + e.getMessage());
		}
		System.out.println(
			"\nDone: " +
			(double) t / 1000000 +
			" milliseconds"
		);
		if (bad)
			System.out.println("Some records failed to load.");
	}
}
