package com.exdev.cc.web.test;

import static org.junit.Assert.fail;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.exdev.cc.utils.CleanCarUtils;
import com.exdev.cc.utils.Notification;

public class CleanCarUtilsTest {

	@Test
	public void testSendEMail() {
		try {
			CleanCarUtils.sendEMail("erwi@outlook.com", "Testing", "This is a test message");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSendNotification() {
		Notification not = new Notification();
		not.put("Message", "New Request has been created");
		not.put("requestId", "1");
		not.setTo(
				"cNcWS_IsyVE:APA91bGVD8pVFz6mgouaVym1-fGipdWcZTIPBB--QLgjyhjNk5DeQB1Gt5LB-RU2pbgMRnT0YHYgppeoVfHLRjqmKJWc80HFhUwFqtpPQFjC6ED6rxjl5XIm5WON7C_T4nzKzULFAVE-");
		try {
			CleanCarUtils.sendNotification(not);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSendSMS() {
		String resp = CleanCarUtils.sendSMS("0548866904", "Welcome مرحبا");
		System.out.println(resp);
	}

	@Test
	public void testMd5() {
		String code = DigestUtils.md5Hex("123");
		String code2 = CleanCarUtils.md5("123");

		System.out.println("Code: " + code);
		System.out.println("Code2: " + code2);
	}
}
