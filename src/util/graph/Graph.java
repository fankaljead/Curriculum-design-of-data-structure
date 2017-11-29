package util.graph;


/**
 * Author: Zhou Xianghui
 * Time: 2017/10/30 15:53
 * Description: 图接口 程序清单27.2
 */
public interface Graph<V> {


    /**
     * 获取图中的顶点数
     * @return
     */
    public int getSize();


    /**
     * 获取图中的所有顶点
     * @return
     */
    public java.util.List<V> getVertices();


    /**
     * 获取图中指定下标的顶点
     * @param index
     * @return
     */
    public V getVertex(int index);


    /**
     * 获取图中指定元素的下标
     * @param v
     * @return
     */
    public int getIndex(V v);


    /**
     * 获取图中指定下标元素的所有邻居
     * @param index
     * @return
     */
    public java.util.List<Integer> getNeighbors(int index);


    /**
     * 获取图中指定下标元素的度数
     * @param index
     * @return
     */
    public int getDegree(int index);


    /**
     * 获取图的邻接矩阵
     * @return
     */
    public int[][] getAdjacencyMatrix();


    /**
     * 打印图的邻接矩阵
     */
    public void printAdjacencyMatrix();


    /**
     * 打印图的边
     */
    public void printEdges();


    /**
     * 返回一棵深度优先树
     * @param v
     * @return
     */
    public AbstractGraph<V>.Tree dfs(int v);


    /**
     * 返回一棵广度优先树
     * @param v
     * @return
     */
    public AbstractGraph<V>.Tree bfs(int v);
}
