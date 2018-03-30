package sorted;

import java.util.Arrays;

public class SortTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] s = {4,5,6,8,7,10,11,12,13,14,15,21,22,23,24,25,26,27};
		long start = System.currentTimeMillis();
		fast(s,0,s.length-1);
		System.out.println(System.currentTimeMillis()-start);
		System.out.println(Arrays.toString(s));
		start = System.currentTimeMillis();
		bubbling3(s,0,s.length-1);
		System.out.println(System.currentTimeMillis()-start);
		System.out.println(Arrays.toString(s));

	}
	

	
	/**
	 * 
	 * @param s		需要排序的数组
	 * @param l		排序的左边界
	 * @param r		排序的右边界
	 * 快速排序的思想是:先从数组中选取一个元素作为key。然后在数组左右两边设置一个标记点。
	 * 然后右标记点开始往左查找找到小于key的值；左标记点开始往右查找找到大于key的值。然后两者交换。
	 * 直到两个标记点相遇，那么就确定了在标记点左边的数肯定比Key小，在右边的数肯定比key大。这就是key所在的实际位置
	 * 接着用递归的算法，分别再对key所在位置的左右两边的部分进行排序。
	 * 
	 * 与冒泡排序的区别。如果待排数组的顺序是大致正确的。只有较少部分的数据位置是错误的。比如：{4，5，6，8，7，10，11，12，13，14，15}这样的，
	 * 其实就是8与7的位置错了。如果是快速排序，还是需要一个一个确认位置。这样的话，效率其实并不高。
	 * 那么如果使用冒泡排序的话。在内层循环就可以快速的跳出。速度比快速排序要快。
	 */
	public static void fast(int[] s,int l,int r){
		if(l>=r)
			return;
		int key = s[l];	//关键数，一般取数组第一个数
		int i=l;		//从左边界开始查找的标记
		int j=r;		//从右边界开始查找的标记
		while(i<j){	
			while(i<j && s[j] >= key){	//j从右边界开始查找，直到找到小于key的值
				j--;
			}
			while(i<j && s[i] <= key){ //i从左边界开始查找，直到找到大于key的值
				i++;
			}
			if(i<j){		//如果i在j的左边那就交换
				s[i] = s[i]+s[j];
				s[j] = s[i]-s[j];
				s[i] = s[i]-s[j];
			}
		}
		
		//循环完成后，i与j应该相等，也就是key所确定的位置
		s[l]=s[i];	//先将左边的边界值换成i所在位置的值		因为通过了上面的循环可以确定出在i左边的一定小于key
		s[i]=key;	//然后将key插入确定的位置，也就是i的位置
		
		//第一躺排序完成。进行递归排序key左边和key右边的部分
		fast(s,l,i-1);
		fast(s,i+1,r);
	}
	
	/**
	 * 
	 * @param s		要排序的数组
	 * @param l		排序的左边界
	 * @param r		排序的右边界
	 * 
	 * 没有任何优化的冒泡排序。每次都需要比较，一趟确定一个元素的位置。
	 * 这样的话有个问题。比如说我们的数组前面的大部分元素是有序的。只有后面的元素是无序的。比如{1，2，3，5，8，7，6}
	 * 那么，当第一次循环完成以后。其实顺序就排好了。那么按照当前方法来进行的话，还是会继续循环下去。效率就不是很高。
	 * 现在有一个解决办法。就是设置一个标志位，在循环的时候如果有进行数据交换就设置为true,如果某一次循环没有进行交换的话。就设置为false。
	 * 这样的话。只要通过判断这个标志位是不是false，如果是false就说明已经排好序了。无序循环下去。直接结束。看bubbling2方法。
	 */
	public static void bubbling1(int[] s,int l,int r){
		if(l>=r){
			return;
		}
		for(int i=r;i>l;i--){
			for(int j=l;j<i;j++){
				if(s[j] > s[j+1]){
					s[j] = s[j]+s[j+1];
					s[j+1] = s[j] -s[j+1];
					s[j] = s[j] -s[j+1];
				}
			}
		}
	}
	

	/**
	 * 第一层优化，如果在某次循环时没有进行过一次交换说明顺序已经是正确的。那么可以直接结束循环
	 * @param s		要排序的数组
	 * @param l		排序的左边界
	 * @param r		排序的右边界
	 * 再来看看分析这个数组{1，2，3，5，7，8，6，9，10，11，12，13} 从9开始后面的数都比前面的数大，并且是有序的
	 * 如果按照当前的方法去排序的话。那么第一次排序以后的结果是 1，2，3，5，7，6，8，9，10，11，12，13
	 * 那么接下来再排序的时候还是会去比较9，10.。。。一直到最后。这样来看，其实9一直到后面其实都不需要再进行比较了。
	 * 实际上，在非第一次循环中（即第二趟，第三趟。。。。）只需要比较到上一趟循环最后交换数字的位置即可。后面的肯定是有序且比前面的数都大的。
	 * 那上面的数组来看。就是，在第一趟比较时，最后交换的位置是6所在的位置。即下标为6的位置。那么后面的循环比较就不用再比较下标6位置以后的数据了。
	 * 这样就能更加节约性能。具体方式看bubbling3
	 */
	public static void bubbling2(int[] s,int l,int r){
		if(l>=r){
			return;
		}
		boolean flag = true;	//设置标志位，初始的时候设置为true
		int k = r;				//标志排序的右边界
		while(flag){			//判断是否有过数据交换，如果没有就结束循环。
			flag = false;		//每一次循环开始时，先把标志位置为false，
			for(int i = l;i<k;i++){
				if(s[i] > s[i+1]){
					s[i] = s[i] + s[i+1];
					s[i+1] = s[i] - s[i+1];
					s[i] = s[i] - s[i+1];
					flag =true;		//有数据交换发生，设置标志位为true
				}
			}
			k--;		//一次循环完成后，将右边界向前移动一位
		}
	}
	
	/**
	 * 在循环的时候记录下最后一次交换数据的位置。那么下一趟循环就可以只比较到该位置就行了。
	 * @param s		要排序的数组
	 * @param l		排序的左边界
	 * @param r		排序的右边界
	 */
	public static void bubbling3(int[] s ,int l,int r){
		if(l >= r){
			return;
		}
		int k;	//作为定位排序的右边界
		int flag = r;	//定义为最后交换数据的位置
		while(flag>l){
			k = flag;	//每次排序开始时，k的值都根据flag来更新。
			flag = 0;	//flag复位为0，重新开始记录最后交换的位置。
			for(int i=l;i<k;i++){
				if(s[i] > s[i+1]){
					s[i] = s[i] + s[i+1];
					s[i+1] = s[i] - s[i+1];
					s[i] = s[i] - s[i+1];
					flag = i+1;
				}
			}
		}
	}

}
