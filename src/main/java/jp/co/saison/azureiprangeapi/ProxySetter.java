package jp.co.saison.azureiprangeapi;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.apache.commons.lang3.StringUtils;

class ProxySetter {

	private ProxySetter() {
	}

	static void proxySet() {
		String proxyHost = System.getenv("proxyHost");
		String proxyPort = System.getenv("proxyPort");
		String proxyUser = System.getenv("proxyUser");
		String proxyPass = System.getenv("proxyPass");

		if (StringUtils.isEmpty(proxyHost)
				|| StringUtils.isEmpty(proxyPort)
				|| StringUtils.isEmpty(proxyUser)
				|| StringUtils.isEmpty(proxyPass)) {
			return;
		}

		System.setProperty("proxyHost", proxyHost);
		System.setProperty("proxyPort", proxyPort);
		Authenticator.setDefault(new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(proxyUser, proxyPass.toCharArray());
			}
		});
	}
}
