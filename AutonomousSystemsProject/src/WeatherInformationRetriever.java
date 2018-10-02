import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;


public class WeatherInformationRetriever implements IWeatherInformationRetriever{
	
	private static final String WEATHER_API_KEY = "&APPID=574e802865a6e062cd48c3421c157bec";
	private Utils utils;
	private double latitude;
	private double longitude;
	private StringBuffer content;
	private JSONObject myResponse;

	public WeatherInformationRetriever(double latitude, double longitude){
		utils = new Utils();
		this.latitude = latitude;
		this.longitude = longitude;
		content = new StringBuffer();
		request();
	}
	
	private void request() {
		URL url;
		try {
			url = new URL(utils.URL + utils.LAT + latitude + "&" + utils.LON + longitude + WEATHER_API_KEY);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(utils.GET);
			con.setRequestProperty(utils.CONTENT_TYPE, utils.APPLICATION_JSON);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
			in.close();
			System.out.println(content);
			myResponse = new JSONObject(content.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}	
	}
	
	public String getWeatherInformations() throws JSONException{
		return "Weather: " + myResponse.getString(utils.WEATHER) + "\n" +
				"Temperature: " + myResponse.getString(utils.MAIN) + "\n" +
				"Visibility: " + myResponse.getString(utils.VISIBILITY) + "\n" +
				"Wind: " + myResponse.getString(utils.WIND);
	}
	
	public JSONObject getResponse(){
		return myResponse;
	}
	
}
