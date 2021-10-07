package utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHelper {
	public String get(String jsonStr, String PATH, String ACCESS_TOKEN) {
		String result = "";
		try {
			OkHttpClient client = new OkHttpClient().newBuilder().build();
			URIBuilder ub = new URIBuilder(buildUrl(PATH));
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(jsonStr, Map.class);
			map.forEach((k, v) -> {
				try {
					ub.addParameter(k, v instanceof String ? (String) v : mapper.writeValueAsString(v));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			});
			URL url = ub.build().toURL();

			Request request = new Request.Builder().url(url).method("GET", null).addHeader("Access-Token", ACCESS_TOKEN)
					.build();
			Response response = client.newCall(request).execute();
			result = response.body().string();
			response.body().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String buildUrl(String path) throws URISyntaxException {
		URI uri = new URI("https", "business-api.tiktok.com", path, "", "");
		return uri.toString();
	}
}
