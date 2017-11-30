package subject_1;


import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Author: Zhou Xianghui
 * Time: 2017/11/29 10:08
 * Description:
 */
public class Test {
    public static void main(String[] args) {
        TreeMap<String, Character> treeMap = new TreeMap<>(new M());
        treeMap.put("111111", 'A');
        treeMap.put("111110", '0');
        treeMap.put("1111", 'b');
        treeMap.put("111111111", 'c');
        treeMap.put("1001", 'e');

        System.out.println(Character.toString(treeMap.get(treeMap.firstKey())));

        for (int i = 0; !treeMap.isEmpty(); i++) {
            System.out.println(treeMap.firstKey() + " : " + treeMap.get(treeMap.firstKey()));
            treeMap.remove(treeMap.firstKey());
        }
    }
}


class M implements Comparator<String>{

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