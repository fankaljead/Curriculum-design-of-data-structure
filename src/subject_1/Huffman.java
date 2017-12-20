package subject_1;

import util.heap.Heap;

import java.io.*;
import java.util.*;

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
//    private TreeMap<String, Character> codesWithKey = new TreeMap<>(new CodeComparator());//按照哈夫曼编码的长度依次保存编码及对应字符
    private Map<Character, String> codesWithKey = new HashMap<>();//按照哈夫曼编码的长度依次保存编码及对应字符


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
                this.codesWithKey.put((char)i, codes[i]);
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

    public Map<Character, String> getCodesWithKey() {
        return codesWithKey;
    }

    public void setCodesWithKey(Map<Character, String> codesWithKey) {
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
    private HuffmanTree getHuffmanTree(int[] counts) {
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

    public HuffmanTree getHuffmanTree() {
        return getHuffmanTree(counts); // 最终的树
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

    //存储哈夫曼编码
    public void storeCodes(String filename) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename + ".huf"));
        output.writeObject(codes);
    }

    //编码
    public void encode(String filename) throws IOException {
        BufferedInputStream fileInput = new BufferedInputStream(
                new FileInputStream(new File(filename)));
        BitOutputStream output = new BitOutputStream(new File(filename + ".new"));

        int r;
        while ((r = fileInput.read()) != -1 ) {
            output.writeBit(codes[r]);
        }
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


        //前序遍历
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
                return 1;
            else if(s.length() == t1.length()){
                if(s.compareTo(t1) > 0)
                    return 1;
                else if(s.compareTo(t1) == 0)
                    return 0;
                else
                    return -1;
            }
            else
                return -1;
        }

    }




    public String decode(EncodeResult decodeResult) {
        // 解码得到的字符串
        StringBuffer decodeStr = new StringBuffer();
        // 获得解码器
        Map<String, Character> decodeMap = getDecoder(decodeResult
                .getLetterCode());
        // 解码器键集合
        Set<String> keys = decodeMap.keySet();
        // 待解码的（被编码的）字符串
        String encode = decodeResult.getEncode();
        // 从最短的开始匹配之所以能够成功，是因为哈夫曼编码的唯一前缀性质
        // 临时的可能的键值
        String temp = "";
        // 改变temp值大小的游标
        int i = 1;
        while (encode.length() > 0) {
            temp = encode.substring(0, i);
            if (keys.contains(temp)) {
                Character character = decodeMap.get(temp);
                decodeStr.append(character);
                encode = encode.substring(i);
                i = 1;
            } else {
                i++;
            }
        }
        return decodeStr.toString();
    }

    /**
     * 获得解码器，也就是通过字母/编码对得到编码/字符对。
     *
     * @param letterCode
     * @return
     */
    private Map<String, Character> getDecoder(Map<Character, String> letterCode) {
        Map<String, Character> decodeMap = new HashMap<String, Character>();
        Set<Character> keys = letterCode.keySet();
        for (Character key : keys) {
            String value = letterCode.get(key);
            decodeMap.put(value, key);
        }
        return decodeMap;
    }





    public static class BitOutputStream {
        private FileOutputStream output;
        private int value;
        private int count = 0;
        private int mask = 1;

        public BitOutputStream(File file) throws IOException {
            output = new FileOutputStream(file);
        }

        public void writeBit(char bit) throws IOException {
            count++;
            value = value << 1;

            if (bit == '1')
                value = value | mask;

            if (count == 8) {
                output.write(value);
                count = 0;
            }
        }

        public void writeBit(String bitString) throws IOException {
            for (int i = 0; i < bitString.length(); i++)
                writeBit(bitString.charAt(i));
        }

        public void close() throws IOException {
            if (count > 0) {
                value = value << (8 - count);
                output.write(value);
            }

            output.close();
        }
    }
}
