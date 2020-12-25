package com.hwt.entity;

import lombok.Data;

@Data
public class SessionMessage {
    private Room room;
    private Player player;
    private String userName;
}
