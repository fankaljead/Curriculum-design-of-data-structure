package subject_5.main.model;

import util.graph.AbstractGraph;
import util.graph.Graph;
import util.graph.UnweightedGraph;

public class CrossRiver {

	// 十进制转换为二进制对应 农夫 狼 羊 白菜
	private final int Total_States = 16;// 改变状态时异或需要的值ֵ
	private final int Change_Farmer = 8; // 二进制1000 农夫
	private final int Change_Worf = 4; // 二进制0100  狼
	private final int Change_Sheep = 2; // 二进制0010 羊
	private final int Change_Food = 1; // 二进制0001 白菜
	private final int[] Change_State_Array = { Change_Farmer, Change_Worf,
			Change_Sheep, Change_Food };//8、4、2、1 

	private Integer[] vertices = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	
	//得到安全的状态״̬
	public int[][] getEdges() {
		int[][] edges = new int[Total_States][4];//16个4位二进制状态
		
		for(int i = 0 ; i < edges.length ; i++) //共16个状态
			
			//初始状态赋值为-1
			for(int j = 0 ;j< edges[i].length ;j++){ //4位二进制数
				edges[i][j] = -1 ;//初始化所有为-1，即未访问状态  
			}
		
			for(int locate = 0 ; locate < 16 ;locate++) {
				
				int nextState = -1; 
				
				for (int i = 0; i < 4; i++) {
					
					if (i == 0) {
						//农夫自己走
						nextState = locate ^ Change_State_Array[i];//当两对应的二进位相异时，结果为1
					}
					else{  
						nextState = locate ^ Change_State_Array[0] //农夫走并和另外的
								^ Change_State_Array[i];
					}
					
					if (isSafe(nextState)){
						edges[locate][i] = nextState ; //如果下一个状态安全，则存入数组 
					}
				}
				
			}
		
		return edges ;
	}

	//得到状态图
	public int[] getRoute2() {
		CrossRiver cr = new CrossRiver();
		int[][] edges = cr.getEdges() ;
		int[][] allEdges = cr.getAllEdges(edges);
		
		Graph<Integer> graph = new UnweightedGraph<Integer>(allEdges,cr.getVertices()) ;
		AbstractGraph<Integer>.Tree dfs = graph.dfs(0);
		java.util.List<Integer> searchOrders = dfs.getSearchOrders();
		
		int[] route2 = new int[16];
		
		for(int i = 0 ;i<16 ; i++) {
			route2[i] = -1;
		}
		
		int count = 0 ;
		int allCount = 1 ;
		
		while(allCount <searchOrders.size()-2 ) {
			route2[count]=graph.getVertex(searchOrders.get(allCount));
			count = searchOrders.get(allCount) ;
			allCount++ ;
		}
		
		return route2;
	}
	
	public int[] getRoute() {
		CrossRiver cr = new CrossRiver();
		int[][] edges = cr.getEdges() ;
		int[][] allEdges = cr.getAllEdges(edges);
		Graph<Integer> graph = new UnweightedGraph<Integer>(allEdges,cr.getVertices()) ;
		AbstractGraph<Integer>.Tree dfs = graph.dfs(15);
		java.util.List<Integer> searchOrders = dfs.getSearchOrders();
		int[] route2 = new int[16];
		
		for(int i = 0 ;i<16 ; i++){
			route2[i] = -1;
		}
		
		int count = 0 ;
		int allCount = searchOrders.size()-3 ;
		
		while(allCount >= 0 ) {
			route2[count]=graph.getVertex(searchOrders.get(allCount));
			count = searchOrders.get(allCount) ;
			allCount-- ;
		}
		
		return route2;
	}
	
	public Integer[] getVertices() {
		return vertices ;
	}
	
	//得到图的边
	public int[][] getAllEdges(int[][] edges) {
		int count= 0 ;
		int[][] allEdges = new int[40][2] ;
		for(int i = 0 ; i <edges.length ; i++) {
			for(int j = 0 ; j <edges[i].length ;j++) {
				if(edges[i][j] != -1) {
					allEdges[count][0] = i;
					allEdges[count][1] = edges[i][j] ;
					count++ ;
				}
			}
		}
		
//		for(int i = 0 ; i < allEdges.length ;i++) {
//			for(int j = 0 ; j < allEdges[i].length ; j++) {
//				System.out.print(allEdges[i][j]+"  ");
//			}
//			System.out.println();
//		}
//		
		
		return allEdges ;
	}
	// 判断当前状态是否安全
	private static boolean isSafe(int state) {
		boolean flag = true;
		// 得到状态 农夫 狼 羊 白菜
		int[] stateArray = getEachState(state);
		
		//狼单独和羊在一起以及羊和白菜单独在一起的情况
		if ((stateArray[0] != stateArray[1] && stateArray[1] == stateArray[2])
				|| (stateArray[0] != stateArray[2] && stateArray[2] == stateArray[3])) {
			flag = false;
		}
		
		return flag;
	}

	// 得到每一种物品的状态 存入一个数组中返回
	public static int[] getEachState(int state) {
		// 每种物品的状态 依次为 农夫 狼 羊 白菜
		int[] stateArray = new int[4];
		
		for (int i = 3; i >= 0; i--) {
			stateArray[i] = state & 1; //相同位的两个数字都为1，则为1；若有一个不为1，则为0
			state = state >> 1; //右移一位
		}

		System.out.print("state array: ");
		for (int i = 0; i < stateArray.length; i++) {
			System.out.print(stateArray[i] + "\t");

		}
		System.out.println();
		return stateArray;
	}
	
	


}

