import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Device implements IDevice{

	private Utils utils;
	private UserInterface ui;
	private ITimeZoneRetriever tzRetriever;
	private IWeatherInformationRetriever wiRetriever;
	private IFilter filter;
	private boolean flag = false;
	private String house_number;
	private String street_name;
	private String city_name;
	private double latitude;
	private double longitude;
	private StringBuffer content;
	private JSONArray myResponse;
	
	public Device(UserInterface ui){
		utils = new Utils();
		this.ui = ui;
		filter = new Filter();
		content = new StringBuffer();
		house_number = ui.getHouseNumber();
		street_name = ui.getStreetName();
		city_name = ui.getCityName();
		getCoordinates();
		setHour();
		setWeather();
		writeMotivationalMessage();	
	}
	
	private void getCoordinates(){
		URL url;
		try {
			url = new URL(utils.COORDINATES_URL + house_number + "+" + street_name + "+" + city_name + "&format=json");
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
			myResponse = new JSONArray(content.toString());
			JSONObject obj = myResponse.getJSONObject(0);
			latitude = Double.parseDouble(obj.getString("lat"));
			longitude = Double.parseDouble(obj.getString("lon"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}	
	}

	public void setHour(){
		tzRetriever = new TimeZoneRetriever(latitude, longitude);
		ui.writeGeneralInfo(utils.HOUR + tzRetriever.getHourFromTimeZone(), true);

	}
	
	public void setWeather(){
		wiRetriever = new WeatherInformationRetriever(latitude, longitude);
		try {
			ui.writeGeneralInfo(wiRetriever.getWeatherInformations(), false);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void writeMotivationalMessage(){
		flag = filter.timeFilter(tzRetriever.getHourFromTimeZone());
		if(flag == false){
			ui.writeMessageInfo(utils.STAY_HOME_MESSAGE_NIGHT);
		}
		else if (flag){
			ui.writeMessageInfo(filter.weatherFilter(wiRetriever.getResponse()));
		}
	}
	
}
