package util.graph;


/**
 * Author: Zhou Xianghui
 * Time: 2017/11/6 22:02
 * Description: 程序清单28.01 加权边
 */
public class WeightedEdge extends AbstractGraph.Edge implements Comparable<WeightedEdge>{

    public int weight;//边的权重

    public WeightedEdge(int u, int v, int weight) {
        super(u, v);
        this.weight = weight;
    }

    /**
     * 比较两条边
     * @param weightedEdge
     * @return
     */
    @Override
    public int compareTo(WeightedEdge weightedEdge) {
        if(weight > weightedEdge.weight){
            return 1;
        }
        else if(weight == weightedEdge.weight){
            return 0;
        }
        else {
            return -1;
        }
    }
}
