package no.hvl.dat110.aciotdevice.client;

import java.io.IOException;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	private static String urlpath = "http://localhost:8080";
	private static String logpath = "/accessdevice/log/";

	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message

		String json = new Gson().toJson(new AccessMessage(message));

		RequestBody reqBody = RequestBody.create(MediaType.parse("application/json"), json);

		OkHttpClient client = new OkHttpClient();

		Request req = new Request.Builder().url(urlpath + logpath).post(reqBody).build();

		try {
			client.newCall(req).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String codepath = "/accessdevice/code";

	public AccessCode doGetAccessCode() {

		Gson gson = new Gson();

		AccessCode code = null;

		OkHttpClient client = new OkHttpClient();

		Request req = new Request.Builder().url(urlpath + codepath).get().build();

		try (Response res = client.newCall(req).execute()) {

			String string = res.body().string();
			code = gson.fromJson(string, AccessCode.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return code;
	}
}
