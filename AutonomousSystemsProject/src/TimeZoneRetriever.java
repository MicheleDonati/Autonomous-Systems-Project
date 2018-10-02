import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeZoneRetriever implements ITimeZoneRetriever{

	private TimeZone timeZone;
	private Calendar calendar;
	private TimezoneMapper timezone_mapper;
	private double latitude;
	private double longitude;
	private int hour;
	
	public TimeZoneRetriever(double latitude, double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
		timezone_mapper = new TimezoneMapper();
		String city = timezone_mapper.latLngToTimezoneString(this.latitude, this.longitude);
		timeZone = TimeZone.getTimeZone(city);
		this.calendar = new GregorianCalendar();
		calendar.setTimeZone(timeZone);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public int getHourFromTimeZone() {
		return this.hour;
	}
	
	
	
}
