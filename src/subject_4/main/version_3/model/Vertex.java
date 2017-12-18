package subject_4.main.version_3.model;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/18 20:46
 * Description: 顶点类
 */
public class Vertex {
    private String name;
    private double x;
    private double y;

    public Vertex(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Vertex(String name) {
        this.name = name;
    }

    public Vertex() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
