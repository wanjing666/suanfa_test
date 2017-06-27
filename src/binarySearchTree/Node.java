package binarySearchTree;

/**
 * 定义节点类
 * @author CP_WJ
 *
 */
public class Node {
	public int key;		//节点的键
	public Object value;//节点的值
	
	public Node leftChild;	//节点的左孩子
	public Node rightChild;	//节点的右孩子
	
	
	public Node(int key,Object value){
		this.key = key;
		this.value = value;
	}
}
