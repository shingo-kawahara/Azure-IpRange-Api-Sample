package jp.co.saison.azureiprangeapi;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class UrlGetter {
	private UrlGetter() {
	}

	static String getDownloadUrl() throws IOException {
		String baseUrl = "https://www.microsoft.com/en-us/download/confirmation.aspx?id=41653";
		String downloadBaseUrl = "https://download.microsoft.com";
		String href = "href";
		String downloadUri = "";

		Document doc = Jsoup.connect(baseUrl).get();
		Elements newsHeadlines = doc.select("a");

		for (Element element : newsHeadlines) {
			if (element.hasAttr(href) && element.getElementsByAttribute(href)
					.attr(href)
					.contains(downloadBaseUrl)) {
				downloadUri = element.getElementsByAttribute(href)
						.attr(href);
				break;
			}
		}
		if (StringUtils.isEmpty(downloadUri)) {
			throw new IllegalStateException ("ダウンロードURLが取得できませんでした");
		}
		return downloadUri;
	}
}
