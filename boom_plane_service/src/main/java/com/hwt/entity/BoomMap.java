package com.hwt.entity;

import lombok.Data;

import java.util.List;

@Data
public class BoomMap {
    private List<Plane> planes;

    private int height;

    private int width;

    private int[][] boomMap;

    public void init(List<Plane> planes){
        this.boomMap = new int[height][width];
        for (Plane plane : planes) {
            fillBoomMap(plane);
        }
    }

    private void fillBoomMap(Plane plane){
        Integer headX = plane.getHeadX();
        Integer headY = plane.getHeadY();
        Direction direction = plane.getDirection();
        //TODO 判断飞机是否重叠
        //机头
        boomMap[headY][headX] = 2;
        switch (direction){
            case EAST: {
                //机翼
                for(int i = headY-2;i<=headY+2;i++){
                    boomMap[i][headX - 1] = 1;
                }
                //机身
                boomMap[headY][headX-2] = 1;
                //机尾
                for(int i = headY-1;i<=headY+1;i++){
                    boomMap[i][headX - 3] = 1;
                }
                break;
            }
            case SOUTH: {
                //机翼
                for(int i = headX-2;i<=headX+2;i++){
                    boomMap[headY + 1][i] = 1;
                }
                //机身X
                boomMap[headY+2][headX] = 1;
                //机尾
                for(int i = headX-1;i<=headX+1;i++){
                    boomMap[headY + 3][i] = 1;
                }
                break;
            }
            case WEST: {
                //机翼
                for(int i = headY-2;i<=headY+2;i++){
                    boomMap[i][headX + 1] = 1;
                }
                //机身
                boomMap[headY][headX+2] = 1;
                //机尾
                for(int i = headY-1;i<=headY+1;i++){
                    boomMap[i][headX + 3] = 1;
                }
                break;
            }
            case NORTH: {
                //机翼
                for(int i = headX-2;i<=headX+2;i++){
                    boomMap[headY - 1][i] = 1;
                }
                //机身
                boomMap[headY-2][headX] = 1;
                //机尾
                for(int i = headX-1;i<=headX+1;i++){
                    boomMap[headY - 3][i] = 1;
                }
                break;
            }
            default:
        }
    }
}
