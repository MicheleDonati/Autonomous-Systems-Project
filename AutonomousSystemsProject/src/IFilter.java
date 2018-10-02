import org.json.JSONObject;

/*
 * This is the filter component that help to build a proper motivational message for the user, given the
 * informations retrieved.
 */
public interface IFilter {

	/*
	 * This method allows to build the motivational message given the hour of the day.
	 */
	boolean timeFilter(int hour);
	
	/*
	 * This method allows to build the motivational message given weather's informations.
	 */
	String weatherFilter(JSONObject weather);
}
