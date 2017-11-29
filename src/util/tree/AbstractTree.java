package util.tree;

/**
 * Author: Zhou Xianghui
 * Time: 2017/10/21 19:55
 * Description: AbstractTree抽象类，部分实现Tree接口 程序清单26.4
 */

public abstract class AbstractTree <E extends Comparable<E>> implements Tree<E>{

    /**
     * 中序遍历树
     * @return
     */
    public void inorder(){

    }

    /**
     * 后序遍历树
     * @return
     */
    public void postorder(){

    }


    /**
     * 前序遍历树
     * @return
     */
    public void preorder(){

    }



    /**
     * 判断树是否为空
     * @return
     */
    public boolean isEmpty(){
        return getSize() == 0;
    }


    /**
     * 返回一个迭代器来遍历树的所有元素
     * @return
     */
    public java.util.Iterator iterator(){
        return null;
    }

}
