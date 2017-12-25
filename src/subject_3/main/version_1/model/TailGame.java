package subject_3.main.version_1.model;

import util.graph.AbstractGraph;
import util.graph.UnweightedGraph;

import java.util.*;


/**
 * Author: Zhou Xianghui
 * Time: 2017/12/7 9:47
 * Description: 硬币问题
 */
public class TailGame implements ITailGame{

    public final static int SIDES_OF_TAIL = 2;//每枚硬币的面数，正反两面
    public final static char[] CHAR_OF_EACH_SIDE = {'H', 'T'};//硬币每面的字符


    private int rows = 4;//行数
    private int columns = 4;//列数
    private int tailNumber = rows * columns;
    private int numberOfNodes = (int)Math.pow(SIDES_OF_TAIL, tailNumber);//硬币问题所有硬币出现的情形
    protected AbstractGraph<Integer>.Tree tree;
    private ArrayList<Rules> rules;//规则类


    public TailGame() {
        this.setInitial();//初始化
    }

    public TailGame(int rows, int columns, ArrayList<Rules> rules) {
        this.rows = rows;
        this.columns = columns;
        if(rules.size() != 0){
            this.rules = rules;
        }else {
            setRules();
        }
        this.tailNumber = rows * columns;
        setInitial();
    }

    public TailGame(int rows) {
        this.rows = rows;
        this.setInitial();//初始化
        this.tailNumber = rows * this.columns;
        setInitial();
    }

    public TailGame(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.setInitial();//初始化
        this.tailNumber = rows * columns;
        setInitial();
    }



    //Get Set方法
    //初始化对象 设置硬币总数 ···
    private void setInitial(){
        //this.setTailNumber();//必须先设置硬币总数
        this.setNumberOfNodes();//设置硬币出现的所有次数

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

    public ArrayList<Rules> getRules() {
        return rules;
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
//        flipACell(node, row, column);
//        flipACell(node, row - 1, column);
//        flipACell(node, row + 1, column);
//        flipACell(node, row, column - 1);
//        flipACell(node, row, column + 1);


        //设置规则
        for (int i = 0; i < rules.size(); i++) {
            flipACell(node, row + this.rules.get(i).getRowToNode(),
                    column + this.rules.get(i).getColumnToNode());
        }

        return getIndex(node);
    }

    /**
     * 翻转一枚硬币
     * @param node
     * @param row 行数
     * @param column //列数
     */
    public void flipACell(char[] node, int row, int column){
        if(row >= 0 && row < rows &&
                column >= 0 && column < columns &&
                row * rows + column < tailNumber && //判断要翻转的硬币是否在tailNumber下标以内，否则这不翻转
                row * rows + column >= 0){//判断要翻转的硬币是否在tailNumber下标以内，否则这不翻转

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

        for (int i = 0; i < this.tailNumber; i++) {
            if(node[i] == CHAR_OF_EACH_SIDE[1]){
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
            if(i % columns != columns - 1){
                System.out.print(node[i]);
            }else {
                System.out.println(node[i]);
            }
        }
        System.out.println();
    }

    /**
     * 设置硬币翻转的默认规则
     */
    @Override
    public void setRules() {
        this.rules = new ArrayList<>();
        rules.add(new Rules(0,0));
        rules.add(new Rules(1, 0));
        rules.add(new Rules(0,1));
        rules.add(new Rules(-1, 0));
        rules.add(new Rules(0,-1));
    }

    /**
     * 设置规则
     * @param rules
     */
    public void setRules(ArrayList<Rules> rules) {

        this.rules = rules;
    }

    //规则类
    public static class Rules{
        int rowToNode;//相对于position的行数
        int columnToNode;//相对与position的列数

        public Rules() {
        }

        public Rules(int rowToNode, int columnToNode) {
            this.rowToNode = rowToNode;
            this.columnToNode = columnToNode;
        }

        public int getRowToNode() {
            return rowToNode;
        }

        public void setRowToNode(int rowToNode) {
            this.rowToNode = rowToNode;
        }

        public int getColumnToNode() {
            return columnToNode;
        }

        public void setColumnToNode(int columnToNode) {
            this.columnToNode = columnToNode;
        }

        @Override
        public String toString() {
            return "Rules{" +
                    "rowToNode=" + rowToNode +
                    ", columnToNode=" + columnToNode +
                    '}';
        }
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
