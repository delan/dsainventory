import java.io.*;
import java.util.*;

public class InventoryApplication {
	public static InventoryBackend backend;
	public static void main(String[] args) {
		backend = new InventoryBackend();
		System.out.println("________________________________________");
		System.out.println("");
		System.out.println("   Data Structures and Algorithms 120");
		System.out.println("    Inventory application assignment");
		System.out.println("       Delan Azabani (#17065012)");
		System.out.println("________________________________________");
		runMainMenu();
	}
	public static int readInt() {
		InputStreamReader reader = new InputStreamReader(System.in);
		StreamTokenizer token = new StreamTokenizer(reader);
		String message = "\nInvalid: not an integer";
		while (true) {
			printPrompt();
			try {
				int r;
				if (token.nextToken() == token.TT_NUMBER) {
					r = new Double(token.nval).intValue();
					return r;
				} else {
					System.out.println(message);
				}
			} catch (IOException e) {
				System.out.println(message);
			}
		}
	}
	public static String readLine() {
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader buffer = new BufferedReader(reader);
		while (true) {
			printPrompt();
			try {
				return buffer.readLine();
			} catch (IOException e) {
				System.out.println("\nInvalid: try again");
			}
		}
	}
	private static void runMainMenu() {
		boolean	done1 = false,
			done2 = false;
		while (true) {
			printMainMenu();
			switch (readInt()) {
			case 1:
				if (!done1)
					done1 = loadRecordsIntoArray();
				else
					System.out.println(
						"\nData already loaded."
					);
				break;
			case 2:
				if (done1)
					done2 = backend.rebuildTree();
				else
					System.out.println(
						"\nLoad the array first!"
					);
				break;
			case 3:
				if (done1)
					searchArray();
				else
					System.out.println(
						"\nLoad the array first!"
					);
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
		System.out.println("(1) Load records into array");
		System.out.println("(2) Build binary search tree from array");
		System.out.println("(3) Search array");
		System.out.println("(4) Search tree");
		System.out.println("(5) Save data to file");
		System.out.println("(0) Quit");
	}
	private static void printPrompt() {
		System.out.print("\n> ");
	}
	private static boolean loadRecordsIntoArray() {
		String filename;
		FileInputStream stream = null;
		long time = 0, good = 0, total = 0;
		System.out.println("\nEnter file name:");
		filename = readLine();
		System.out.println("");
		try {
			String line;
			InputStreamReader reader;
			BufferedReader buffer;
			stream = new FileInputStream(filename);
			reader = new InputStreamReader(stream);
			buffer = new BufferedReader(reader);
			time = System.nanoTime();
			while ((line = buffer.readLine()) != null) {
				total++;
				if (backend.addWarehouseItemByLine(line)) {
					System.out.print(
						"\rLoading... " + (++good)
					);
				}
			}
			time = System.nanoTime() - time;
			stream.close();
		} catch (IOException e) {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException z) {
					// Tough luck!
				}
			}
			System.out.println("\nFile error: " + e.getMessage());
		}
		if (good == 0) {
			System.out.println("Failure: no records loaded.");
			return false;
		} else {
			System.out.println(
				"\nSuccess: " +
				(double) time / 1000000 +
				" milliseconds"
			);
			return true;
		}
	}
	private static boolean searchArray() {
		String key;
		WarehouseItem i;
		boolean good;
		long time = 0;
		System.out.println("\nEnter key:");
		key = readLine();
		System.out.println("");
		time = System.nanoTime();
		i = backend.searchArray(key);
		time = System.nanoTime() - time;
		if (i != null) {
			System.out.println(i.toString(true));
			good = true;
		} else {
			System.out.println("Failure: no item found with key.");
			good = false;
		}
		System.out.println(
			"\nTime elapsed: " +
			(double) time / 1000000 +
			" milliseconds"
		);
		return good;
	}
}
