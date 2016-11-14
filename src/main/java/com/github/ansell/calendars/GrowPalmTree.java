package com.github.ansell.calendars;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Create a JSON file suitable for importing into the Seasonal Calendars
 * application based on an in-memory representation.
 * 
 * @author Peter Ansell p_ansell@yahoo.com
 */
public class GrowPalmTree {

	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	private static final JsonFactory JSON_FACTORY = new JsonFactory(JSON_MAPPER);

	static {
		JSON_FACTORY.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
	}

	/**
	 * Private constructor for static only class
	 */
	private GrowPalmTree() {
	}

	/**
	 * Writes the given calendar object out to the {@link OutputStream}.
	 * 
	 * @param calendar
	 *            A {@link Map} containing {@link String} keys mapping to
	 *            objects containing the details about the calendar.
	 * @param output
	 *            The OutputStream that will contain the JSON representing the
	 *            calendar.
	 * @throws IOException
	 *             If there is an exception writing to the given output stream.
	 */
	public static void write(final Map<String, Object> calendar, final OutputStream output) throws IOException {
		final JsonGenerator jw = JSON_FACTORY.createGenerator(new OutputStreamWriter(output, StandardCharsets.UTF_8));
		jw.useDefaultPrettyPrinter();
		jw.writeObject(calendar);
	}

}
