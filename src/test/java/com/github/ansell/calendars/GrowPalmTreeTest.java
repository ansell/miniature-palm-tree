package com.github.ansell.calendars;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link GrowPalmTree}.
 * 
 * @author Peter Ansell p_ansell@yahoo.com
 */
public class GrowPalmTreeTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.github.ansell.calendars.GrowPalmTree#write(java.util.Map, java.io.OutputStream)}.
	 */
	@Test
	public final void testWrite() throws Exception {
		ByteArrayOutputStream output = new ByteArrayOutputStream(8096);
		GrowPalmTree.write(Collections.singletonMap("test", "value"), output);
		byte[] outputBytes = output.toByteArray();
		assertEquals(22, outputBytes.length);
	}

	/**
	 * Test method for
	 * {@link com.github.ansell.calendars.GrowPalmTree#read(java.io.InputStream)}.
	 */
	@Test
	public final void testRead() throws Exception {
		Map<String, Object> calendar = GrowPalmTree
				.read(this.getClass().getResourceAsStream("/com/github/ansell/calendars/sc-example.json"));
		assertNotNull(calendar);
		assertEquals(16, calendar.size());
	}

	@Test
	public final void testFluentBuilder() throws Exception {
		List<Map<String, Object>> features = new ArrayList<>();

		Map<String, Object> feature = new ConcurrentHashMap<>();
		feature.put("featureNameEnglish", "English feature name");
		feature.put("speciesName", "Crocodylus porosus Schneider");
		feature.put("speciesLink", "http://bie.ala.org.au/species/Crocodylus+porosus");
		feature.put("featureName", "Danggalaba - Saltwater Croc");
		feature.put("description", "Danggalaba (Saltwater Crocodile) are laying their eggs. "
				+ "Damibila (Barramundi) are multiplying upstream on the floodplains.\n\n"
				+ "Saltwater crocodiles (Crocodylus porosus) can be very aggressive and territorial. "
				+ "They build nests from grass and leaves, and lay around 50 eggs inside the warm decomposing vegetation. "
				+ "Saltwater Crocodiles inhabit mangrove swamps, river mouths and coastal marshes of northern Australia, and are strictly carnivorous. "
				+ "Barramundi (Lates calcarifer) can live in both fresh and salt water. "
				+ "Low-lying areas become flooded during monsoonal rains and provide an ideal habitat for juvenile Barramundi.");

		ArrayList<Map<String, Object>> thumbImages = new ArrayList<Map<String, Object>>();
		Map<String, Object> thumbImage1 = Collections.singletonMap("url",
				"http://images.ala.org.au/store/1/5/4/e/d2480076-2a8f-4750-8990-0344a90be451/thumbnail");
		thumbImages.add(thumbImage1);
		Map<String, Object> thumbImage2 = Collections.singletonMap("url",
				"http://images-dev.ala.org.au/data/images/store/9/d/c/5/4d1a6d32-61c5-478c-b7bd-46b9a3ad5cd9/thumbnail");
		thumbImages.add(thumbImage2);
		Map<String, Object> thumbImage3 = Collections.singletonMap("url",
				"http://images-dev.ala.org.au/data/images/store/d/9/e/6/148127ca-efa7-456d-a67d-479bde9c6e9d/thumbnail");
		thumbImages.add(thumbImage3);
		feature.put("thumbImages", thumbImages);
		features.add(feature);

		String geoJson = "{ \"features\" : [ "
				+ "{ \"geometry\" : { \"coordinates\" : [ 128.14453125, -21.0434912168035 ], \"type\" : \"Point\" }, "
				+ "\"type\" : \"Feature\", \"properties\" : "
				+ "{ \"point_type\" : \"Circle\", \"radius\" : 877764.340445275 } } ], "
				+ "\"type\" : \"FeatureCollection\" }";

		ByteArrayOutputStream out = new ByteArrayOutputStream(8096);
		GrowPalmTree.builder(UUID.fromString("c10b79cf-e9cd-4979-b7e3-c75cf4727275")).name("Larrakia")
				.status("published").description("Test1")
				.imageURL(
						new URL("http://www.larrakia.csiro.au/images/calendars/CSIRO_Gulumoerrgin_(Larrakia)_seasonal-calendar_A3(print).jpg"))
				.organisation("Organisation Name", "URL", "asdsadsa")
				.season("Test", "rainy", "Dalay", "January", "Monsoon Season", features).sites(geoJson).build(out);
		String jsonOutput = out.toString("UTF-8");
		System.out.println(jsonOutput);
	}

}
