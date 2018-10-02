import org.json.JSONException;
import org.json.JSONObject;

/*
 * This is the weather informations retriever component, that finds the weather conditions about a city, given latitude and longitude.
 */
public interface IWeatherInformationRetriever {

	/*
	 * This method returns the weather informations retrieved.
	 */
	String getWeatherInformations() throws JSONException;
	
	/*
	 * This method returns a JSONObject with the response from the web service about the weather conditions.
	 */
	JSONObject getResponse();
}
