package singletons;

public class SingletonDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static SingletonDemo instance;
	
	private SingletonDemo(){
		
	}
	
	public static SingletonDemo getInstance(){
		
		if(null == instance){
			synchronized(SingletonDemo.class){
				if(null == instance)
					instance = new SingletonDemo();
			}
		}
		
		return instance;
	}

}
