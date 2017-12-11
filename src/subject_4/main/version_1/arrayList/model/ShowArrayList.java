package subject_4.main.version_1.arrayList.model;

import javafx.scene.shape.Rectangle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/11 10:05
 * Description: 展示arrayList类
 */
public class ShowArrayList<E> {

    public static int WIDTH = 100;
    public static int HEIGHT = 40;

    private E element;
    private Rectangle rectangle = new Rectangle(WIDTH, HEIGHT);

    public ShowArrayList(E element, Rectangle rectangle) {
        this.element = element;
        this.rectangle = rectangle;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
