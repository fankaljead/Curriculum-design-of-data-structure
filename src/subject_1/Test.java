package subject_1;

/**
 * Author: Zhou Xianghui
 * Time: 2017/11/29 10:08
 * Description:
 */
public class Test {
    public static void main(String[] args) {
        Huffman huffman = new Huffman("assadass");
        System.out.println(huffman.getTree().preorder().toString());
        System.out.println();
        for (int i = 0; i < huffman.getCodes().length; i++) {
            if(huffman.getCodes()[i] != null)
                System.out.println((char)i + ": code " + huffman.getCodes()[i] + ", cishu " + huffman.getCounts()[i]);
        }
    }
}
