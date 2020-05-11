import java.io.*;
import java.util.ArrayList;

public class ListKeeper {
	private ArrayList<ListItem> partsList = new ArrayList<ListItem>();
	
	private File inventoryFile;
	private BufferedReader buffReader;
	private FileWriter writer;
	private PrintWriter printWriter;
	
	public ListKeeper () throws IOException {
		inventoryFile = new File("LEGOS.txt");
		
		FileReader reader = null;
		try {
			reader = new FileReader(inventoryFile);
		} catch (FileNotFoundException e) {
			System.out.println("Creating new file");
			PrintWriter printWriter = new PrintWriter("LEGOS.txt", "UTF-8"); //Create a new file
			reader = new FileReader(inventoryFile);
		}
		buffReader = new BufferedReader(reader);
		
		loadFromDisk();
		
		writer = null;
		try {
			writer = new FileWriter(inventoryFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		printWriter = new PrintWriter(writer);
		
		saveToDisk();
	}
	
	public void loadFromDisk () throws IOException {
		while (buffReader.ready()) {
			partsList.add(new ListItem(buffReader.readLine()));
		}
	}
	
	public void saveToDisk () {
		try {
			writer = new FileWriter(inventoryFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		printWriter = new PrintWriter(writer);
		for(int i = 0; i < partsList.size(); i++) {
			printWriter.print(partsList.get(i).toString() + "\n");
		}
		printWriter.close();
	}
	
	public void addItem (ListItem item) {
		//The list is not sorted at all, so I use a linear search
		Integer match = null;
		for(int i = 0; i < partsList.size(); i++) {
			if (partsList.get(i).equals(item)) {
				match = i;
				break;
			}
		}
		if (match != null) {
			partsList.get(match).qty += item.qty;
			if (partsList.get(match).qty <= 0) {
				partsList.remove(partsList.get(match));
				System.out.println(partsList);
			}
		} else {
			partsList.add(item);
		}
	}
	
	public void increaseQty (int index, int deltaQty) {
		partsList.get(index).qty += deltaQty;
	}
	
	public int getLength () {
		return partsList.size();
	}
	
	public ListItem getItem(int index) {
		return partsList.get(index);
	}
	
	public void setItem(int index, ListItem item) {
		partsList.set(index, item);
	}
}
