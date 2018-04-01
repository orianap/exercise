import java.util.Arrays;
import java.util.List;

public class Util
{

	public static String formatter(String format, Object... params)
	{
		//String newFormat = format;
//		for (int i = 0; i < params.length; i++)
//		{
//			newFormat = newFormat.replaceAll("\\{" + i + "\\}", String.valueOf(params[i]));
//		}
//		return newFormat;

		Automat a = new Automat(Arrays.asList(States.values()), States.verifyOpenBracket, format, params);
		return a.run();

	}
}
