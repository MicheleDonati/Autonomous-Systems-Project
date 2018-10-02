/*
 * 	This is the device interface
 */


public interface IDevice {
	
	/*
	 * This method sets the hour of the day given latitude and longitude.
	 */
	void setHour();
	
	/*
	 * This method sets the weather informations given latitude and longitude.
	 */
	void setWeather();

	/*
	 * This method write a motivational message on the user interface given the informations retrieved with
	 * setHour() and setWeather().
	 */
	void writeMotivationalMessage();
}
