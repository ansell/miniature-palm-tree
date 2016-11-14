package com.github.ansell.calendars;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
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
	private static final String ORGANISATION_NAME = "name";
	private static final String ORGANISATION_CONTACT_NAME = "contactName";
	private static final String ORGANISATION_DESCRIPTION = "orgDescription";

	static {
		JSON_FACTORY.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
	}

	private final UUID id;
	private static final String NAME = "name";
	private static final String STATUS = "status";
	private static final String DESCRIPTION = "description";
	private static final String EXTERNAL_LINK = "externalLink";
	private static final String HOW = "how";
	private static final String IMAGE_URL = "imageUrl";
	private static final String LICENSE_URL = "licenseURL";
	private static final String LIMITATIONS = "limitations";
	private static final String MULTIMEDIA = "multimedia";
	private final Map<String, String> results = new ConcurrentHashMap<>();

	/**
	 * Builder for Seasonal Calendars.
	 */
	private GrowPalmTree(UUID id) {
		this.id = id;
	}

	/**
	 * Create a calendar with a new id.
	 * 
	 * @return A builder object that can be used to create a calendar.
	 */
	public static GrowPalmTree builder() {
		return new GrowPalmTree(UUID.randomUUID());
	}

	/**
	 * Create a calendar with the given id.
	 * 
	 * @param id
	 *            The id to use with the calendar.
	 * @return A builder object that can be used to create a calendar.
	 */
	public static GrowPalmTree builder(UUID id) {
		return new GrowPalmTree(id);
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

	@SuppressWarnings("unchecked")
	public static Map<String, Object> read(final InputStream input) throws IOException {
		final JsonParser jp = JSON_FACTORY.createParser(new InputStreamReader(input, StandardCharsets.UTF_8));
		return (Map<String, Object>) jp.readValueAs(Map.class);
	}

	/**
	 * Set the name for this calendar.
	 * 
	 * @param name
	 *            The name for this calendar.
	 * @return The builder object so that it can be fluently reused.
	 */
	public GrowPalmTree name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Set the status for this calendar.
	 * 
	 * @param status
	 *            The status, published or unpublished.
	 * @return The builder object so that it can be fluently reused.
	 */
	public GrowPalmTree status(String status) {
		this.status = status;
		return this;
	}

	/**
	 * Set the description for this calendar.
	 * 
	 * @param description
	 *            The description for this calendar.
	 * @return The builder object so that it can be fluently reused.
	 */
	public GrowPalmTree description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Set the externalLink for this calendar.
	 * 
	 * @param externalLink
	 *            The externalLink for this calendar.
	 * @return The builder object so that it can be fluently reused.
	 */
	public GrowPalmTree externalLink(URL externalLink) {
		this.externalLink = externalLink;
		return this;
	}

	/**
	 * Set the how for this calendar.
	 * 
	 * @param how
	 *            The how for this calendar.
	 * @return The builder object so that it can be fluently reused.
	 */
	public GrowPalmTree how(String how) {
		this.how = how;
		return this;
	}

	/**
	 * Set the imageUrl for this calendar.
	 * 
	 * @param imageUrl
	 *            The imageUrl for this calendar.
	 * @return The builder object so that it can be fluently reused.
	 */
	public GrowPalmTree imageURL(URL imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	/**
	 * Set the licenseURL for this calendar.
	 * 
	 * @param licenseURL
	 *            The licenseURL for this calendar.
	 * @return The builder object so that it can be fluently reused.
	 */
	public GrowPalmTree licenseURL(URL licenseURL) {
		this.licenseURL = licenseURL;
		return this;
	}

	/**
	 * Set the limitations for this calendar.
	 * 
	 * @param limitations
	 *            The limitations for this calendar.
	 * @return The builder object so that it can be fluently reused.
	 */
	public GrowPalmTree limitations(String limitations) {
		this.limitations = limitations;
		return this;
	}

	/**
	 * Set the multimedia for this calendar.
	 * 
	 * @param multimedia
	 *            The multimedia for this calendar.
	 * @return The builder object so that it can be fluently reused.
	 */
	public GrowPalmTree multimedia(String multimedia) {
		this.multimedia = multimedia;
		return this;
	}

	/**
	 * Set the organisation for this calendar.
	 * 
	 * @param organisation
	 *            The organisation for this calendar.
	 * @return The builder object so that it can be fluently reused.
	 */
	public GrowPalmTree organisation(String organisationName, String contactName, String description) {
		final Map<String, String> organisationDetails = new HashMap<>();
		organisationDetails.put(ORGANISATION_NAME, organisationName);
		organisationDetails.put(ORGANISATION_CONTACT_NAME, contactName);
		organisationDetails.put(ORGANISATION_DESCRIPTION, description);
		this.organisation = organisationDetails;
		return this;
	}

}
