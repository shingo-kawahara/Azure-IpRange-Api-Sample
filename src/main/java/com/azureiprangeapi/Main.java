package com.azureiprangeapi;

import java.io.IOException;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class Main {
	@FunctionName("iprange")
	public HttpResponseMessage main(
			@HttpTrigger(
					name = "req",
					methods = { HttpMethod.GET, HttpMethod.POST },
					authLevel = AuthorizationLevel.FUNCTION)
			HttpRequestMessage<Optional<String>> request,
			ExecutionContext context)
					throws SAXException, IOException, ParserConfigurationException {
		context.getLogger().info("Java HTTP trigger processed a request.");

		String queryRegion = request.getQueryParameters().get("region");
		String region = request.getBody().orElse(queryRegion);

		if (StringUtils.isEmpty(region)) {
			return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
					.body("Please pass a region on the query string or in the request body")
					.build();
		}

		ProxySetter.proxySet();

		return request.createResponseBuilder(HttpStatus.OK).body(
				IpRangeGetter.getIpRangeText(UrlGetter.getDownloadUrl(), region))
				.build();
	}
}
