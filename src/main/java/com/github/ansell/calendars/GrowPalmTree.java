package com.github.ansell.calendars;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.geotools.feature.FeatureCollection;
import org.geotools.geojson.feature.FeatureJSON;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;

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

	private static final String ORGANISATION = "organisation";
	private static final String ORGANISATION_NAME = "name";
	private static final String ORGANISATION_CONTACT_NAME = "contactName";
	private static final String ORGANISATION_DESCRIPTION = "orgDescription";

	private static final String REFERENCE = "reference";
	private static final String REFERENCE_LINK = "referenceLink";
	private static final String WHY = "why";
	private static final String SITES = "sites";
	private static final String SEASON_NAME_ENGLISH = "seasonNameEnglish";
	private static final String WEATHER_ICON = "weatherIcon";
	private static final String SEASON_NAME = "seasonName";
	private static final String SEASON_MONTHS = "seasonMonths";
	private static final String SEASON_DESCRIPTION = "description";
	private static final String FEATURES = "features";
	private static final String SEASONS = "seasons";
	private static final String CALENDAR_ID = "calendarId";

	private final Map<String, Object> results = new ConcurrentHashMap<>();

	/**
	 * Builder for Seasonal Calendars. <br>
	 * Accessed using static helper methods rather than this constructor, to
	 * provide flexibility for internal redevelopment.
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
		this.results.put(NAME, name);
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
		this.results.put(STATUS, status);
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
		this.results.put(DESCRIPTION, description);
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
		this.results.put(EXTERNAL_LINK, externalLink);
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
		this.results.put(HOW, how);
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
		this.results.put(IMAGE_URL, imageUrl);
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
		this.results.put(LICENSE_URL, licenseURL);
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
		this.results.put(LIMITATIONS, limitations);
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
		this.results.put(MULTIMEDIA, multimedia);
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
		this.results.put(ORGANISATION, organisationDetails);
		return this;
	}

	/**
	 * Set the reference for this calendar.
	 * 
	 * @param reference
	 *            The reference for this calendar.
	 * @return The builder object so that it can be fluently reused.
	 */
	public GrowPalmTree reference(String reference) {
		this.results.put(REFERENCE, reference);
		return this;
	}

	/**
	 * Set the referenceLink for this calendar.
	 * 
	 * @param referenceLink
	 *            The referenceLink for this calendar.
	 * @return The builder object so that it can be fluently reused.
	 */
	public GrowPalmTree referenceLink(String referenceLink) {
		this.results.put(REFERENCE_LINK, referenceLink);
		return this;
	}

	/**
	 * Set the why for this calendar.
	 * 
	 * @param why
	 *            The why for this calendar.
	 * @return The builder object so that it can be fluently reused.
	 */
	public GrowPalmTree why(String why) {
		this.results.put(WHY, why);
		return this;
	}

	public GrowPalmTree sites(String geoJson) {
		FeatureJSON fjson = new FeatureJSON();

		FeatureCollection<?, ?> fcol = null;
		try {
			// Verify that it is a supported structure by parsing it explicitly
			fcol = fjson.readFeatureCollection(new ByteArrayInputStream(geoJson.getBytes(StandardCharsets.UTF_8)));
		} catch (IOException e) {
			throw new UndeclaredThrowableException(e);
		}

		return sites(fcol);
	}

	public GrowPalmTree sites(FeatureCollection<?, ?> feature) {
		FeatureJSON fjson = new FeatureJSON();
		StringWriter tempWriter = new StringWriter();
		try {
			// Use GeoTools GeoJSON module to write out the features and then
			// parse back into a Map
			fjson.writeFeatureCollection(feature, tempWriter);
			Map<String, Object> fmap = read(
					new ByteArrayInputStream(tempWriter.toString().getBytes(StandardCharsets.UTF_8)));

			this.results.put(SITES, fmap);
		} catch (IOException e) {
			throw new UndeclaredThrowableException(e);
		}

		return this;
	}

	public GrowPalmTree season(String seasonNameEnglish, String weatherIcon, String seasonName, String seasonMonths,
			String description, List<Map<String, Object>> features) {
		Map<String, Object> nextSeason = new ConcurrentHashMap<>();

		nextSeason.put(SEASON_NAME_ENGLISH, seasonNameEnglish);
		nextSeason.put(WEATHER_ICON, weatherIcon);
		nextSeason.put(SEASON_NAME, seasonName);
		nextSeason.put(SEASON_MONTHS, seasonMonths);
		nextSeason.put(SEASON_DESCRIPTION, description);
		nextSeason.put(FEATURES, features);

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>) this.results.computeIfAbsent(SEASONS,
				k -> new ArrayList<Map<String, Object>>());
		list.add(nextSeason);

		return this;
	}

	public void build(OutputStream out) throws IOException {
		this.results.put(CALENDAR_ID, this.id.toString());
		write(this.results, out);
	}

}
