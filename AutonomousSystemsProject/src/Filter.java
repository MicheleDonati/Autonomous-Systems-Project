import org.json.JSONException;
import org.json.JSONObject;

public class Filter implements IFilter{
	
	private Utils utils;
	
	public Filter(){
		utils = new Utils();
	}
	
	public boolean timeFilter(int hour){
		if(hour >= utils.MORNING && hour <= utils.EVENING){
			return true;
		}
		else return false;
		
	}
	
	public String weatherFilter(JSONObject weather){
		try {
			String temp = weather.getJSONObject(utils.MAIN).get(utils.TEMP).toString();
			double tempInCelsius = convertKelvinToCelsius(temp);
			JSONObject obj = (JSONObject) weather.getJSONArray(utils.WEATHER).get(0);
			String condition = obj.get(utils.MAIN).toString();
			boolean isRaining = (condition.equals(utils.RAIN) || condition.equals(utils.SNOW) || condition.equals(utils.EXTREME) || condition.equals(utils.THUNDERSTORM));
			if(isRaining){
				return utils.STAY_HOME_MESSAGE_BAD_WEATHER;
			}
			else if(tempInCelsius >= 17 && tempInCelsius <= 24) {
				return utils.GO_OUT_FOR_A_WALK;
			}
			else if (tempInCelsius < 17){
				return utils.TOO_COLD_FOR_A_WALK;
			}
			else if (tempInCelsius > 24){
				return utils.TOO_HOT_FOR_A_WALK;
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	private double convertKelvinToCelsius(String temp){
		return (Double.parseDouble(temp) - utils.CONVERSION_VALUE);	
	}
	
	
}
