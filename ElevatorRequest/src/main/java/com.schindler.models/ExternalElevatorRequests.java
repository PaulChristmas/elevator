package com.schindler.models;

import java.util.HashSet;
import java.util.Set;

public class ExternalElevatorRequests {

    private Set<Integer> upRequests = new HashSet<Integer>();
    private Set<Integer> downRequests = new HashSet<Integer>();

    public synchronized void requestElevator(int floorNumber, Direction direction)
    {
        if(Direction.DOWN == direction) {
            addDownRequest(floorNumber);
            return;
        }

        if(Direction.UP == direction) {
            addUpRequest(floorNumber);
            return;
        }
    }

    private synchronized void addUpRequest(int floorNumber)
    {
        upRequests.add(floorNumber);
    }

    private synchronized void addDownRequest(int floorNumber)
    {
        downRequests.add(floorNumber);
    }

    public synchronized Set<Integer> getUpRequests() {
        return upRequests;
    }

    public synchronized Set<Integer> getDownRequests() {
        return downRequests;
    }
}
