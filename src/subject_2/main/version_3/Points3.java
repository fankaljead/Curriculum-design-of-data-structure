
package subject_2.main.version_3;


public class Points3 {

	private double[] temp1 = new double[3];
	private double[] temp2 = new double[2];

	private double sum;

	
//	  cardArray : 52张牌中抽取的4张牌
	 
	private int[] cardArray = new int[4];

	
//	  operator : 运算符数组
	 
	private char[] operator = { '+', '-', '*', '/' };

	
//	 bcard : 4张纸牌代表的数字的数组
	 
	private double[] scard = new double[4];


	private boolean isCorrect = false;

	public static void main(String[] args) {

		Points3 nAnalyzePoker = new Points3();
		nAnalyzePoker.allResult();
	}

	public Points3() {

	}


	public static double calculate(double number1, double number2, char operator) {
		if (operator == '+') {
			return number1 + number2;
		} else if (operator == '-') {
			return number1 - number2;
		} else if (operator == '*') {
			return number1 * number2;
		} else if (operator == '/' && number2 != 0) {
			return number1 / number2;
		} else {
			return -1;
		}
	}

	public void search() {


		for (int i = 0; i < 4; i++) {


			for (int j = 0; j < 4; j++) {


				for (int k = 0; k < 4; k++) {

					// 首先计算的两个相邻数字，共有3种情况，相当于括号的作用
					for (int m = 0; m < 3; m++) {
//						if (scard[m + 1] == 0 && operator[i] == '/') {
//							break;
//						}

						temp1[m] = calculate(scard[m], scard[m + 1], operator[i]);
						temp1[(m + 1) % 3] = scard[(m + 2) % 4];
						temp1[(m + 2) % 3] = scard[(m + 3) % 4];

						// 先确定首先计算的两个数字，计算完成相当于剩下三个数，按顺序储存在temp1数组中
						// 三个数字选出先计算的两个相邻数字，两种情况，相当于第二个括号
						for (int n = 0; n < 2; n++) {
//							if (temp1[n + 1] == 0 && operator[j] == '/') {
//								break;
//							}

							temp2[n] = calculate(temp1[n], temp1[n + 1],
									operator[j]);
							temp2[(n + 1) % 2] = temp1[(n + 2) % 3];

							// 先确定首先计算的两个数字，计算完成相当于剩下两个数，按顺序储存在temp2数组中
							if (temp2[1] == 0 && operator[k] == '/') {
								break;
							}

							sum = calculate(temp2[0], temp2[1], operator[k]);

							if (sum == 24) {
								isCorrect = true;
								String expression = "";

								if (m == 0 && n == 0) {
									expression = "((" + (int) scard[0]
											+ operator[i] + (int) scard[1]
											+ ")" + operator[j]
											+ (int) scard[2] + ")"
											+ operator[k] + (int) scard[3]
											+ "=" + (int) sum;
								} else if (m == 0 && n == 1) {
									expression = "(" + (int) scard[0]
											+ operator[i] + (int) scard[1]
											+ ")" + operator[k] + "("
											+ (int) scard[2] + operator[j]
											+ (int) scard[3] + ")=" + (int) sum;
								} else if (m == 1 && n == 0) {
									expression = "(" + (int) scard[0]
											+ operator[j] + "("
											+ (int) scard[1] + operator[i]
											+ (int) scard[2] + "))"
											+ operator[k] + (int) scard[3]
											+ "=" + (int) sum;
								} else if (m == 2 && n == 0) {
									expression = "(" + (int) scard[0]
											+ operator[j] + (int) scard[1]
											+ ")" + operator[k] + "("
											+ (int) scard[2] + operator[i]
											+ (int) scard[3] + ")=" + (int) sum;
								} else if (m == 2 && n == 0) {
									expression = (int) scard[0] + operator[k]
											+ "(" + (int) scard[1]
											+ operator[j] + "("
											+ (int) scard[2] + operator[i]
											+ (int) scard[3] + "))="
											+ (int) sum;
								}

//								System.out.println(expression);//打印每种可能
							}
						}
					}
				}
			}
		}
	}

	public void allResult() {
        System.out.println("计算中······");
        int allCount = 0;//总次数
		int count = 0;//可以构成24点的次数
		for (int i = 1; i < 53; i++) {
			for (int j = i + 1; j < 53; j++) {
				for (int k = j + 1; k < 53; k++)
					for (int l = k + 1; l < 53; l++) {
						allCount++;
						if (isCorrectExpression(i, j, k, l)) {
							count++;
						}
					}
			}
		}
        System.out.println("计算完成");
		double rate = (double) count / allCount;
		System.out.println("所有可能的组合共有：" + allCount);
		System.out.println("结果为24的组合共有： " + count);
		System.out.println("成功的几率是:" + rate);
	}

	
//	 输入4个数判断其能产生多少个结果等于24的算式
	public boolean isCorrectExpression(int num1, int num2, int num3, int num4) {
		for (int a = 0; a < 4; a++)
			for (int b = 0; b < 4; b++) {
				if (b == a) {
					continue;
				}

				for (int c = 0; c < 4; c++) {
					if (c == a || c == b) {
						continue;
					}

					for (int d = 0; d < 4; d++) {
						if (d == a || d == b || d == c) {
							continue;
						}

						cardArray[a] = num1;
						cardArray[b] = num2;
						cardArray[c] = num3;
						cardArray[d] = num4;

						for (int m = 0; m < 4; m++) {
							scard[m] = (double) cardArray[m] % 13;
							if (cardArray[m] % 13 == 0) {
								scard[m] = 13;
							}
						}

						search();

						if (isCorrect) {
							isCorrect = false;
							return true;
						}
					}
				}
			}

		return false;
	}
}
