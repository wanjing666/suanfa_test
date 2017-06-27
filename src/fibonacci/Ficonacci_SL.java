package fibonacci;

/**
 * 斐波拉契数列
 * @author CP_WJ
 *
 */
public class Ficonacci_SL {
	//递归方式
	public static int ficonacci1(int n){
		if(n <= 2)
			return 1;
		return ficonacci1(n-1)+ficonacci1(n-2);
	}
	
	//递推方式
	public static long ficonacci2(int n){
		long x = 1;
		long y = 1;
		long s = 0;
		if (n <= 2)
			return 1;
		for(int i=3;i<=n;i++){
			s = x + y;
			x = y;
			y = s;
		}
		return s;
	}
	
	public static void main(String[] args) {
		//System.out.println(ficonacci1(10));	//1分钟都执行不完，我怕死机，就直接断开了。。。。
		
		System.out.println(ficonacci2(100));	//不到1S
	}
}
