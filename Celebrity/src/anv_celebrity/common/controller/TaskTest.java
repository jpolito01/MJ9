package common.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class TaskTest extends CommonController {
	
	public void run()
	{
		System.out.println("Inside run method;");
		if(getCommonServices()!=null)
		{
			System.out.println("Object initilozed......");
			getCommonServices().getCelebrity_name();
		}
			
			
			

	}
}
