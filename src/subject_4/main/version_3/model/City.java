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

    public City(double x, double y, String name) {
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
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
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
}
