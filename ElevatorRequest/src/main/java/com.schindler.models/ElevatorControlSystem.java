package com.schindler.models;

public class ElevatorControlSystem {

    private ExternalElevatorRequests externalElevatorRequests = new ExternalElevatorRequests();

    public static void bringAllSystemsOnline()
    {
        ElevatorControlSystem elevatorControlSystem = new ElevatorControlSystem();
        elevatorControlSystem.bringElevatorOnline();
        elevatorControlSystem.bringElevatorControlSystemOnline();
    }

    private void bringElevatorOnline()
    {
      Elevator elevator = new Elevator(externalElevatorRequests);
      Thread elevatorThread = new Thread(elevator);
      elevatorThread.start();

    }

    private void bringElevatorControlSystemOnline()
    {
        ExternalElevatorRequestHandler externalElevatorRequestHandler = new ExternalElevatorRequestHandler(externalElevatorRequests);
        Thread elevatorRequests = new Thread(externalElevatorRequestHandler);
        elevatorRequests.start();
    }
}
