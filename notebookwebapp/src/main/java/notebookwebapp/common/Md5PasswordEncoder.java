package notebookwebapp.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Объект <code> класса Md5PasswordEncoder</code> предоставляет 
 * кодирование пароля по алгоритму MD5
 * @version 1.0
 */
public class Md5PasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] hashArray = messageDigest.digest(rawPassword.toString().getBytes(StandardCharsets.UTF_8));
			StringBuilder builder = new StringBuilder();
			for(byte _byte : hashArray) {
				builder.append(String.format("%02x", _byte));
			}
			return builder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String _rawPassword = encode(rawPassword);
		return encodedPassword != null && encodedPassword.equals(_rawPassword);
	}

}
