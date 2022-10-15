public abstract class Item {
	protected static StringBuffer indent = new StringBuffer();

	public abstract String print();

	public abstract float getWeight();
}