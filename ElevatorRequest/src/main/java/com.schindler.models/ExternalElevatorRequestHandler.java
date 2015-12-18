package com.schindler.models;

import org.apache.log4j.Logger;

import java.util.Scanner;

public class ExternalElevatorRequestHandler implements Runnable
{
    final static Logger logger = Logger.getLogger(ExternalElevatorRequestHandler.class);

    private ExternalElevatorRequests externalElevatorRequests;

    public ExternalElevatorRequestHandler(ExternalElevatorRequests externalElevatorRequests)
    {
        this.externalElevatorRequests = externalElevatorRequests;
    }


    @Override
    public void run() {
        logger.info("Elevator Request Handler Online.");
        while(true) {
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            logger.info("Enter floor and direction of elevator request, for example 1:UP for a request of an elevator to floor 1 going up or 1:DOWN for down:");
            String n = reader.next(); // Scans the next token of the input as an int.
            String[] request = n.split(":");
            try {
                int floor = Integer.valueOf(request[0]);
                Direction direction = Direction.valueOf(request[1]);
                externalElevatorRequests.requestElevator(floor, direction);
            } catch(NumberFormatException e) {
                logger.info("Input invalid, enter a request in the following format, 4:UP or 2:DOWN where the number is your floor, and UP/DOWN is the direction you would like to go");
            } catch (Exception e) {
                logger.info("Issue found when input entered, enter a request in the following format, 4:UP or 2:DOWN where the number is your floor, and UP/DOWN is the direction you would like to go");

            }

        }
    }
}
