package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private int hang, cot;
    private int[][] matrix;

    private void createMatrix() {
        matrix = new int[hang][cot];
        Random rd = new Random();
        int imgCount = 21;
        int max = 4;
        int arr[] = new int[imgCount +1];
        ArrayList<Point> listPoint = new ArrayList<Point>();

        for (int i = 0; i < hang; i++ ){
            for(int j = 0; j < cot; j++){
                listPoint.add(new Point(i,j));
            }
        }

        int i = 0;
        do {
            int index = rd.nextInt(imgCount) + 1; // ??
            if (arr[index] < max) {
                arr[index] += 2; //??
                for(int j = 0; j < 2; j++) {
                    int size = listPoint.size();
                    int pointIndex = rd.nextInt(size);
                    matrix[listPoint.get(pointIndex).x][listPoint.get(pointIndex).y] = index;
                    listPoint.remove(pointIndex);
                }
                i++;
            }
        }while (i < hang * cot / 2);
    }

    public void showMatrix() {
        for (int i = 0; i < hang; i++){
            for (int j = 0; j < cot; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public Controller(int hang,int cot){
        this.hang = hang;
        this.cot = cot;
        createMatrix();
        showMatrix();
    }

    public int getHang(){ return hang; }

    public void setHang(int hang){
        this.hang = hang;
    }

    public int getCot(){
        return cot;
    }

    public void setCot(int cot) {
        this.cot = cot;
    }

    public void setMatrix(int[][] matrix){
        this.matrix = matrix;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public PointLine checkTwoPoint(Point p1, Point p2){
        if (!p1.equals(p2) && matrix[p1.x][p1.y] == matrix[p2.x][p2.y]){
            return new PointLine(p1,p2);
        }
        return null;
    }
}
