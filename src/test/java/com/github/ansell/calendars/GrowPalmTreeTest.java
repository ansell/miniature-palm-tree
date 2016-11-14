package com.github.ansell.calendars;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Map;

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

}
