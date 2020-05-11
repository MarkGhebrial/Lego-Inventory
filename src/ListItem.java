public class ListItem {
	/**
	 * Container class to represent an item in the inventory of parts
	 */
	
	String name;
	Colors color;
	int qty;
	
	public ListItem (String name, Colors color, int qty) {
		this.name = name;
		this.color = color;
		this.qty = qty;
	}
	
	public ListItem (String values) {
		String[] vals = values.split("\t");
		
		name = vals[0];
		color = Colors.valueOf(vals[1]);
		qty = Integer.parseInt(vals[2]);
	}
	
	public String[] getValues () {
		String[] vals = {name, color.toString(), Integer.toString(qty)};
		return vals;
	}
	
	public boolean equals (ListItem item) {
		return (name.toUpperCase().equals(item.name.toUpperCase())) && (color.equals(item.color));
	}
	
	public String toString () {
		return name + "\t" + color.toString() + "\t" + Integer.toString(qty);
	}
}
