package com.exdev.cc.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.exdev.cc.web.CCApp;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CleanCarUtils {

	private static final String MESSAGING_AUTH_KEY = CCApp.CONFIG.getProperty("MESSAGING_AUTH_KEY");
	private static final String MESSAGING_HTTP_API = CCApp.CONFIG.getProperty("MESSAGING_HTTP_API");
	private static final String SMS_TAG_NAME = CCApp.CONFIG.getProperty("SMS_TAG_NAME");
	private static final String SMS_USER_PWD = CCApp.CONFIG.getProperty("SMS_USER_PWD");
	private static final String SMS_USERNAME = CCApp.CONFIG.getProperty("SMS_USER_NAME");
	private static final String SMTP = CCApp.CONFIG.getProperty("SMTP");
	private static final String BAREEQ_EMAIL_PWD = CCApp.CONFIG.getProperty("BAREEQ_EMAIL_PWD");
	private static final String BAREEQAPP_EMAIL_ID = CCApp.CONFIG.getProperty("BAREEQAPP_EMAIL_ID");
	private static final String BASE_SMS_API = CCApp.CONFIG.getProperty("BASE_SMS_API");
	private static final String SEND_SMS_API = CCApp.CONFIG.getProperty("SEND_SMS_API");

	private static final RestTemplate RT = new RestTemplate();
	static {
		RT.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		RT.getInterceptors().add(new ClientHttpRequestInterceptor() {

			@Override
			public ClientHttpResponse intercept(HttpRequest req, byte[] data, ClientHttpRequestExecution exec)
					throws IOException {
				System.out.println("Intercept......");
				System.out.println(new String(data));
				System.out.println("Execute......");
				return exec.execute(req, data);
			}
		});
	}

	public CleanCarUtils() {
		// TODO Auto-generated constructor stub
	}

	public static void sendEMail(String to, String subject, String body) throws Exception {
		if (StringUtils.isEmpty(to))
			return;
		Email email = new HtmlEmail();
		email.setStartTLSEnabled(true);
		email.addTo(to);
		email.setSubject(subject);
		email.setSentDate(new Date());
		email.setAuthentication(BAREEQAPP_EMAIL_ID, BAREEQ_EMAIL_PWD);
		email.setMsg(body);
		email.setHostName(SMTP);
		email.setFrom(BAREEQAPP_EMAIL_ID);

		email.send();
	}

	public static String sendSMS(String mobileNo, String message) {

		SMS sms = new SMS();
		sms.setEnableDR("false");
		sms.setUsername(SMS_USERNAME);
		sms.setMessage(message);
		sms.setRecepientNumber(mobileNo);
		sms.setPassword(SMS_USER_PWD);
		sms.setTagname(SMS_TAG_NAME);
		sms.setSendDateTime("0");
		sms.setReplacementList("");
		sms.setVariableList("");
		String resp = RT.postForObject(SEND_SMS_API, sms, String.class);

		return resp;

	}

	public static void sendNotification(Map<String, String> data,String path, String to) throws Exception {
		sendNotification(new Notification(data,path, to));
	}

	public static void sendNotification(Notification notif) throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", MESSAGING_AUTH_KEY);
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");
		HttpEntity<Notification> notifEntity = new HttpEntity<Notification>(notif, headers);

		System.out.println(notifEntity);
		ResponseEntity<String> response = RT.exchange(MESSAGING_HTTP_API, HttpMethod.POST, notifEntity, String.class);
		System.out.println(response);

		if (response.getStatusCode().value() != 200) {
			throw new Exception("Failed : HTTP error code : " + response.getStatusCode().value());
		}

		String output = response.getBody();
		System.out.println("Output from Server .... \n");
		System.out.println(output);
	}

	public static final String md5(final String toEncrypt) {
		try {
			final MessageDigest digest = MessageDigest.getInstance("md5");
			digest.update(toEncrypt.getBytes());
			final byte[] bytes = digest.digest();
			final StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(String.format("%02X", bytes[i]));
			}
			return sb.toString().toLowerCase();
		} catch (Exception exc) {
			return ""; // Impossibru!
		}
	}

	private static class SMS {

		private String username, password, tagname, recepientNumber, variableList, replacementList, message,
				sendDateTime, enableDR;

		@JsonProperty("Username")
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		@JsonProperty("Password")
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@JsonProperty("Tagname")
		public String getTagname() {
			return tagname;
		}

		public void setTagname(String tagname) {
			this.tagname = tagname;
		}

		@JsonProperty("RecepientNumber")
		public String getRecepientNumber() {
			return recepientNumber;
		}

		public void setRecepientNumber(String recepientNumber) {
			this.recepientNumber = recepientNumber;
		}

		@JsonProperty("VariableList")
		public String getVariableList() {
			return variableList;
		}

		public void setVariableList(String variableList) {
			this.variableList = variableList;
		}

		@JsonProperty("ReplacementList")
		public String getReplacementList() {
			return replacementList;
		}

		public void setReplacementList(String replacementList) {
			this.replacementList = replacementList;
		}

		@JsonProperty("Message")
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@JsonProperty("SendDateTime")
		public String getSendDateTime() {
			return sendDateTime;
		}

		public void setSendDateTime(String sendDateTime) {
			this.sendDateTime = sendDateTime;
		}

		@JsonProperty("EnableDR")
		public String getEnableDR() {
			return enableDR;
		}

		public void setEnableDR(String enableDR) {
			this.enableDR = enableDR;
		}
	}

}
