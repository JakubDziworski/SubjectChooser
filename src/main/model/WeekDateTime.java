package model;



import java.util.ArrayList;
import java.util.List;

import managers.ApplicationManager;
import exceptions.IllegalHourException;
import exceptions.IllegalMinuteException;

public class WeekDateTime implements Comparable<WeekDateTime> {
		private Day day;
		private int hour;
		private int min;
		
		public WeekDateTime(Day day, int hour, int min) throws IllegalHourException, IllegalMinuteException {
			super();
			this.day = day;
			this.setHour(hour);
			this.setMin(min);
		}
		
		public static WeekDateTime DefaultDateTime() {
			try {
				return new WeekDateTime(Day.MON,0,0);
			} catch (IllegalHourException | IllegalMinuteException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public Day getDay() {
			return day;
		}
		public void setDay(Day day) {
			this.day = day;
		}
		public int getHour() {
			return hour;
		}
		public void setHour(int godz) throws IllegalHourException {
			if(godz > 24 || godz < 0) throw new IllegalHourException();
			this.hour = godz;
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
			SUN;

			@Override
			public String toString() {
				switch (this) {
					case MON : return ApplicationManager.Strings.DAY_MON;
					case TUE : return ApplicationManager.Strings.DAY_TUE;
					case WED : return ApplicationManager.Strings.DAY_WED;
					case THU : return ApplicationManager.Strings.DAY_THU;
					case FRI : return ApplicationManager.Strings.DAY_FRI;
					case SAT : return ApplicationManager.Strings.DAY_SAT;
					case SUN : return ApplicationManager.Strings.DAY_SUN;
					default : return ApplicationManager.Strings.DAY_UNDEFINED;
				}
			}
		}
		
		public static List<String> getAllPossibleDateTimesAsString() {
			List<String> possibleTimes = new ArrayList<String>();
			for(int i=0;i<25;i++) {
				for(int j=0;j<60;j+=15) {
					try {
						possibleTimes.add((new WeekDateTime(Day.MON, i, j)).getTimeOnlyString());
					} catch (IllegalHourException | IllegalMinuteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return possibleTimes;
		}
		
		
		/**
		 * @return Returns minutes from Monday 00:00
		 */
		public int toMinutes() {
			return day.ordinal()*1440 + getHour()*60 + getMin();
		}
		
		public String getTimeOnlyString() {
			return String.format("%d2:%d2",hour,min);
		}
		
		public void setTimeFromString(String str) {
			hour = Integer.valueOf(str.substring(0,2));
			min = Integer.valueOf(str.substring(2,2));
		}

		@Override
		public int compareTo(WeekDateTime otherDate) {
			return (int) Math.signum(this.toMinutes() - otherDate.toMinutes());
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + day.ordinal();
			result = prime * result + hour;
			result = prime * result + min;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			WeekDateTime other = (WeekDateTime) obj;
			if (day != other.day)
				return false;
			if (hour != other.hour)
				return false;
			if (min != other.min)
				return false;
			return true;
		}
}
