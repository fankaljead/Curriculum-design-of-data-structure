package subject_1;

import util.heap.Heap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * Author: Zhou Xianghui
 * Time: 2017/11/27 17:10
 * Description: 哈夫曼编码
 */
public class Huffman implements Serializable { //实现Serializable接口可以使本对象被写如文件
    private String text;//输入的字符串
    private String[] codes;//生成的哈夫曼编码
    private int[] counts;//每个哈夫曼编码的权重
    private HuffmanTree tree;//生成的哈夫曼树
    private TreeMap<String, Character> codesWithKey = new TreeMap<>(new CodeComparator());//按照哈夫曼编码的长度依次保存编码及对应字符


    public Huffman(){

    }

    //创建一棵哈夫曼树
    public Huffman(String text) {
        this.text = text;
        this.counts = getCharacterFrequency(text);
        this.tree = getHuffmanTree(counts); //创建一棵霍夫曼树
        this.codes = getCode(tree.root);

        for (int i = 0; i < codes.length; i++) {
            if(codes[i] != null)
                this.codesWithKey.put(codes[i], (char)i);
        }

    }

    /** get与 set方法 **/
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        this.counts = getCharacterFrequency(text);
        this.tree = getHuffmanTree(counts); //创建一棵霍夫曼树
        this.codes = getCode(tree.root);
    }

    public String[] getCodes() {
        return codes;
    }

    public void setCodes(String[] codes) {
        this.codes = codes;
    }

    public int[] getCounts() {
        return counts;
    }

    public void setCounts(int[] counts) {
        this.counts = counts;
    }

    public HuffmanTree getTree() {
        return tree;
    }

    public void setTree(HuffmanTree tree) {
        this.tree = tree;
    }

    public TreeMap<String, Character> getCodesWithKey() {
        return codesWithKey;
    }

    public void setCodesWithKey(TreeMap<String, Character> codesWithKey) {
        this.codesWithKey = codesWithKey;
    }

    //获取哈夫曼编码
    public String[] getCode(HuffmanTree.TreeNode root) {
        if (root == null) return null;
        String[] codes = new String[2 * 128];
        assignCode(root, codes);
        return codes;
    }

    /* 从节点中获取哈夫曼编码 */
    private void assignCode(HuffmanTree.TreeNode root, String[] codes) {
        if (root.left != null) {
            root.left.code = root.code + "0";
            assignCode(root.left, codes);

            root.right.code = root.code + "1";
            assignCode(root.right, codes);
        }
        else {
            codes[(int)root.element] = root.code;
        }
    }

    /** 从哈夫曼编码中获得一棵哈夫曼树 */
    public HuffmanTree getHuffmanTree(int[] counts) {
        // 创建一个堆来保存树
        Heap<HuffmanTree> heap = new Heap<>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0)
                heap.add(new HuffmanTree(counts[i], (char)i)); //一个树叶节点
        }

        while (heap.getSize() > 1) {
            HuffmanTree t1 = heap.remove(); // 删除权重最小的树
            HuffmanTree t2 = heap.remove(); // 删除下一个权重最小的树

            heap.add(new HuffmanTree(t1, t2)); // 联合两棵树
        }

        return heap.remove(); // 最终的树
    }

    /** 获取字符出现的次数 */
    public int[] getCharacterFrequency(String text) {
        int[] counts = new int[256]; // 256个ASCII码字符

        for (int i = 0; i < text.length(); i++)
            counts[(int)text.charAt(i)]++; // 统计字符在字符串中出现的次数

        return counts;
    }

    public String getHuffmanCodes() {
        String temp = "";
        for (int i = 0; i < text.length(); i++) {
            temp += codes[(int)(text.charAt(i))];
        }
        return temp;
    }


    /** 定义一棵哈夫曼编码树 */
    public class HuffmanTree implements Comparable<HuffmanTree>, Serializable{
        public TreeNode root; // The root of the tree

        /** 通过两棵子树创建一棵树 */
        public HuffmanTree(HuffmanTree t1, HuffmanTree t2) {
            root = new TreeNode();
            root.left = t1.root;
            root.right = t2.root;
            root.weight = t1.root.weight + t2.root.weight;
        }

        /** 创建一棵树包含一个叶子节点 */
        public HuffmanTree(int weight, char element) {
            root = new TreeNode(weight, element);
        }

        /** 通过权重比较两棵树 */
        public int compareTo(HuffmanTree o) {
            if (root.weight < o.root.weight) // Purposely reverse the order
                return 1;
            else if (root.weight == o.root.weight)
                return 0;
            else
                return -1;
        }

        public ArrayList<Character> preorder(){
            ArrayList<Character> temp = new ArrayList<>();
            preorder(root, temp);
            return temp;
        }

        public void preorder(TreeNode root, ArrayList<Character> temp){
            if(root == null){
                return;
            }
            System.out.print(root.element + " ");
            temp.add(root.element);
            preorder(root.left, temp);
            preorder(root.right, temp);

        }

        public class TreeNode implements Serializable{
            char element; // 存储节点的字符
            int weight; // 存储节点的权重
            TreeNode left; // 左节点
            TreeNode right; // 右节点
            String code = ""; // 这个节点到根节点的编码

            /** 创建一个空节点 */
            public TreeNode() {
            }

            /** 通过特定的权值和字符创建一个节点 */
            public TreeNode(int weight, char element) {
                this.weight = weight;
                this.element = element;
            }
        }
    }


    public class CodeComparator implements Comparator<String> , Serializable{

        @Override
        public int compare(String s, String t1) {
            if(s.length() > t1.length())
                return -1;
            else if(s.length() == t1.length()){
                if(s.compareTo(t1) > 0)
                    return -1;
                else if(s.compareTo(t1) == 0)
                    return 0;
                else
                    return 1;
            }
            else
                return 1;
        }

    }
}
