package com.hwt.entity;

import com.hwt.constant.MapConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Player {
    private String userName;

    private int[][] targetMap = new int[MapConstant.MAP_HEIGHT][MapConstant.MAP_WIDTH];

    private BoomMap map;
}
