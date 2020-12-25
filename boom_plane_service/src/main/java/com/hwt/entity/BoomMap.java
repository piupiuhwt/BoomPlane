package com.hwt.entity;

import com.hwt.constant.MapConstant;
import lombok.Data;

import java.util.List;

@Data
public class BoomMap {
    private List<Plane> planes;

    private int height = MapConstant.MAP_HEIGHT;

    private int width = MapConstant.MAP_WIDTH;

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
                fillEast(headX,headY);
                break;
            }
            case SOUTH: {
                fillSouth(headX,headY);
                break;
            }
            case WEST: {
                fillWest(headX,headY);
                break;
            }
            case NORTH: {
                fillNorth(headX,headY);
                break;
            }
            default:
        }
    }

    private void fillEast(Integer headX,Integer headY){
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
    }

    private void fillSouth(Integer headX,Integer headY){
        //机翼
        for(int i = headX-2;i<=headX+2;i++){
            boomMap[headY + 1][i] = 1;
        }
        //机身
        boomMap[headY+2][headX] = 1;
        //机尾
        for(int i = headX-1;i<=headX+1;i++){
            boomMap[headY + 3][i] = 1;
        }
    }

    private void fillWest(Integer headX,Integer headY){
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
    }

    private void fillNorth(Integer headX,Integer headY){
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
    }

    private void fillParam(Integer headX,Integer headY,Integer changer,boolean changerIsX,boolean isSum){
        int sumNum = isSum? 1 : -1;
        if (changerIsX) {
            //机翼
            for(int i = changer-2;i<=changer+2;i++){
                boomMap[headY + sumNum][i] = 1;
            }
            //机身
            boomMap[headY + 2*sumNum][headX] = 1;
            //机尾
            for(int i = changer-1;i<=changer+1;i++){
                boomMap[headY + 3*sumNum][i] = 1;
            }
            return;
        }
        //机翼
        for(int i = changer-2;i<=changer+2;i++){
            boomMap[i][headX + sumNum] = 1;
        }
        //机身
        boomMap[headY][headX + 2*sumNum] = 1;
        //机尾
        for(int i = changer-1;i<=changer+1;i++){
            boomMap[i][headX + 3*sumNum] = 1;
        }
    }
}
