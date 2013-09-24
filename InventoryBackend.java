import java.io.*;
import java.util.*;

public class InventoryBackend {
	private static int MAX_ARRAY = 1000000;
	private WarehouseItem[] array;
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
}
