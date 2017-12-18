package subject_4.main.version_3.model;

import util.graph.AbstractGraph;
import util.graph.UnweightedGraph;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/18 15:05
 * Description:
 */
public class USMap {
    private static final long serialVersionUID = 1L;

    public City[] vertices =
            { new City("Seattle", 75, 50), new City("San Franciso", 50, 210),
                    new City("Los Angeles", 75, 275), new City("Denver", 275, 175),
                    new City("Kansas City", 400, 245), new City("Chicago", 450, 100),
                    new City("Boston", 700, 80), new City("New York", 675, 120),
                    new City("Atlanta", 575, 295), new City("Miami", 600, 400),
                    new City("Dallas", 408, 325), new City("Houston", 450, 360) };

    public int[][] edges =
            {{ 0, 1 }, { 0, 3 },{ 0, 5 },
                    { 1, 0 }, { 1, 2 }, { 1, 3 },
                    { 2, 1 }, { 2, 3 }, { 2, 4 }, { 2, 10 },
                    { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 4 }, { 3, 5 },
                    { 4, 2 }, { 4, 3 }, { 4, 5 }, { 4, 7 }, { 4, 8 }, { 4, 10 },
                    { 5, 0 }, { 5, 3 }, { 5, 4 }, { 5, 6 }, { 5, 7 },
                    { 6, 5 }, { 6, 7 },
                    { 7, 4 }, { 7, 5 }, { 7, 6 }, { 7, 8 },
                    { 8, 4 }, { 8, 7 }, { 8, 9 }, { 8, 10 }, { 8, 11 },
                    { 9, 8 }, { 9, 11 },
                    { 10, 2 }, { 10, 4 }, { 10, 8 }, { 10, 11 },
                    { 11, 8 }, { 11, 9 }, { 11, 10 } };
    public UnweightedGraph<City> graph = new UnweightedGraph<City>(edges, vertices);
}
