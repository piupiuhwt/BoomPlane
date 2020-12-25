package com.hwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Plane {
    private Direction direction;

    private Integer headX;

    private Integer headY;

    public Plane(int x,int y,int direction){
        this.headX = x;
        this.headY = y;
        this.direction = Direction.values()[direction];
    }
}