package subject_6.main.model;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/15 10:53
 * Description: 迷宫类
 */
public class Maze {
    private int rows = 10;//迷宫的行数
    private int columns = 10;//迷宫的列数
    private byte[][] mazeData;//存储迷宫的数据0表示通路，1表示阻碍
    private int startPoint = 0;//迷宫起点
    private int endPoint = rows * columns - 1;//迷宫终点

    public static final byte[] STATE = {0, 1};//点的状态 0表示通过 1表示阻碍

    /**
     * 通过行和列来构造迷宫
     * @param rows
     * @param columns
     */
    public Maze(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        initialMazeData();
    }

    public Maze(int rows) {
        this.rows = rows;
        initialMazeData();
    }

    public Maze(){
        initialMazeData();
    }


    //Get Set
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public byte[][] getMazeData() {
        return mazeData;
    }

    public void setMazeData(byte[][] mazeData) {
        this.mazeData = mazeData;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }


    /**
     * 随机生成迷宫
     */
    public void initialMazeData(){
        mazeData = new byte[rows][columns];
        endPoint = rows * columns - 1;//设置默认终点

        //随机设置迷宫
        for (int i = 0; i < mazeData.length; i++) {
            for (int j = 0; j < mazeData[i].length; j++) {
                if(Math.random() > 0.5){
                    mazeData[i][j] = STATE[1];
                }
                else {
                    mazeData[i][j] = STATE[0];
                }
            }
        }

        //将起点和终点设为通过
        mazeData[startPoint/this.rows][startPoint%this.rows] = STATE[0];
        mazeData[endPoint/this.rows][endPoint%this.rows] = STATE[0];
    }
}
