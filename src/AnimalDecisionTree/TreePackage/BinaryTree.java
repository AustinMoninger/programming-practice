package TreePackage;

/**
 * A class that implements a binary tree.
 */
public class BinaryTree<T> implements BinaryTreeInterface<T> {
    private BinaryNode<T> root;
    
    /**
     * New empty tree
     */
    public BinaryTree() {
        root = null;
    } 

    /**
     * New tree with data in root
     * @param rootData Data for root
     */
    public BinaryTree(T rootData) {
        root = new BinaryNode<T>(rootData);
    } 

    /**
     * New tree with specified root data and subtrees
     * @param rootData Data for root
     * @param leftTree Left subtree
     * @param rightTree Right subtree
     */
    public BinaryTree (T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
        privateSetTree(rootData, leftTree, rightTree);
    } 

    /**
     * Replace tree with new tree consisting only of a root with given data.
     */
    public void setTree (T rootData) {
        root = new BinaryNode<T>(rootData);
    } 

    /**
     * Replace tree with new tree consisting of root data and subtrees
     */
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
        privateSetTree(rootData, (BinaryTree<T>) leftTree, (BinaryTree<T>) rightTree);
    } 

    /**
     * Replace this tree with a new one using the given root data and subtrees, but
     * make copies of subtrees if they are the same. Also destroy the source trees
     * so that no lingering references to the internal nodes of this tree exist.
     * 
     * @param rootData New root data
     * @param leftTree Left subtree
     * @param rightTree Right subtree
     */
    private void privateSetTree (T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
    	root = new BinaryNode<T>(rootData);
    	if(leftTree != null && !leftTree.isEmpty())
    		root.setLeftChild(leftTree.root);
    	if(rightTree != null && !rightTree.isEmpty()) {
    		if(rightTree != leftTree)
    			root.setRightChild(rightTree.root);
    		else
    			root.setRightChild(rightTree.root.copy());
    	}
    	if(leftTree != null && leftTree != this)
    		leftTree.clear();
    	if(rightTree != null && rightTree != this)
    		rightTree.clear();
    } 

    /**
     * Return data stored in root
     */
    public T getRootData() {
        T rootData = null;
        if(root != null)
            rootData = root.getData();
        return rootData;
    } 

    /**
     * Indicate whether tree is empty
     */
    public boolean isEmpty() {
        return root == null;
    } 

    /**
     * Empty the tree by setting root to null.
     */
    public void clear() {
        root = null;
    } 

    /**
     * Change root data
     * @param rootData New data
     */
    protected void setRootData(T rootData) {
        root.setData(rootData);
    } 

    /**
     * Replace root node
     * @param rootNode New node
     */
    protected void setRootNode(BinaryNode<T> rootNode) {
        root = rootNode;
    } 

    /**
     * Return node that is the root.
     * @return Root node
     */
    protected BinaryNode<T> getRootNode() {
        return root;
    } 

    /**
     * Return height of tree
     */
    public int getHeight() {
        return root.getHeight();
    } 

    /**
     * Return number of nodes in tree
     */
    public int getNumberOfNodes() {
        return root.getNumberOfNodes();
    } 
    
} 