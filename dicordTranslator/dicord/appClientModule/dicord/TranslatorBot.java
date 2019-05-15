package dicord;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class TranslatorBot {

	public static void main(String[] args) {
		String clientId = "NUJTqmXO2dDejppjeiHS";
		String clientSecret = "Br5tAi_BTN";
		try {
			String text = URLEncoder.encode("Áö½Â ·Ä", "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/language/translate";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			//post request
			String postParams = "source=ko&target=en&text = " + text;
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if(responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			
			JsonParser jsonParser = new JsonParser();
			JsonElement jsonElement = jsonParser.parse(br.readLine());
			/*
			JsonObject jsonObject = new JsonParser().parse(br.readLine()).getAsJsonObject();
			JsonArray jsonArray = jsonObject.getAsJsonArray("result");
			JsonObject jsonObject1 = jsonArray.get(0).getAsJsonObject();
			JsonPrimitive jsonPrimitive = jsonObject1.getAsJsonPrimitive("translatedText");
			br.close();
			*/
			System.out.println(jsonElement.getAsJsonObject().get("message").getAsJsonObject().get("result").getAsJsonObject().get("translatedText").toString().trim());
			br.close();
			/*
			String inputLine;
			StringBuffer response = new StringBuffer();
			*/
			/*while((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			System.out.println(response.toString());
			*/
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
}
