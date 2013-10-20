package ie.paco.ir;

public interface RecordReader {

	public boolean openResource(String[] config);
	public String[] nextRecord();
	
}
