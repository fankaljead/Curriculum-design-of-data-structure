package util.graph;


import java.util.*;

/**
 * Author: Zhou Xianghui
 * Time: 2017/11/6 22:06
 * Description: 程序清单28.02 加权图类
 */

public class WeightedGraph<V> extends AbstractGraph<V> {

    //优先邻接表
    protected List<PriorityQueue<WeightedEdge>> queues;

    /**
     * 通过边矩阵和顶点数组创建图
     * @param edges 边矩阵
     * @param vertices 顶点数组
     */
    public WeightedGraph(int[][] edges, V[] vertices){
        super(edges, vertices);
        createQueues(edges, vertices.length);
    }

    /**
     * 通过边矩阵和顶点数创建图
     * @param edges 边矩阵
     * @param numberOfVertices 顶点数
     */
    public WeightedGraph(int[][] edges, int numberOfVertices){
        super(edges, numberOfVertices);
        createQueues(edges, numberOfVertices);
    }


    /**
     * 通过边线性表和顶点线性表创建图
     * @param edges 边线性表
     * @param vertices 顶点线性表
     */
    public WeightedGraph(List<WeightedEdge> edges, List<V> vertices){
        super((List) edges, vertices);
        createQueues(edges, vertices.size());
    }


    /**
     * 通过边线性表和顶点数创建图
     * @param edges 边线性表
     * @param numberOfVertices 顶点数
     */
    public WeightedGraph(List<WeightedEdge> edges, int numberOfVertices){
        super((List) edges, numberOfVertices);
        createQueues(edges, numberOfVertices);
    }

    /**
     * 由边矩阵和顶点个数创建优先队列
     * @param edges 边矩阵
     * @param numberOfVertices 顶点个数
     */
    private void createQueues(int[][] edges, int numberOfVertices) {
        queues = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            queues.add(new PriorityQueue<WeightedEdge>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];//边起点
            int v = edges[i][1];//边终点
            int weight = edges[i][2];//边的权值
            queues.get(u).offer(new WeightedEdge(u, v, weight));
        }
    }


    /**
     * 由边线性表和顶点个数创建优先队列
     * @param edges 边线性表
     * @param numberOfVertices 顶点个数
     */
    private void createQueues(List<WeightedEdge>edges, int numberOfVertices) {
        queues = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            queues.add(new PriorityQueue<WeightedEdge>());
        }

        for (WeightedEdge edge : edges
             ) {
            queues.get(edge.u).offer(edge);//向队列中插入一条边
        }
    }


    /**
     * 打印加权边
     */
    public void printWeightedEdges(){
        for (int i = 0; i < queues.size(); i++) {
            System.out.print("Vertex " + i +": ");
            for (WeightedEdge edge : queues.get(i)
                 ) {
                System.out.print("(" + edge.u + ", " + edge.v + ", " + edge.weight + ")");
            }
            System.out.println();
        }
    }

    /**
     * 得到从顶点0开始的最小生成树
     * @return MST
     */
    public MST getMinimumSpanningTree(){
        return getMinimumSpanningTree(0);
    }

    /**
     * 得到从顶点startingIndex开始的最小生成树
     * @param startingIndex
     * @return MST
     */
    public MST getMinimumSpanningTree(int startingIndex) {
        List<Integer> T = new ArrayList<>();
        T.add(startingIndex);

        int numberOfVertices = vertices.size();//顶点个数
        int[] parent = new int[numberOfVertices];//顶点的父顶点
        //初始化所有顶点的父顶点为-1
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        int totalWeight = 0;//生成树的权重和

        //克隆这个队列，以便保持原有的队列
        List<PriorityQueue<WeightedEdge>> queues = deepClone(this.queues);

        //是否找到所有的顶点？
        while(T.size() < numberOfVertices){
            //在T中搜索最小的边
            int v = -1;
            double smallestWeight = Double.MAX_VALUE;
            for (int u : T
                 ) {
                while (!queues.get(u).isEmpty() && T.contains(queues.get(u).peek().v)){
                    queues.get(u).remove();
                }

                if(queues.get(u).isEmpty()){
                    continue;
                }

                //当前u的邻接边最小权值
                WeightedEdge edge = queues.get(u).peek();
                if(edge.weight  < smallestWeight){
                    v = edge.v;
                    smallestWeight = edge.weight;

                    parent[v] = u;
                }
            }

            T.add(v);
            totalWeight += smallestWeight;
        }
        return new MST(startingIndex, parent, T, totalWeight);
    }


    /**
     * 深度克隆一个线性表
     * @param queues
     * @return
     */
    protected List<PriorityQueue<WeightedEdge>> deepClone(List<PriorityQueue<WeightedEdge>> queues) {
        List<PriorityQueue<WeightedEdge>> copiedQueues = new ArrayList<>();

        for (int i = 0; i < queues.size(); i++) {
            copiedQueues.add(new PriorityQueue<WeightedEdge>());
            for (WeightedEdge e : queues.get(i)
                 ) {
                copiedQueues.get(i).add(e);
            }
        }

        return copiedQueues;
    }


    public ShortestPathTree getShortestPathTree(int sourceIndex){
        List<Integer> T = new ArrayList<>();
        T.add(sourceIndex);

        int numberOfVertices = vertices.size();

        int[] parent = new int[numberOfVertices];
        parent[sourceIndex] = -1;

        int[] costs = new int[numberOfVertices];
        for (int i = 0; i < costs.length; i++) {
            costs[i] = Integer.MAX_VALUE;
        }
        costs[sourceIndex] = 0;

        List<PriorityQueue<WeightedEdge>> queues = deepClone(this.queues);

        while(T.size() < numberOfVertices){
            int v = -1;
            int smallestCost = Integer.MAX_VALUE;
            for (int u : T
                 ) {
                while (!queues.get(u).isEmpty() &&
                        T.contains(queues.get(u).peek().v)){
                    queues.get(u).remove();
                }

                if(queues.get(u).isEmpty()){
                    continue;
                }

                WeightedEdge edge = queues.get(u).peek();
                if(costs[u] + edge.weight < smallestCost){
                    v = edge.v;
                    smallestCost = costs[u] + edge.weight;

                    parent[v] = u;
                }
            }

            T.add(v);
            costs[v] = smallestCost;
        }

        return new ShortestPathTree(sourceIndex, parent, T, costs);
    }

    //MST加权图内部类
    public class MST extends Tree{
        private int totalWeight;//所有边的总权重

        public MST(int root, int[] parent, List<Integer> searchOrder, int totalWeight){
            super(root, parent, searchOrder);
            this.totalWeight = totalWeight;
        }

        public int getTotalWeight() {
            return totalWeight;
        }
    }


    /**
     * 最新生成树类
     */
    public class ShortestPathTree extends Tree{
        private int[] costs;//cost[v]是v到source的cost

        public ShortestPathTree(int source, int[] parent, List<Integer> searchOrder, int[] costs){
            super(source, parent, searchOrder);
            this.costs = costs;
        }

        public int getCosts(int v) {
            return costs[v];
        }

        public void printAllPaths(){
            System.out.println("All shortest paths from " + vertices.get(getRoot()) + " are:");
            for (int i = 0; i < costs.length; i++) {
                printPath(i);
                System.out.println("(cost: " + costs[i] + ")");
            }
        }
    }


    public List<PriorityQueue<WeightedEdge>> getWeightedEdges() {
        return queues;
    }
}
