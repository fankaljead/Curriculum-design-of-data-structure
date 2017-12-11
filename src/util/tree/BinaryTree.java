package util.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * Author: Zhou Xianghui
 * Time: 2017/10/21 19:59
 * Description: 具体类BinaryTree扩展AbstractTree抽象类 程序清单26.5
 */
public class BinaryTree<E extends Comparable<E>> extends AbstractTree<E>{

    protected TreeNode<E> root;//根节点
    protected int size;//树的大小

    //默认无参构造方法
    public BinaryTree(){

    }


    /**
     * 用数组创建一棵树
     * @param objects
     */
    public BinaryTree(E[] objects){
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }

    //返回树的根节点
    public TreeNode getRoot() {
        return root;
    }

    /**
     * 查找e元素是否在树中，是则返回true
     *
     * @param e
     * @return
     */
    @Override
    public boolean search(E e) {

        TreeNode<E> current = root;

        //从根节点遍历树
        while (current != null){

            //当e大于current当前的元素时，说明e在current的右边
            if(e.compareTo(current.element) > 0){
                current = current.right;
            }

            //当e小于current当前的元素时，说明e在current的左边
            else if(e.compareTo(current.element) < 0){
                current = current.left;
            }

            //当e等于current当前的元素时，返回true
            else {
                return true;
            }

        }

        //循环结束，说明树中不含有e元素
        return false;

    }

    /**
     * 在树中插入e元素，成功则返回true
     *
     * @param e
     * @return
     */
    @Override
    public boolean insert(E e) {

        //当树为空时，直接将root指向用e新创建的节点
        if(root == null){
            root = createNewNode(e);
        }

        else {

            //定位父节点
            TreeNode<E> parent = null;
            TreeNode<E> current = root;

            //循环遍历树找到父节点
            while (current != null){

                //当e比current的元素大时，说明e应该加在其右边
                if(e.compareTo(current.element) > 0){
                    parent = current;
                    current = current.right;
                }

                //当e比current的元素小时，说明e应该加在其左边
                else if(e.compareTo(current.element) < 0){
                    parent = current;
                    current = current.left;
                }

                //该元素已经存在
                else {
                    return false;
                }
            }

            //找到父节点后判断e元素是在其左边还是右边
            if(e.compareTo(parent.element) > 0){
                parent.right = createNewNode(e);
            }

            else {
                parent.left = createNewNode(e);
            }

        }

        size++;
        return true;
    }



    /**
     * 删除树中的e元素，成功则返回true
     *
     * @param e
     * @return
     */
    @Override
    public boolean delete(E e) {

        //定位父节点和当前节点
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        //循环遍历树找到父节点和当前节点
        while (current != null){
            //当e比current的元素大时，说明e应该加在其右边
            if(e.compareTo(current.element) > 0){
                parent = current;
                current = current.right;
            }

            //当e比current的元素小时，说明e应该加在其左边
            else if(e.compareTo(current.element) < 0){
                parent = current;
                current = current.left;
            }

            //该元素已经找到
            else {
                break;
            }
        }

        //当树中没有此元素时返回false
        if(current == null){
            return false;
        }


        //当该节点没有左子节点时
        if(current.left == null){

            //连接父节点, 当父节点为空时说明删除该元素后树没有元素
            if(parent == null){
                root = current.right;
            }

            //当删除节点后父节点不为空时
            else {

                //如果被删除元素e小于父节点的元素，则将父节点的左节点赋给被删除元素的右节点
                if(e.compareTo(parent.element) < 0){
                    parent.left = current.right;
                }

                //将父节点的右节点赋给被删除元素的右节点
                else {
                    parent.right = current.right;
                }
            }

        }

        else{

            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null){
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;

            if(parentOfRightMost.right == rightMost){
                parentOfRightMost.right = rightMost.left;
            }
            else {
                parentOfRightMost.left = rightMost.left;
            }
        }

        size--;
        return true;
    }

    /**
     * 获取树的大小
     *
     * @return
     */
    @Override
    public int getSize() {
        return 0;
    }


    /**
     * 返回从root到e的路径
     * @param e
     * @return
     */
    public ArrayList<TreeNode<E>> path(E e){

        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root;

        //遍历树找到路径
        while (current != null){

            list.add(current);

            if(e.compareTo(current.element) > 0){
                current = current.right;
            }

            else if(e.compareTo(current.element) < 0){
                current = current.left;
            }

            else {
                break;
            }        }

        return list;
    }


    /**
     * 编程练习题26.2
     * 判断该树是否为完全二叉树
     * @return
     */
    public boolean isFullBinaryTree(){
        return 2*depth()-1 == getSize();
    }


    /**
     * 二叉树的深度
     * @return
     */
    public int depth(){
        TreeNode<E> temp = root;
        return depth(temp);
    }

    private int depth(TreeNode<E> root){

        int i = 0, j = 0;

        if(root == null) {
            return 0;
        }

        if(root.left !=null) {
            i = depth(root.left); // 左子树深度
        }

        else {
            i = 0;
        }

        if(root.right !=null) {
            j = depth(root.right);  // 右子树深度
        }
        else {
            j = 0;
        }

        return i>j ? i+1 :j+1;
    }


    //中序遍历，使用栈
    public List<E> inorderWithLoop(){
        return inorderWithLoop(root);
    }

    private List<E> inorderWithLoop(TreeNode<E> root){

        List<E> list=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<TreeNode>();

        if(root!=null) stack.push(root);

        while (!stack.isEmpty()) {

            root=stack.peek();

            stack.pop();

            list.add(root.element);

            if(root.right!=null) {
                stack.push(root.right);
            }

            if(root.left!=null) {
                stack.push(root.left);
            }

        }

        return list;

    }


    //中序遍历，使用递归
    public void inorder(){
        inorder(root);
    }

    protected void inorder(TreeNode<E> root){
        if(root == null){
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    //后序遍历，使用递归
    public void postorder(){
        postorder(root);
    }

    protected void postorder(TreeNode<E> root){
        if(root == null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }


    //前序遍历，使用递归
    public void preorder(){
        preorder(root);
    }

    protected void preorder(TreeNode<E> root){
        if(root == null){
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }
//    public ArrayList<E> preorder(){
//        ArrayList<E> temp = new ArrayList<>();
//        preorder(root, temp);
//        return temp;
//    }
//
//    public void preorder(TreeNode<E> root, ArrayList<E> temp){
//        if(root == null){
//            return;
//        }
//        System.out.print(root.element + " ");
//        temp.add(root.element);
//        preorder(root.left, temp);
//        preorder(root.right, temp);
//
//    }




    /**
     * 内部树节点类
     * @param <E>
     */
    public static class TreeNode<E extends Comparable<E>>{

        public E element;
        public TreeNode<E> left;
        public TreeNode<E> right;

        public TreeNode(E e){
            element = e;
        }
    }


    protected TreeNode<E> createNewNode(E e){
        return new TreeNode<>(e);
    }


    public InorderIterator inorderIterater(){
        return new InorderIterator();
    }

    class InorderIterator implements java.util.Iterator{

        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        public InorderIterator(){
            inorder();
        }

        private void inorder(){
            inorder(root);
        }


        private void inorder(TreeNode<E> root){
            if(root == null){
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext() {
            if(current < list.size()){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            return list.get(current++);
        }

        @Override
        public void remove() {
            delete(list.get(current));
            list.clear();
            inorder();
        }

        public void clear(){
            root = null;
            size = 0;
        }

        @Override
        public void forEachRemaining(Consumer consumer) {

        }
    }

}
