package model;


import com.google.gson.annotations.SerializedName;

import exceptions.IllegalHourException;
import exceptions.IllegalMinuteException;

public class WeekDateTime {
		@SerializedName("day")
		Day day;

		@SerializedName("godz")
		int godz;
		@SerializedName("min")
		int min;
		
		public WeekDateTime() {
			this(Day.MON,0,0);
		}
		public WeekDateTime(Day day, int godz, int min) {
			super();
			this.day = day;
			this.godz = godz;
			this.min = min;
		}
		
		public Day getDay() {
			return day;
		}
		public void setDay(Day day) {
			this.day = day;
		}
		public int getGodz() {
			return godz;
		}
		public void setGodz(int godz) throws IllegalHourException {
			if(godz > 24 || godz < 0) throw new IllegalHourException();
			this.godz = godz;
		}
		public int getMin() {
			return min;
		}
		public void setMin(int min) throws IllegalMinuteException {
			if(min > 60 || min < 0) throw new IllegalMinuteException();
			this.min = min;
		}

		public enum Day {
			MON,
			TUE,
			WED,
			THU,
			FRI,
			SAT,
			SUN
		}
}
