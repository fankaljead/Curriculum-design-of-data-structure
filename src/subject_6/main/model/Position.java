package subject_6.main.model;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/15 14:37
 * Description: 位置类
 */
public class Position {
    private int x;//位置的x坐标
    private int y;//位置的y坐标
    private int attribute;
    private String direction;

    public static final String UP = "up";//上 往上纵坐标y减1
    public static final String DOWN = "down";//下 往下纵坐标加1
    public static final String LEFT = "left";//左 往左横坐标x减1
    public static final String RIGHT = "right";//右 往右横坐标x加1
    public static final String END = "end";//右 往右横坐标x加1

    public Position(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Position(int x, int y, int attribute) {
        this.x = x;
        this.y = y;
        this.attribute = attribute;
    }

    public Position() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }

    public int getAttribute() {
        return attribute;
    }

    @Override
    public String toString() {
        return "(" +
                "x=" + x +
                ", y=" + y +
                ", direction='" + direction + '\'' +
                ')';
    }
}
