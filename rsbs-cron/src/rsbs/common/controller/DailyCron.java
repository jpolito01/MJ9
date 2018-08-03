package common.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Properties;
import java.util.Set;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import common.model.AdminModel;
import common.services.CommonServices;

@Configuration
@EnableScheduling
public class DailyCron {
	@Autowired
	private CommonServices commonServices;

	public CommonServices getCommonServices() {
		return commonServices;
	}

	public void setCommonServices(CommonServices commonServices) {
		this.commonServices = commonServices;
	}
	
	@Scheduled(cron = "0 5 2 * * *")
	public void sendDailyNewsLetter() {
		// SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		//int checkforToday = getCommonServices().checkforRepeatCount();
		//System.out.println("checkforToday=" + checkforToday);
		//if (checkforToday == 0) {
			System.out.println("insideeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			// Insert celebrity of the day
			int totalCelebCount = getCommonServices().getTotalCelebrityCount();
			System.out.println("counttt+ "+totalCelebCount);
			List<AdminModel> trending_count = getCommonServices().getTrending_celebrities1(totalCelebCount);
			for (AdminModel trending_count1 : trending_count) {
				int celeb_id = trending_count1.getCelebrity_id();
				getCommonServices().removeCelebrityoftheDay();
				System.out.println("celeb id "+celeb_id);
				getCommonServices().insertCelebrityoftheDay(celeb_id);
			}
			// Send daily updates
			//int checkforToday1 = getCommonServices().checkforRepeatCount();
			//System.out.println("checkforToday1=" + checkforToday1);
			//if (checkforToday1 == 1) {
				List<AdminModel> followed_celebrities = commonServices
						.getFollowers();
				for (AdminModel followed_celebrities1 : followed_celebrities) {
					int user_id = followed_celebrities1.getUser_id();
					System.out.println("Follower=" + user_id);
					String celebrityNewsLetterString = "";
					List<AdminModel> followedCelebrityIds = commonServices
							.getCelebrityListByFollower(user_id);
					for (AdminModel followedCelebrityIds1 : followedCelebrityIds) {
						System.out.println("Celebrity_id="
								+ followedCelebrityIds1.getCelebrity_id());

						String btnColor = "";
						if (followedCelebrityIds1.getParty_id() == 1) {
							btnColor = "red1";
						}
						if (followedCelebrityIds1.getParty_id() == 2) {
							btnColor = "blue1";
						}
						String celebrityNewsLetterHeader = "<tr><td align='center' valign='top' style='padding-bottom:10px;'><table border='0' cellpadding='0' cellspacing='0' width='600' id='templateContainer' style='margin-top:15px;'>"
								+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='610' id='templateHeader'>"
								+ "<tr><td valign='top' class='headerContainer' style='padding-top:8px; padding-bottom:8px;'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnImageBlock' style='min-width:100%;'>"
								+ "<tbody class='mcnImageBlockOuter'><tr><td valign='top' style='padding:5px' class='mcnImageBlockInner'><table align='left' width='100%' border='0' cellpadding='0' cellspacing='0' class='mcnImageContentContainer' style='min-width:100%;'>"
								+ "<tbody><tr><td valign='top' class='mcnTextContent' style='color: #222222;font-family:Roboto;font-size: 16px;line-height: 100%;vertical-align:middle;padding: 0 20px;'>"
								+ followedCelebrityIds1.getCelebrity_name()
								+ "</td><td class='mcnImageContent' valign='top' align='right' style='padding-right: 8px; padding-left: 9px; padding-top: 0; padding-bottom: 0;'>"
								+ "<button type='submit' class='"
								+ btnColor
								+ " btn-follow' name='celebrity_id' value='"
								+ followedCelebrityIds1.getCelebrity_id()
								+ "'>See all</button></td></tr></tbody></table>"
								+ "</td></tr></tbody></table></td></tr></table></td></tr></table></td></tr><tr id='myCheck'><td align='center' valign='top'>"
								+ "<table border='0' cellpadding='0' cellspacing='0' width='600' id='templateColumns'>"
								+ "<tr>";
						List<AdminModel> celebrity_news = commonServices
								.getTodaysNewsforCelebrity(followedCelebrityIds1
										.getCelebrity_id());
						System.out.println("size" + celebrity_news.size());
						if (celebrity_news.size() > 0) {
							celebrityNewsLetterString = celebrityNewsLetterString
									+ celebrityNewsLetterHeader;

							for (int j = 0; j <= 1; j++) {
								String boxSide = "";
								String boxStyle = "";
								if (j == 0) {
									boxSide = "left";
									boxStyle = "style='padding-right:10px;'";
								} else {
									boxSide = "right";
								}
								int tempVar = j;
								int i = 0;

								String innercelebrityNewsLetterHeader = "<td align='"
										+ boxSide
										+ "' valign='top' "
										+ boxStyle
										+ ">"
										+ "<table border='0' cellpadding='0' cellspacing='0' width='300px' id='templateColumns'>";
								celebrityNewsLetterString = celebrityNewsLetterString
										+ innercelebrityNewsLetterHeader;

								for (AdminModel celebrity_news1 : celebrity_news) {
									if (tempVar == i) {
										String celebrityNewsLetterBody = "";

										String post_source = "";
										String imageStyle = "";
										if (celebrity_news1.getNews_source()
												.equalsIgnoreCase("Facebook")) {
											post_source = "fb.png";
											imageStyle = "height:150px;width:150px;padding-top:10px";
										} else if (celebrity_news1
												.getNews_source()
												.equalsIgnoreCase("Instagram")) {
											post_source = "instagram.png";
											imageStyle = "width:100%;padding-top:10px;";
										} else if (celebrity_news1
												.getNews_source()
												.equalsIgnoreCase("Twitter")) {
											post_source = "twitter.png";
											imageStyle = "width:100%;padding-top:10px";
										} else {
											post_source = "news.png";
											imageStyle = "height:150px;width:150px;padding-top:10px";
										}
										String tempHeader = "";
										String postImage = "";
										/*
										 * if(!celebrity_news1.getImage_url().equals
										 * ("null") &&
										 * !celebrity_news1.getImage_url
										 * ().equals("") &&
										 * !celebrity_news1.getImage_url
										 * ().equals("unknown")){
										 * postImage="<img alt='' src='"
										 * +celebrity_news1
										 * .getImage_url()+"' width='150px' style='"
										 * +imageStyle+"' class='mcnImage'>";
										 * 
										 * }else{
										 * 
										 * }
										 */
										String imgUrl = celebrity_news1
												.getImage_url();
										if (imgUrl != "" && imgUrl != null
												&& imgUrl != "unknown"
												&& imgUrl != "null") {
											postImage = "<img alt='' src='"
													+ imgUrl
													+ "' width='150px' style='"
													+ imageStyle
													+ "' class='mcnImage'>";
											// System.out.println("in image");
										}
										String newsTitle = celebrity_news1
												.getNews_title();
										newsTitle=replaceSpecialCharacters(newsTitle);
										newsTitle = newsTitle.replace("?", "");
										celebrityNewsLetterBody = tempHeader
												+ " <tr><td align='left' valign='top' class='columnsContainer' width='300px' style='padding-top:4px;padding-bottom:4px;'>"
												+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' id='templateLeftColumn'><tr><td align='center' valign='top' style='padding-top:4px; padding-bottom:4px;'>"
												+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' class='templateColumn'><tr><td valign='top' class='leftColumnContainer'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnCaptionBlock'>"
												+ "<tbody class='mcnCaptionBlockOuter'><tr><td class='mcnCaptionBlockInner' valign='top' style='padding:9px;'>"
												+ "<table align='left' border='0' cellpadding='0' cellspacing='0' class='mcnCaptionBottomContent' width='false'><tbody><tr style='border-bottom: 2px solid #64f964;'><td class='mcnCaptionBottomImageContent' align='left' valign='top' style='padding:0 9px 9px 9px;width:10%'>"
												+ "<img alt='' src='http://www.redsaidbluesaid.com/resources/assets/images/"
												+ post_source
												+ "' width='30' style='max-width:30px;' class='mcnImage'>"
												+ "</td><td class='mcnCaptionBottomImageContent' align='left' valign='top' style='padding:0 9px 9px 0;width:80%'>"
												+ newsTitle
												+ "</td></tr><tr></tr></tbody></table><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnButtonBlock' style='min-width:100%;'>"
												+ "<tbody class='mcnButtonBlockOuter'><tr><td align='center' valign='middle' class='mcnButtonContent'>"
												+ postImage
												+ "</td></tr></tbody></table></td></tr></tbody></table></td></tr></table></td></tr></table></td></tr>";

										celebrityNewsLetterString = celebrityNewsLetterString
												+ celebrityNewsLetterBody;
										tempVar = tempVar + 2;

									}
									i++;
								}
								String innercelebrityNewsLetterFooter = "</table></td>";
								celebrityNewsLetterString = celebrityNewsLetterString
										+ innercelebrityNewsLetterFooter;
							}

							String celebrityNewsLetterFooter = "</tr></table></td></tr>";
							celebrityNewsLetterString = celebrityNewsLetterString
									+ celebrityNewsLetterFooter;

						}

					}
					if (celebrityNewsLetterString != "") {
						Map<String, String> input = new HashMap<String, String>();
						input.put("celebrityNewsLetterString",
								celebrityNewsLetterString);
						//String[] mailFromAddress = { "kedar.khire@anveshak.com"};
						 String[] mailFromAddress = { followed_celebrities1.getUser_name() };
						String htmlText = readEmailFromHtml(
								"/usr/share/apache-tomcat-7.0.75/webapps/rsbs-cron/WEB-INF/jsp/Common/dailyNewsletterTemplate.html",
								input);
						// EmailController email1 = new EmailController();
						String subject = "Red Said Blue Said - Daily updates";
						//int checkforToday2 = getCommonServices().checkforRepeatCount();
						//System.out.println("checkforToday2=" + checkforToday2);
						//if (checkforToday2 == 1) {
							// email1.emailSending(mailFromAddress, htmlText,subject);
							emailSending(mailFromAddress, htmlText, subject);
							System.out.println("In mail...");
						//}
						input.clear();
					}
				}
			//}
		//}
		return;
	}

	// Method to replace the values for keys
	protected String readEmailFromHtml(String filePath,
			Map<String, String> input) {
		String msg = readContentFromFile(filePath);
		Set<Entry<String, String>> entries = input.entrySet();
		try {
			for (Map.Entry<String, String> entry : entries) {
				msg = msg.replace(entry.getKey().trim(), entry.getValue()
						.trim());
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			entries.clear();
		}
		return msg;
	}
	String readContentFromFile(String fileName)
	{
	    StringBuffer contents = new StringBuffer();
	    
	    try {
	      //use buffering, reading one line at a time
	      BufferedReader reader =  new BufferedReader(new FileReader(fileName));
	      try {
	        String line = null; 
	        while (( line = reader.readLine()) != null){
	          contents.append(line);
	          contents.append(System.getProperty("line.separator"));
	        }
	      }
	      finally {
	          reader.close();
	      }
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
	    return contents.toString();
	}

	public Void emailSending(String[] email, String htmlText, String subject) {
		// Object to = email;
		final String from = "redsaidbluesaid01@gmail.com";
		final String password = "Impact1!";
		String host = "smtp.googlemail.com";
		Properties properties = System.getProperties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.host", host);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		Session session = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from, password);
					}
				});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			Address[] ia = new InternetAddress[email.length];
			int i = 0;
			for (String address : email) {
				ia[i] = new InternetAddress(address);
				i++;
			}

			message.addRecipients(RecipientType.TO, ia);
			// message.addRecipient(Message.RecipientType.TO, new
			// InternetAddress((String) to));
			message.setSubject(subject);
			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(htmlText, "text/html");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
		return null;
	}
	
	public String replaceSpecialCharacters(String title)
	{
		String c="";
		if(title!=null && title!="")
		{
			c= title;
	        //Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
	        
	        Pattern pt = Pattern.compile("[^'_/.,:;#-$\"A-Za-z0-9 ]");
	        Matcher match= pt.matcher(c);
	        while(match.find())
	        {
	            String s= match.group();
	        c=c.replaceAll("\\"+s, "");
	        }
	        
		}
        return c;
	}
}