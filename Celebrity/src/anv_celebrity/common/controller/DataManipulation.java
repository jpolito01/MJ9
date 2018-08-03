package common.controller;

public class DataManipulation {
	public String EncodeToHtml(String data)
	{
		String input=data;
		input=input.replace("\'", "&#39;");//&#39;&apos;

		input=input.replace("\"", "&quot;");
		input=input.replace("&", "&amp;");
		return input;
	}
}
