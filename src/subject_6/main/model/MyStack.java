package subject_6.main.model;

import java.util.LinkedList;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/15 10:46
 * Description: 用LinkedList实现的栈
 */
public class MyStack<E> {
    private LinkedList<E> list = new LinkedList<>();

    /**
     * 将项目推送到此堆栈的顶部
     * @param e
     * @return
     */
    public E push(E e){
        list.add(e);
        return e;
    }

    /**
     * 删除此堆栈顶部的对象，并将该对象作为此函数的值返回
     * @return
     */
    public E pop(){
        return list.removeLast();
    }

    /**
     * 查看此堆栈顶部的对象，而不从堆栈中删除它
     * @return
     */
    public E peek(){
        return list.getLast();
    }

    /**
     * 获取栈的大小
     * @return 栈的大小
     */
    public int getSize(){
        return list.size();
    }

    /**
     * 判断栈是否为空
     * @return 栈是否为空
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }


}
