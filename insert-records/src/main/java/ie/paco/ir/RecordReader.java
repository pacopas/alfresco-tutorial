package ie.paco.ir;

import java.io.IOException;

public interface RecordReader {

	public boolean openResource(String[] config) throws IOException;
	public String[] nextRecord() throws IOException;
	
}
