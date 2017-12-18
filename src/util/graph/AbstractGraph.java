package util.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Zhou Xianghui
 * Time: 2017/10/30 16:05
 * Description: 抽象图类 程序清单27.3
 */
public class AbstractGraph<V> implements Graph<V>{

    protected List<V> vertices;//存储顶点
    protected List<List<Integer>> neighbors;//邻接线性表


    protected AbstractGraph(){

    }

    /**
     * 有数组建立一张图
     * @param edges
     * @param vertices
     */
    protected AbstractGraph(int[][] edges, V[] vertices){
        this.vertices = new ArrayList<>();
        for (int i = 0; i < vertices.length; i++) {
            this.vertices.add(vertices[i]);
        }

        createAdjacencyLists(edges, vertices.length);
    }


    /**
     * 通过对象来创建图
     * @param edges
     * @param vertices
     */
    public AbstractGraph(List<Edge> edges, List<V> vertices){
        this.vertices = vertices;
        createAdjacencyLists(edges, vertices.size());
    }

    public AbstractGraph(List<Edge> edges, int numberOfVertices){
        vertices = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add((V) new Integer(i));
        }
        createAdjacencyLists(edges, numberOfVertices);
    }


    protected AbstractGraph(int[][] edges, int numberOfVertices) {
        vertices = new ArrayList<V>();
        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add((V)(new Integer(i)));
        }
        createAdjacencyLists(edges, numberOfVertices);
    }


    private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
        neighbors = new ArrayList<List<Integer>>();
        for (int i = 0; i < numberOfVertices; i++) {
            neighbors.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            neighbors.get(u).add(v);
        }
    }


    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
        neighbors = new ArrayList<List<Integer>>();
        for (int i = 0; i < numberOfVertices; i++) {
            neighbors.add(new ArrayList<Integer>());
        }

        for (Edge edge : edges
             ) {
            neighbors.get(edge.u).add(edge.v);

        }
    }

    /**
     * 获取图中的顶点数
     *
     * @return
     */
    @Override
    public int getSize() {
        return vertices.size();
    }


    /**
     * 获取图中的所有顶点
     *
     * @return
     */
    @Override
    public List<V> getVertices() {
        return vertices;
    }


    /**
     * 获取图中指定下标的顶点
     *
     * @param index
     * @return
     */
    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }


    /**
     * 判断图是否联通
     * @return
     */
    public boolean isConnected(){
        return vertices.size() == dfs(0).getNumberOfVerticesFound();
    }

    /**
     * 获取图中指定元素的下标
     *
     * @param v
     * @return
     */
    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }


    /**
     * 获取图中指定下标元素的所有邻居
     *
     * @param index
     * @return
     */
    @Override
    public List<Integer> getNeighbors(int index) {
        return neighbors.get(index);
    }


    /**
     * 获取图中指定下标元素的度数
     *
     * @param index
     * @return
     */
    @Override
    public int getDegree(int index) {
        return neighbors.get(index).size();
    }


    /**
     * 获取图的邻接矩阵
     *
     * @return
     */
    @Override
    public int[][] getAdjacencyMatrix() {

        int[][] adjacencyMatrix = new int[getSize()][getSize()];

        for (int i = 0; i < neighbors.size(); i++) {
            for (int j = 0; j < neighbors.get(i).size(); j++) {
                int v = neighbors.get(i).get(j);
                adjacencyMatrix[i][v] = 1;
            }
        }

        return adjacencyMatrix;
    }


    /**
     * 打印图的邻接矩阵
     */
    @Override
    public void printAdjacencyMatrix() {
        int[][] adjacencyMatrix = getAdjacencyMatrix();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[0].length; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }

            System.out.println();
        }
    }

    /**
     * 打印图的边
     */
    @Override
    public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.print("Vertex " + u + ": ");
            for (int j = 0; j < neighbors.get(u).size(); j++) {
                System.out.print("(" + u + ", " +
                        neighbors.get(u).get(j) + ") ");
            }
            System.out.println();
        }
    }


    /**
     * 返回一棵深度优先树
     *
     * @param v
     * @return
     */
    @Override
    public Tree dfs(int v) {
        List<Integer> searchOrders = new ArrayList<Integer>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1; //初始化parent

        // 初始化顶点为未访问
        boolean[] isVisited = new boolean[vertices.size()];

        //深度搜索
        dfs(v, parent, searchOrders, isVisited);


        return new Tree(v, parent, searchOrders);
    }


    private void dfs(int v, int[] parent, List<Integer> searchOrders,
                     boolean[] isVisited) {
        //存储已经访问的顶点
        searchOrders.add(v);
        isVisited[v] = true;

        for (int i : neighbors.get(v)) {
            if (!isVisited[i]) {
                parent[i] = v;
                dfs(i, parent, searchOrders, isVisited);
            }
        }
    }


    /**
     * 找出两个顶点间的最短距离
     * @param u
     * @param v
     * @return
     */
    public List<Integer> getPath(int u, int v){
        Tree tree = bfs(u);
        ArrayList<Integer> path = (ArrayList<Integer>) tree.getPath(v);

        return path;

    }

    /**
     * 返回一棵广度优先树
     *
     * @param v
     * @return
     */
    @Override
    public Tree bfs(int v) {
        List<Integer> searchOrders = new ArrayList<Integer>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1; //初始化parent

        java.util.LinkedList<Integer> queue =
                new java.util.LinkedList<Integer>();
        boolean[] isVisited = new boolean[vertices.size()];
        queue.offer(v);
        isVisited[v] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            searchOrders.add(u);
            for (int w : neighbors.get(u)) {
                if (!isVisited[w]) {
                    queue.offer(w);
                    parent[w] = u;
                    isVisited[w] = true;
                }
            }
        }

        return new Tree(v, parent, searchOrders);
    }


    public static class Edge{
        public int u;
        public int v;

        public Edge(int u, int v){
            this.u = u;
            this.v = v;
        }
    }

    /**
     * 内部类，用来存储广度优先、深度优先的返回树
     */
    public class Tree{

        private int root;//树的根节点
        private int[] parent;//存储每个顶点的父节点
        private List<Integer> searchOrders;//存储搜索顺序


        public Tree(){

        }

        /**
         * 包括树根节点、每个顶点的父节点、搜索顺序的构造方法
         * @param root
         * @param parent
         * @param searchOrders
         */
        public Tree(int root, int[] parent, List<Integer> searchOrders){
            this.root = root;
            this.parent = parent;
            this.searchOrders = searchOrders;
        }

        public Tree(int root, int[] parent){
            this.root = root;
            this.parent = parent;
        }


        /**
         * 返回树的根节点
         * @return
         */
        public int getRoot(){
            return root;
        }

        /**
         * 获取某个节点的父节点
         * @param v
         * @return
         */
        public int getParent(int v){
            return parent[v];
        }

        /**
         * 获取搜索顺序的线性表
         * @return
         */
        public List<Integer> getSearchOrders(){
            return searchOrders;
        }

        /**
         * 返回顶点个数
         * @return
         */
        public int getNumberOfVerticesFound(){
            return searchOrders.size();
        }

        /**
         * 返回从指定顶点到根节点的路径
         * @param index
         * @return
         */
        public List<V> getPath(int index){
            ArrayList<V> path = new ArrayList<>();

            do{
                path.add(vertices.get(index));
                index = parent[index];
            }while (index != -1);


            return path;
        }

        /**
         * 打印从root到指定节点的路径
         * @param index
         */
        public void printPath(int index){
            List<V> path = getPath(index);
            System.out.print("A path from " + vertices.get(root) + " to " + vertices.get(index) + " : ");
            for (int i = path.size(); i >= 0; i--) {
                System.out.print(path.get(i) + " ");
            }

        }


        /**
         * 打印整棵树
         */
        public void printTree(){
            System.out.println("Root is: " + vertices.get(root));
            System.out.print("Edges: ");
            for (int i = 0; i < parent.length; i++) {
                if(parent[i] != -1){
                    System.out.print("(" + vertices.get(parent[i]) + ", " + vertices.get(i) + ") ");
                }
            }
            System.out.println();
        }
    }



}
