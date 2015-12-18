package com.schindler.app;

import com.schindler.models.ElevatorControlSystem;

/**
 * Created by ronyosi on 12/16/15.
 */
public class App {

    public static void main(String[] args) {
        ElevatorControlSystem elevatorControlSystem = new ElevatorControlSystem();
        elevatorControlSystem.bringAllSystemsOnline();
    }

}
