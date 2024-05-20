package org.example;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static final int DAYOFF = 25;
    private static final int MONTHOFF = 12;

    private static Fan fan = new Fan();

    public static void main(String[] args) {

        fan.setDayOff(DAYOFF);
        fan.setMonthOff(MONTHOFF);

        String choice = "";

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Welcome to the Fan Manage App\n");
        displayFanState(fan);

        while ((!isExit(choice))) {
            try {
                choice = performAction(fan);

                if (!isExit(choice))
                    displayFanState(fan);

            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }

        System.out.println("Exiting the app...");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Prompts the user to choose an action and performs the selected action on the fan.
     *
     * @param fan the fan object to perform the action on
     * @return the user's choice
     * @throws Exception if an invalid action is selected
     */
    private static String performAction(Fan fan) throws Exception {
        System.out.println("What action would you like to perform?");
        System.out.println("\t1. Change fan speed (enter '1', 's' or 'speed')");
        System.out.println("\t2. Change fan direction (enter '2' or 'direction')");
        System.out.println("\t3. Set current date (enter '3' or 'date')");
        System.out.println("\t4. Reset current date (enter '4' or 'reset')");
        System.out.println("\t5. Exit the app (enter '5', 'e' or 'exit')");

        var choice = sc.next();

        if (!isValidAction(choice)) {
            throw new Exception("Invalid Action");
        }

        if (isSpeedChange(choice)) {
            fan.pullSpeedCord();
        } else if (isDirectionChange(choice)) {
            fan.pullDirectionCord();
        } else if (isDateChange(choice)) {
            var date = getDate("Enter a date (yyyy-mm-dd): ",
                    "Invalid date or date format. Please enter in the format yyyy-mm-dd");
            fan.setDate(date);
        } else if (isResetDate(choice)) {
            fan.resetDate();
        }

        return choice;
    }

    /**
     * Displays the current state of the fan, including speed, direction, and date.
     *
     * @param fan the fan object to display the state of
     */
    private static void displayFanState(Fan fan) {
        boolean isDayOff = fan.now().getMonthValue() == 12 && fan.now().getDayOfMonth() == 25;
        int speed = fan.getSpeed();
        String direction = fan.isClockwise() ? "clockwise" : "counterclockwise";
        String speedMessage = "The Fan's current speed is set to " + speed;
        String directionMessage = ", current direction is " + direction;

        System.out.println();
        System.out.println((fan.isOn() ? "The fan is on" : "The fan is off") + "\n" + speedMessage + directionMessage);
        System.out.println("Current date is " + fan.now());

        if (isDayOff && speed > 0) {
            System.out.println("The fan speed is set to " + speed + " with " + direction + " direction, " +
                    "but the fan is off due to it being December 25th.");
        }

        System.out.println();
    }

    /**
     * Prompts the user to enter a date and validates the input.
     *
     * @param message the message to display to the user
     * @param errorMessage the error message to display if the input is invalid
     * @return the parsed LocalDate object
     */
    private static LocalDate getDate(String message, String errorMessage) {
        boolean validValue = false;
        LocalDate date = null;
        do {
            try {
                System.out.print(message);
                String input = sc.next();
                date = LocalDate.parse(input);

                validValue = true;
            } catch (Exception ex) {
                System.out.println(errorMessage);
            }
        } while (!validValue);
        return date;
    }

    /**
     * Checks if the user's choice is to exit the app.
     *
     * @param choice the user's choice
     * @return true if the choice is to exit, false otherwise
     */
    private static boolean isExit(String choice) {
        return choice.equalsIgnoreCase("5") ||
                choice.equalsIgnoreCase("e") ||
                choice.equalsIgnoreCase("exit");
    }

    /**
     * Checks if the user's choice is to change the fan speed.
     *
     * @param choice the user's choice
     * @return true if the choice is to change the fan speed, false otherwise
     */
    private static boolean isSpeedChange(String choice) {
        return choice.equalsIgnoreCase("1") ||
                choice.equalsIgnoreCase("s") ||
                choice.equalsIgnoreCase("speed") ||
                choice.equalsIgnoreCase("change speed");
    }

    /**
     * Checks if the user's choice is to change the fan direction.
     *
     * @param choice the user's choice
     * @return true if the choice is to change the fan direction, false otherwise
     */
    private static boolean isDirectionChange(String choice) {
        return choice.equalsIgnoreCase("2") ||
                choice.equalsIgnoreCase("direction") ||
                choice.equalsIgnoreCase("change direction");
    }

    /**
     * Checks if the user's choice is to change the current date.
     *
     * @param choice the user's choice
     * @return true if the choice is to change the current date, false otherwise
     */
    private static boolean isDateChange(String choice) {
        return choice.equalsIgnoreCase("3") ||
                choice.equalsIgnoreCase("date");
    }

    /**
     * Checks if the user's choice is to reset the current date.
     *
     * @param choice the user's choice
     * @return true if the choice is to reset the current date, false otherwise
     */
    private static boolean isResetDate(String choice) {
        return choice.equalsIgnoreCase("4") ||
                choice.equalsIgnoreCase("reset");
    }

    /**
     * Checks if the user's choice is a valid action.
     *
     * @param choice the user's choice
     * @return true if the choice is a valid action, false otherwise
     */
    private static boolean isValidAction(String choice) {
        return isSpeedChange(choice) ||
                isDirectionChange(choice) ||
                isDateChange(choice) ||
                isResetDate(choice) ||
                isExit(choice);
    }
}