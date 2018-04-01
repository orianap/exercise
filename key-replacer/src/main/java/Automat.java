import java.util.List;

public class Automat
{
	private States currentState;
	private List<States> allStates;
	private String input;
	private Object[] params;

	public Automat (List <States> allStates, States initialState, String input, Object... params )
	{
		this.currentState = initialState;
		this.allStates = allStates;
		this.input = input;
		this.params = params;
	}

	protected States transitionFunction(char currentChar, List <States> posibleStates)
	{
		States nextState = null;

		if(currentState == States.verifyOpenBracket)
		{
			if(currentChar == '{')
			{
				nextState = States.verifyDigit;
			}
			else
			{
				nextState = States.verifyOpenBracket;
			}
		}
		else if (currentState == States.verifyDigit)
		{
			if (Character.isDigit(currentChar))
			{
				nextState = States.verifyDigit;
			}
			else
			{
				nextState = States.verifyOpenBracket;
			}
		}

		if (!posibleStates.contains(nextState)) throw new RuntimeException("This shouldn't happen");

		return nextState;
	}

	public String run()
	{
		//reduce ifs

		StringBuilder result = new StringBuilder();
		StringBuilder buffer = new StringBuilder();

		for(int i = 0; i < input.length(); i++)
		{
			char currentChar = input.charAt(i);
			currentState = transitionFunction(currentChar, allStates);
			if(currentState == States.verifyOpenBracket)
			{
				if(buffer.length()==0)
				{
					result.append(currentChar);
				}
				else
				{
					buffer.append(currentChar);
					if(buffer.toString().matches("^\\{\\d+\\}$"))
					{
						int digit = Integer.parseInt(buffer.substring(1,buffer.length()-1));
						if(digit < params.length)
						{
							result.append(params[digit]);
						}
						else
						{
							result.append(buffer);
						}

					}
					else
					{
						result.append(buffer);
					}
					buffer.setLength(0);
				}
			}
			else if (currentState == States.verifyDigit)
			{
				buffer.append(currentChar);
			}
		}

		return result.toString();
	}
}
