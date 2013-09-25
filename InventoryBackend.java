import java.io.*;
import java.util.*;

public class InventoryBackend {
	private static int MAX_ARRAY = 1000000;
	private WarehouseItem[] array;
	private DSABinarySearchTree<String, WarehouseItem> bst;
	private int arrayUsed;
	public InventoryBackend() {
		array = new WarehouseItem[MAX_ARRAY];
		arrayUsed = 0;
	}
	public boolean addWarehouseItemByLine(String line) {
		StringTokenizer t = new StringTokenizer(line, ",");
		try {
			String key = t.nextToken();
			String brand = t.nextToken();
			String model = t.nextToken();
			int weightInKg = Integer.parseInt(t.nextToken());
			double price = Double.parseDouble(t.nextToken());
			array[arrayUsed++] = new WarehouseItem(
				key, brand, model, weightInKg, price
			);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean rebuildTree() {
		long time, good = 0, total = 0;
		bst = new DSABinarySearchTree<String, WarehouseItem>();
		time = System.nanoTime();
		System.out.println("");
		for (int i = 0; i < arrayUsed; i++) {
			total++;
			try {
				bst.insert(array[i].getKey(), array[i]);
				System.out.print(
					"\rLoading... " + (++good)
				);
			} catch (Exception e) {
				// Tough luck!
			}
		}
		time = System.nanoTime() - time;
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
}
