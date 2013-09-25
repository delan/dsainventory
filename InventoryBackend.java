import java.io.*;
import java.util.*;

public class InventoryBackend {
	private static int MAX_ARRAY = 1000000;
	private WarehouseItem[] array;
	private int arrayUsed;
	private DSABinarySearchTree<String, WarehouseItem> bst;
	public InventoryBackend() {
		array = new WarehouseItem[MAX_ARRAY];
		arrayUsed = 0;
	}
	public WarehouseItem[] getArray() {
		return array;
	}
	public int getArrayUsed() {
		return arrayUsed;
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
				if (++good % 100 == 0)
					System.out.print(
						"\rLoading... " + good
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
	public WarehouseItem searchArray(String key) {
		for (int i = 0; i < arrayUsed; i++)
			if (key.equals(array[i].getKey()))
				return array[i];
		return null;
	}
	public WarehouseItem searchTree(String key) {
		try {
			return bst.find(key);
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	public void sortArray() {
		mergeSort(0, arrayUsed - 1);
	}
	public void mergeSort(int left, int right) {
		int subarrayLength = 1 + right - left;
		int mid = left + subarrayLength / 2 - 1;
		if (subarrayLength <= 1)
			return;
		mergeSort(left, mid);
		mergeSort(mid + 1, right);
		merge(left, mid, right);
	}
	private void merge(int left, int mid, int right) {
		int subarrayLength = 1 + right - left;
		WarehouseItem[] temp = new WarehouseItem[subarrayLength];
		int i = left;
		int j = mid + 1;
		int k = 0;
		while (i <= mid && j <= right) {
			if (array[i].getKey().
				compareTo(array[j].getKey()) < 0)
				temp[k++] = array[i++];
			else
				temp[k++] = array[j++];
		}
		while (i <= mid)
			temp[k++] = array[i++];
		while (j <= right)
			temp[k++] = array[j++];
		for (int x = 0; x < subarrayLength; x++)
			array[x + left] = temp[x];
	}
}
