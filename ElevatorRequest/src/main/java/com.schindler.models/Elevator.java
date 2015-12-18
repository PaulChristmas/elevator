package com.schindler.models;

import org.apache.log4j.Logger;

import java.util.Set;

public class Elevator implements Runnable
{
    final static Logger logger = Logger.getLogger(Elevator.class);

    public final int TOP_FLOOR = 15;

    public final int BOTTOM_FLOOR = 1;

    private ElevatorState elevatorState = ElevatorState.STATIC;

    private Direction direction;

    private int currentFloor = BOTTOM_FLOOR;

    private ExternalElevatorRequests externalElevatorRequests;

    public Elevator(ExternalElevatorRequests externalElevatorRequests)
    {
        this.externalElevatorRequests = externalElevatorRequests;
    }

    public void gotoFloor(int destinationFloor)
    {
        int floorIncrement = determineElevatorDirection(destinationFloor);
        elevatorState = ElevatorState.MOVING;
        logger.info("Elevator is at floor: " + currentFloor + " going to floor: " + destinationFloor + " Elevator going " + direction);


        while (destinationFloor != currentFloor) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentFloor += floorIncrement;
            Set<Integer> elevatorRequestsForCurrentDirection = getElevatorRequestsForCurrentDirection();
            if(elevatorRequestsForCurrentDirection.contains(currentFloor)) {
                logger.info("Making a stop on the way to service request on floor: " + currentFloor + " going to floor: " + destinationFloor + " Elevator going " + direction);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                elevatorRequestsForCurrentDirection.remove(currentFloor);
            }
            logger.info("Elevator is at floor: " + currentFloor + " going to floor: " + destinationFloor + " Elevator going " + direction);

        }
        elevatorState = ElevatorState.STATIC;

    }

    private int determineElevatorDirection(int destinationFloor) {
        if(currentFloor > destinationFloor)
        {
            direction = Direction.DOWN;
            return -1;
        } else {
            direction = Direction.UP;
            return 1;
        }
    }

    private Set<Integer> getElevatorRequestsForCurrentDirection()
    {
        if(direction == Direction.UP) {
            return externalElevatorRequests.getUpRequests();
        }

        return externalElevatorRequests.getDownRequests();
    }

    @Override
    public void run() {
        logger.info("Elevator Online.");
        while(true) {
            if(elevatorState == ElevatorState.STATIC)
            {
                for(Integer floorRequest : externalElevatorRequests.getDownRequests())
                {
                    externalElevatorRequests.getDownRequests().remove(floorRequest);
                    gotoFloor(floorRequest);
                }

                for (Integer floorRequest : externalElevatorRequests.getUpRequests())
                {
                    externalElevatorRequests.getUpRequests().remove(floorRequest);
                    gotoFloor(floorRequest);
                }
            }
        }
    }
}
