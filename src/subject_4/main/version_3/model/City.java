package subject_4.main.version_3.model;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/11 22:29
 * Description: 城市类 包括城市的名字和坐标
 */
public class City implements Displayable {
    private double x;//横坐标
    private double y;//纵坐标
    private String name;//城市的名称

    public City(String name, double x, double y) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public City(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public City(String name) {
        this.name = name;
    }

    public City() {
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                '}';
    }
}
