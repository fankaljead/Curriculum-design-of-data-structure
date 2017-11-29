package util.graph;

import java.util.List;

/**
 * Author: Zhou Xianghui
 * Time: 2017/10/30 16:47
 * Description: 普通图类 程序清单27.4
 */
public class UnweightedGraph<V> extends AbstractGraph<V> {


    public UnweightedGraph(int[][] edges, V[] vertices) {
        super(edges, vertices);
    }


    public UnweightedGraph(List<Edge> edges, List<V> vertices) {
        super(edges, vertices);
    }


    public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }


    public UnweightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }
}
