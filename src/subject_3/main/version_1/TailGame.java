package subject_3.main.version_1;

import util.graph.AbstractGraph;
import util.graph.UnweightedGraph;

import java.util.*;


/**
 * Author: Zhou Xianghui
 * Time: 2017/12/7 9:47
 * Description:
 */
public class TailGame implements ITailGame{

    public final static int SIDES_OF_TAIL = 2;//每枚硬币的面数，正反两面
    public final static char[] CHAR_OF_EACH_SIDE = {'H', 'T'};//硬币每面的字符

    private int rows = 4;//行数
    private int columns = 4;//列数
    private int tailNumber = rows * columns;
    private int numberOfNodes = (int)Math.pow(SIDES_OF_TAIL, tailNumber);//硬币问题所有硬币出现的情形
    protected AbstractGraph<Integer>.Tree tree;


    public TailGame() {
        this.setInitial();//初始化
    }

    public TailGame(int rows) {
        this.rows = rows;
        this.setInitial();//初始化
    }

    public TailGame(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.setInitial();//初始化
    }



    //Get Set方法
    //初始化对象 设置硬币总数 ···
    private void setInitial(){
        this.setTailNumber();//必须先设置硬币总数
        this.setNumberOfNodes();

        List<AbstractGraph.Edge> edges = getEdges();
        UnweightedGraph<Integer> graph = new UnweightedGraph<Integer>(edges, numberOfNodes);
        tree = graph.bfs(numberOfNodes - 1);
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    private void setNumberOfNodes() {
        this.numberOfNodes = (int)Math.pow(SIDES_OF_TAIL, tailNumber);
    }

    public int getTailNumber() {
        return tailNumber;
    }

    private void setTailNumber() {
        this.tailNumber = rows * columns;
    }

    private List<AbstractGraph.Edge> getEdges(){
        List<AbstractGraph.Edge> edges = new ArrayList<>();

        for (int u = 0; u < numberOfNodes; u++) {
            for (int k = 0; k < tailNumber; k++) {
                char[] node = getNode(u);
                if(node[k] == CHAR_OF_EACH_SIDE[0]){//为head时 'H'
                    int v = getFlippedNode(node, k);

                    edges.add(new AbstractGraph.Edge(v, u));
                }
            }
        }
        return edges;
    }


    /**
     * 获取指定下标的节点
     * @param index
     * @return
     */
    public char[] getNode(int index){
        char[] result = new char[tailNumber];

        for (int i = 0; i < tailNumber; i++) {
            int digit = index % SIDES_OF_TAIL;//为 0 -> 'H'或 1 -> 'T'
            if(digit == 0){
                result[tailNumber - 1 - i] = CHAR_OF_EACH_SIDE[0];//为head
            }
            else {
                result[tailNumber - 1 - i] = CHAR_OF_EACH_SIDE[1];//为tail
            }
            index = index / SIDES_OF_TAIL;
        }

        return result;
    }


    public int getFlippedNode(char[] node, int position){
        int row = position / rows;//指定下标所在的行数
        int column = position % rows;//指定下标的列数

        //翻转硬币 默认规则
        flipACell(node, row, column);
        flipACell(node, row - 1, column);
        flipACell(node, row + 1, column);
        flipACell(node, row, column - 1);
        flipACell(node, row, column + 1);

        return getIndex(node);
    }

    public void flipACell(char[] node, int row, int column){
        if(row >= 0 && row < rows && column >= 0 && column < columns){
            if(node[row * rows + column] == CHAR_OF_EACH_SIDE[0]){//head
                node[row * rows + column] = CHAR_OF_EACH_SIDE[1];//tail
            }
            else {
                node[row * rows + column] = CHAR_OF_EACH_SIDE[0];//head
            }
        }
    }

    /**
     * 获取指定节点的下标
     * @param node
     * @return
     */
    public int getIndex(char[] node){
        int result = 0;

        for (int i = 0; i < tailNumber; i++) {
            if(node[i] == 'T'){
                result = result * SIDES_OF_TAIL + 1;
            }
            else {
                result = result * SIDES_OF_TAIL + 0;
            }
        }

        return result;
    }


    public List<Integer> getShortestPath(int nodeIndex){
        return tree.getPath(nodeIndex);
    }

    public void printNode(char[] node){
        for (int i = 0; i < tailNumber; i++) {
            if(i % rows != rows - 1){
                System.out.print(node[i]);
            }else {
                System.out.println(node[i]);
            }
        }
        System.out.println();
    }

    /**
     * 设置硬币翻转的规则
     */
    @Override
    public void setRules() {

    }

    @Override
    public String toString() {
        return "TailGame{" +
                "rows=" + rows +
                ", columns=" + columns +
                ", tailNumber=" + tailNumber +
                ", numberOfNodes=" + numberOfNodes +
                ", tree=" + tree +
                '}';
    }
}
