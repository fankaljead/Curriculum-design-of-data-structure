package util.tree;

/**
 * Author: Zhou Xianghui
 * Time: 2017/10/21 19:46
 * Description: 接口Tree定义树的所有常用操作 程序清单26.3
 */
public interface Tree <E extends Comparable<E>>{

    /**
     * 查找e元素是否在树中，是则返回true
     * @param e
     * @return
     */
    public boolean search(E e);


    /**
     * 在树中插入e元素，成功则返回true
     * @param e
     * @return
     */
    public boolean insert(E e);


    /**
     * 删除树中的e元素，成功则返回true
     * @param e
     * @return
     */
    public boolean delete(E e);


    /**
     * 中序遍历树
     * @return
     */
    public void inorder();


    /**
     * 后序遍历树
     * @return
     */
    public void postorder();


    /**
     * 前序遍历树
     * @return
     */
    public void preorder();


    /**
     * 获取树的大小
     * @return
     */
    public int getSize();


    /**
     * 判断树是否为空
     * @return
     */
    public boolean isEmpty();


    /**
     * 返回一个迭代器来遍历树的所有元素
     * @return
     */
    public java.util.Iterator iterator();

}
