package com.hwt.entity;

import lombok.Data;

@Data
public class Room {
    private int roomNum;

    private Player player1;

    private Player player2;

    private int currentUser;

    private RoomStatus status = RoomStatus.INIT;

    private enum RoomStatus{
        INIT(0),WAIT_PLAYER(1),READY(2),GAMING(3), NONE(4);

        private final int status;

        RoomStatus(int status){
            this.status = status;
        }

        public int getStatus() {
            return status;
        }
    }
}
