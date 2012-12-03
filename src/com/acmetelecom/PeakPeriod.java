package com.acmetelecom;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

class PeakPeriod {

	final private static LocalTime DEFAULT_PEAK_START_TIME = new LocalTime(7,
			0, 0);
	final private static LocalTime DEFAULT_PEAK_END_TIME = new LocalTime(19,
			00, 00);

	final public static PeakPeriod DEFAULT_PEAK_PERIOD = new PeakPeriod(
			DEFAULT_PEAK_START_TIME, DEFAULT_PEAK_END_TIME);

	final private int SECONDS_IN_A_DAY = 86400;

	final public LocalTime peakStartTime;
	final public LocalTime peakEndTime;
	final private boolean peakStartBeforeEnd;

	public PeakPeriod(LocalTime PeakStartTime, LocalTime PeakEndTime) {

		peakStartTime = PeakStartTime;
		peakEndTime = PeakEndTime;

		peakStartBeforeEnd = peakStartTime.isBefore(peakEndTime)
				|| peakStartTime.isEqual(peakEndTime);
	}

	public boolean isOffPeak(DateTime time) {
		return isOffPeak(time.toLocalTime());
	}

	public boolean isOffPeak(LocalTime time) {

		if (peakStartBeforeEnd) {

			boolean isAfterPeakStart = (peakStartTime.isBefore(time) || peakStartTime
					.isEqual(time));
			boolean isBeforePeakEnd = peakEndTime.isAfter(time);

			if (isAfterPeakStart && isBeforePeakEnd) {
				return false;
			}
			return true;

		} else {
			// Peak time crosses over midnight
			boolean isAfterPeakStart = (peakStartTime.isBefore(time) || peakStartTime
					.isEqual(time));
			boolean isBeforePeakEnd = peakEndTime.isAfter(time);

			if (isAfterPeakStart || isBeforePeakEnd) {
				return false;
			}
			return true;
		}
	}

	public int getSecondsBeforeNextPeakStartTime(LocalTime time) {
		int seconds = 0;
		int peakSeconds = peakStartTime.getMillisOfDay() / 1000;
		int timeSeconds = time.getMillisOfDay() / 1000;
		seconds = peakSeconds - timeSeconds;
		if (seconds < 0) {
			// Wrap around if negative
			seconds += SECONDS_IN_A_DAY;
		}
		return seconds;
	}

	public int getSecondsUntilPeakEndTime(LocalTime time) {
		int seconds = 0;
		int peakSeconds = peakEndTime.getMillisOfDay() / 1000;
		int timeSeconds = time.getMillisOfDay() / 1000;
		seconds = peakSeconds - timeSeconds;
		if (seconds < 0) {
			// Wrap around if negative
			seconds += SECONDS_IN_A_DAY;
		}
		return seconds;
	}

}
