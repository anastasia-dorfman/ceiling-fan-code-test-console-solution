package org.example;

import java.time.LocalDate;

public class Fan {
    private int speed;
    private boolean isClockwise;
    private boolean isOn;
    private int dayOff;
    private int monthOff;
    private static LocalDate currentDate = LocalDate.now();

    /**
     * Constructs a new Fan object with default settings.
     * The initial speed is set to 0, the direction is clockwise and state off.
     */
    public Fan() {
        this.speed = 0;
        this.isClockwise = true;
        this.isOn = false;
    }

    /**
     * Returns the current date.
     *
     * @return the current date
     */
    public LocalDate now() {
        return currentDate;
    }

    /**
     * Sets the current date for the fan.
     *
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        currentDate = date;
        this.isOn = !isDateOff(currentDate) ? true : false;
    }

    /**
     * Resets the current date to the system's current date.
     */
    public void resetDate() {
        currentDate = LocalDate.now();
        this.isOn = !isDateOff(currentDate) ? true : false;
    }

    /**
     * Sets the day off for the fan.
     *
     * @param dayOff the day off to set for the fan
     */
    public void setDayOff(int dayOff) {
        this.dayOff = dayOff;
    }

    /**
     * Sets the month off for the fan.
     *
     * @param monthOff the month off to set for the fan
     */
    public void setMonthOff(int monthOff) {
        this.monthOff = monthOff;
    }

    /**
     * Returns the current speed of the fan.
     *
     * @return the current speed of the fan
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Increases the speed of the fan by pulling the speed cord.
     * If the current speed is less than 3, it increments the speed by 1.
     * If the current speed is 3, it resets the speed to 0.
     */
    public void pullSpeedCord() {
        if (speed < 3) {
            speed++;
//            if (!isDateOff(LocalDate.now())) {
            if (!isDateOff(currentDate)) {
                this.isOn = true;
            }
        } else {
            speed = 0;
            this.isOn = false;
        }
    }

    /**
     * Reverses the direction of the fan by pulling the direction cord.
     * If the current direction is clockwise, it changes to counterclockwise, and vice versa.
     */
    public void pullDirectionCord() {
        isClockwise = !isClockwise;
    }

    /**
     * Returns the current direction of the fan.
     *
     * @return true if the fan is rotating clockwise, false otherwise
     */
    public boolean isClockwise() {
        return isClockwise;
    }

    /**
     * Returns the current state of the fan.
     *
     * @return true if the fan is on, false otherwise
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Checks if the given date is date off.
     *
     * @param date the date to check
     * @return true if the date is date off, false otherwise
     */
    private boolean isDateOff(LocalDate date) {
        return date.getMonthValue() == monthOff && date.getDayOfMonth() == dayOff;
    }
}
