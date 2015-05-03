package model;



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
			SUN
		}
		
		
		/**
		 * @return Returns minutes from Monday 00:00
		 */
		public int toMinutes() {
			return day.ordinal()*1440 + getHour()*60 + getMin();
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
