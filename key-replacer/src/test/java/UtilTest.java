import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilTest
{
	@Test
	public void replacerShouldWorkProperly()
	{
		assertEquals("negative parameter", "Unexpected ERROR at line -1",
				Util.formatter("Unexpected {0} at line {3}", "ERROR", 10, "INFO", -1));

		assertEquals("double parameter","Unexpected ERROR at line 0.2",
				Util.formatter("Unexpected {0} at line {3}", "ERROR", 10, "INFO", 0.2));

		assertEquals("nothing to format", "Unexpected something at some line",
				Util.formatter("Unexpected something at some line", "ERROR", 10, "INFO", -1));

		assertEquals("reversed parameter order", "Unexpected INFO at line 10",
				Util.formatter("Unexpected {2} at line {1}", "ERROR", 10, "INFO", -1));

		assertEquals("positive test","Unexpected ERROR at line 1",
				Util.formatter("Unexpected {0} at line {3}", "ERROR", 10, "INFO", 1));

		assertEquals("parameter not present in the list","Unexpected ERROR at line {100}",
				Util.formatter("Unexpected {0} at line {100}", "ERROR", 10, "INFO", 1));

		assertEquals("message starts with param","INFO: Unexpected ERROR at line {100}",
				Util.formatter("{2}: Unexpected {0} at line {100}", "ERROR", 10, "INFO", 1));

		assertEquals("message uses the same param twice","Unexpected ERROR caused by an ERROR",
				Util.formatter("Unexpected {0} caused by an {0}", "ERROR", 10, "INFO", 1));

		assertEquals("message contains only one param twice without space","INFOINFO",
				Util.formatter("{2}{2}", "ERROR", 10, "INFO", 1));

		assertEquals("message contains only one param twice with space","INFO INFO",
				Util.formatter("{2} {2}", "ERROR", 10, "INFO", 1));

		assertEquals("message contains only one nonexistent param twice with space","{10} {10}",
				Util.formatter("{10} {10}", "ERROR", 10, "INFO", 1));

		assertEquals("message starts with space and contains only two params and finishes with space"," ERROR ERROR ",
				Util.formatter(" {0} {0} ", "ERROR", 10, "INFO", 1));

		assertEquals("empty string for params","Empty  String test",
				Util.formatter("Empty {0} String test", "", "ERROR", 10, "INFO", 1));
	}

}
