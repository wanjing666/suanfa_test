package binarySearchTree;

/**
 * 定义一个二叉查找树实现类
 * @author CP_WJ
 * 本示例规定了该实现类不允许出现key重复的数据；因为如果key相同，则处理起来比较麻烦
 */
public class BinarySearchTree {
	public Node root;		//定义二叉查找树的根节点
	
	//通过key查找对应的值
	public Node get(int key){
		//首先得判断该树是否为空树
		if(root == null)
			System.out.println("this is empty!");
		//定义一个变量表示当前操作得节点
		Node current = root;
		/**
		 * 判断：当当前操作节点的key与待查找的key不同时，比较待查找的key与当前操作节点的key值；
		 * 若待查找的key大，则将操作节点设置成当前操作节点的右孩子。反之，则将操作节点设置成当前操作节点的左孩子
		 * 这里依据的是二叉查找树的一条特性：任意结点的左子树不为空，则左子树的所有结点值都小于或等于该结点；
		 * 任意结点的右子树不为空，则右子树的所有结点值都大于等于该结点
		*/
		while(current.key != key){
			if(key > current.key)
				current = current.rightChild;
			else
				current = current.leftChild;
			//如果当前操作结点为空，则返回空，说明没有找到该
			if(current == null)
				return null;
		}
		return current;
	}
	
	//插入一个键值对（结点）
	public void put(int key,Object value){
		//判断当前树是否为空树，如果为空树则将插入的结点设置成根结点
		if(root == null){
			root = new Node(key, value);
			return;
		}
		
		Node current = root;
		//当当前操作结点的key不等于待插入的key时；判断带插入的key与当前操作节点的key的大小；如果带插入的key大，则判断当前操作结点是否有右孩子，如果
		//没有，则插入新的结点
		while(current.key !=key){
			if(key > current.key){
				if(current.rightChild == null){
					current.rightChild = new Node(key, value);
					return;
				}
				current = current.rightChild;
			}else{
				if(current.leftChild == null){
					current.leftChild = new Node(key, value);
					return;
				}
				current = current.leftChild;
			}
		}
		//如果当前操作结点的值等于待插入的值，则用新的结点覆盖原来的结点
		current = new Node(key, value);
		return;
	}
	
	/**
	 * 遍历树
	 * 1.前序遍历：先根结点，然后再所有的左结点，最后再所有的右结点
	 * 2.中序遍历：先左结点，然后再根结点，最后再所有的右结点；通过遍历的次序可以看到用这种方式遍历会得到有序的结点序列
	 * 3.后序遍历：先左结点，然后再右结点，最后再根结点
	 * @param key
	 */
	
	//前序遍历
	public void preorder_iterator(Node node){
		//首先输出需要遍历的树或者子树的根结点
		System.out.println(node.key+":"+node.value.toString());
		//然后判断该结点是否有左孩子，如果有，则先遍历它的左孩子。以此类推
		if(node.leftChild !=null)
			preorder_iterator(node.leftChild);
		//最后判断该结点是否有右孩子，如果有，则先遍历它的右孩子。以此类推
		if(node.rightChild !=null)
			preorder_iterator(node.rightChild);
	}
	
	//中序遍历
	public void inorder_iterator(Node node){
		if(node.leftChild != null)
			inorder_iterator(node.leftChild);
		System.out.println(node.key+":"+node.value.toString());
		if(node.rightChild != null){
			inorder_iterator(node.rightChild);
		}
	}
	
	//后序遍历
	public void postorder_iterator(Node node){
		if(node.leftChild != null)
			postorder_iterator(node.leftChild);
		if(node.rightChild != null)
			postorder_iterator(node.rightChild);
		System.out.println(node.key+":"+node.value.toString());
	}
	
	//查找树/子树中key最小的结点
	public Node minNode(Node node){
		if(this.get(node.key) == null)
			return null;
		if(node.leftChild == null)
			return node;
		Node current = node.leftChild;
		while(current.leftChild != null)
			current = current.leftChild;
		return current;
	}
	
	//查找树/子树中key最大的结点
	public Node maxNode(Node node){
		if(this.get(node.key) == null)
			return null;
		if(node.rightChild == null)
			return node;
		Node current = node.rightChild;
		while(current.rightChild != null)
			current = current.rightChild;
		return current;
	}
	
	//根据key删除一个结点
	/**
	 * 这里删除一个结点有3种情况：
	 * 1：该结点是叶子结点		处理方式：直接删除；
	 * 2：该结点只有一个孩子（分两种：a.只有左孩子；b.只有右孩子） 处理方式：将它的孩子结点替换他的位置；
	 * 3: 该结点有两个孩子 首先在该结点的右子树上找到最小的结点替换该结点，然后将这个找到的最小结点删除即可
	 * @param key
	 * 
	 */
	public void delete(int key){
		if(root == null)
			return;
		return;
	}
}
