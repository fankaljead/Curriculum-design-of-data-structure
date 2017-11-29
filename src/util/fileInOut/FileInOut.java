package util.fileInOut;

import java.io.*;
import java.util.Scanner;

/**
 * Author: Zhou Xianghui
 * Time: 2017/11/29 11:08
 * Description: 文件输入输出类
 */
public class FileInOut<E> implements Serializable{

    /** 输出到文件 **/
    public void writeToFile(String fileName, String text) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new File(fileName));
        out.write(text);
        out.close();
    }

    /** 输出到文件 **/
    public void writeToFile(String fileName, String... text) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new File(fileName));
        for (String t : text
                ) {
            out.write(t);
        }
        out.close();
    }

    /**
     * 将对象写入指定文件中
     * @param e
     * @param fileName
     * @throws IOException
     */
    public void writeObjectToFile(E e, String fileName) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject((Object) e);
        out.close();
    }



    /**
     * 从指定文件中读取对象
     * @param fileName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public E readObjectFromFile(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        E e = (E)in.readObject();
        in.close();

        return e;
    }



    /**
     * 从指定文件中读取所有字符
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public String readFromFile(String fileName) throws FileNotFoundException {
        String temp = "";
        Scanner in = new Scanner(new File(fileName));

        while (in.hasNext()){
            temp += in.nextLine();
        }

        in.close();

        return temp;
    }



}
