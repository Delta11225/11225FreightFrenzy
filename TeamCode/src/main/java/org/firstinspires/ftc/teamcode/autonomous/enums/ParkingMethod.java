package org.firstinspires.ftc.teamcode.autonomous.enums;

public enum ParkingMethod {
    WALL("wall"),
    BARRIER("barrier")
    ;

    private final String text;

    ParkingMethod(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}