package bTree;

public class BinarySearchTree <T extends KeyedItem<KT>, 
							   KT extends Comparable<? super KT>>
									extends BinaryTreeBasis<T>{
	
	
	public BinarySearchTree() {
	}
	
	public BinarySearchTree(T rootItem){
		super(rootItem);
	}
	
	public void setRootItem(T newItem)
					throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	public void insert(T newItem){
		root = insertItem(root, newItem);
	}

	public T retrive(KT searchKey){
		return retrieveItem(root, searchKey);
	}
	
	public void delete(KT searchKey) throws TreeException {
		root = deleteItem(root, searchKey);
	}
	
	public void delete(T item) throws TreeException {
		root = deleteItem(root, item.getKey());
	}
		
	protected T retrieveItem(TreeNode<T> tNode,
							 KT searchKey){
		T treeItem;
		if (tNode == null)
		{
			treeItem = null;
		}
		else
		{
			T nodeItem = tNode.item;
			if(searchKey.compareTo(nodeItem.getKey()) == 0)
			{
				treeItem = tNode.item;
			}
			else if(searchKey.compareTo(nodeItem.getKey()) < 0)
			{
				treeItem = retrieveItem(tNode.leftChild, searchKey);
			}
			else
			{
				treeItem = retrieveItem(tNode.rightChild, searchKey);
			}
		}
		return treeItem;
	}
	
	protected T findLeftmost(TreeNode<T> tNode){
		if(tNode.leftChild == null)
		{
			return tNode.item;
		}
		else
		{
			return findLeftmost(tNode.leftChild);
		}
	}
	
	protected T findRightmost(TreeNode<T> tNode){
		if(tNode.rightChild == null)
		{
			return tNode.item;
		}
		else
		{
			return findLeftmost(tNode.rightChild);
		}
	}
	
	protected TreeNode<T> insertItem(TreeNode<T> tNode,
										T newItem) {
		TreeNode<T> newSubtree;
		
		if (tNode == null) 
		{
			tNode = new TreeNode<T>(newItem, null, null);
			return tNode;
		}
		
		T nodeItem = tNode.item;
		
		if(newItem.getKey().compareTo(nodeItem.getKey()) < 0) 
		{
			newSubtree = insertItem(tNode.leftChild, newItem);
			tNode.leftChild = newSubtree;
			return tNode;
		}
		else 
		{
			newSubtree = insertItem(tNode.rightChild, newItem);
			tNode.rightChild = newSubtree;
			return tNode;
		}
	}
	
	protected TreeNode<T> deleteItem(TreeNode<T> tNode,
									 KT searchKey){
		TreeNode<T> newSubtree;
		
		if(tNode == null)
		{
			throw new TreeException("TreeException: Item not found");
		}
		else
		{
			T nodeItem = tNode.item;
			if(searchKey.compareTo(nodeItem.getKey()) == 0)
			{
				tNode = deleteNode(tNode);
			}
			else if(searchKey.compareTo(nodeItem.getKey()) < 0)
			{
				newSubtree = deleteItem(tNode.leftChild, searchKey);
				tNode.leftChild = newSubtree;
			}
			else
			{
				newSubtree = deleteItem(tNode.rightChild, searchKey);
				tNode.rightChild = newSubtree;
			}
		}
		return tNode;
	}
	
	protected TreeNode<T> deleteNode(TreeNode<T> tNode) {
		
		T replacementItem;
		
		if((tNode.leftChild == null) && (tNode.rightChild == null))
		{
			return null;
		}
		else if (tNode.leftChild == null)
		{
			return tNode.rightChild;
		}
		else if (tNode.rightChild == null)
		{
			return tNode.leftChild;
		}
		else
		{
			replacementItem = findLeftmost(tNode.rightChild);
			tNode.item = replacementItem;
			tNode.rightChild = deleteLeftmost(tNode.rightChild);
			return tNode;
		}
	}
	
	protected TreeNode<T> deleteLeftmost(TreeNode<T> tNode) {
		if(tNode.leftChild == null)
		{
			return tNode.rightChild;
		}
		else {
			tNode.leftChild = deleteLeftmost(tNode.leftChild);
			return tNode;
		}
	}
	
	/**
	 * display() method prints BSTree
	 * by calling printTree method.
	 */
	public void display() {
		int indent = 5; //indent
		printTree(root, indent);
	}
	
	/**
	 * Recursive method printTree to print a BSTree 
	 * horizontally.
	 * 
	 * Precondition: checks if root not equal to null.
	 * If not:
	 * 		-prints right subtree,increasing indentation 
	 * 		by one level
	 * 		-prints contents of root by calling formatPrint
	 * 		method
	 * 		-prints left subtree,increasing indentation 
	 * 		by one level
	 * @param root is a reference to a root node of
     * BSTree
	 * @param indent
	 */
	protected void printTree(TreeNode<T> root, int indent) 	
	{
		if (root != null)
		{	
			printTree(root.leftChild, indent + 1);
			formatPrint(root.item, indent);
			printTree(root.rightChild, indent + 1); 
		}
	}
	
	/**
	 * formatPrint method formats output in
	 * the same way as represented in a sample sheet.
	 * For each object element adds three white spaces,
	 * depends on a number of "indent" variable.
	 * 
	 * @param obj is a root node represented as 
	 * 			an object.
	 * @param indent 
	 */
	private void formatPrint(Object obj, int indent) {
		
		for (int i = 0; i < indent; i++) 
		{
			System.out.print("   ");
		}
		System.out.println(obj);
	}
}
