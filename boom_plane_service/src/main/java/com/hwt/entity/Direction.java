package com.hwt.entity;

public enum Direction {
    EAST(0),SOUTH(1),WEST(2),NORTH(3);

    private final int direction;

    private Direction(int direction){
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
