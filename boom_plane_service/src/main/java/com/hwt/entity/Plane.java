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

}
