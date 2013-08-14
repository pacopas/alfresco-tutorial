package ie.paco.alfresco.util;

import java.text.Format;

public interface FormatFactory {

	/**
	 * Return a {@code Format} instance
	 * 
	 * @param key implementations will know what this {@code key} is. Maybe a pattern, maybe just a name
	 * @return
	 */
	public Format getFormat(String key);
}
