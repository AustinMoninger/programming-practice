package TreePackage;

/**
 * Class for the nodes of a binary tree.
 */
class BinaryNode<T> {
    private T data;
    private BinaryNode<T> left;
    private BinaryNode<T> right;
    
    /**
     * New empty tree
     */
    public BinaryNode() {
        this(null); // call next constructor
    } 

    /**
     * New tree with data in the root
     * @param dataPortion Root data
     */
    public BinaryNode(T dataPortion) {
        this(dataPortion, null, null); // call next constructor
    } 

    /**
     * New tree with given root data and specified left and right subtrees.
     * @param dataPortion Data in root.
     * @param leftChild Left subtree
     * @param rightChild Right subtree
     */
    public BinaryNode(T dataPortion, BinaryNode<T> leftChild, BinaryNode<T> rightChild) {
        data = dataPortion;
        left = leftChild;
        right = rightChild;
    } 

    /**
     * Return data from root
     * @return Root data
     */
    public T getData() {
        return data;
    } 

    /**
     * Change root data
     * @param newData New data in root
     */
    public void setData(T newData) {
        data = newData;
    } 

    /**
     * Return left sub-tree
     * @return Left sub-tree
     */
    public BinaryNode<T> getLeftChild() {
        return left;
    } 

    /**
     * Change left sub-tree
     * @param leftChild Tree that replaces left tree
     */
    public void setLeftChild(BinaryNode<T> leftChild) {
        left = leftChild;
    } 

    /**
     * Whether there is a left sub-tree
     * @return True if the left tree is not null, false otherwise
     */
    public boolean hasLeftChild() {
        return left != null;
    } 

    /**
     * Whether node is a leaf, meaning it has no children
     * @return True if node is a leaf
     */
    public boolean isLeaf() {
        return (left == null) && (right == null);
    } 


    /**
     * Return left sub-tree
     * @return Left sub-tree
     */
    public BinaryNode<T> getRightChild() {
        return right;
    } 

    /**
     * Change right sub-tree
     * @param rightChild Tree that replaces right tree
     */
    public void setRightChild(BinaryNode<T> rightChild) {
        right = rightChild;
    } 

    /**
     * Whether there is a right sub-tree
     * @return True if the right tree is not null, false otherwise
     */
    public boolean hasRightChild() {
        return right != null;
    } 

    /**
     * Copies this node and its subtrees
     * @return Copy of this node
     */
    public BinaryNode<T> copy ()
    {
        BinaryNode<T> newRoot = new BinaryNode<T> (data);
        if(left != null)
            newRoot.left = (BinaryNode<T>) left.copy ();
        if(right != null)
            newRoot.right = (BinaryNode<T>) right.copy ();
        return newRoot;
    } 

    /**
     * Height of tree descending from this node.
     * This is a kick-off for the actual recursive method.
     * 
     * @return Height of tree from this node.
     */
    public int getHeight () {
        return getHeight(this); // call private getHeight
    } 

    /**
     * Recursive height calculation
     * @param node Starting node
     * @return Height from starting node downward.
     */
    private int getHeight (BinaryNode<T> node) {
        int height = 0;
        if(node != null)
            height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        return height;
    } 

    /**
     * Number of nodes in the tree for which this node is the root.
     * @return Number of nodes.
     */
    public int getNumberOfNodes() {
        int leftNumber = 0;
        int rightNumber = 0;
        if(left != null)
            leftNumber = left.getNumberOfNodes();
        if(right != null)
            rightNumber = right.getNumberOfNodes();
        return 1 + leftNumber + rightNumber;
    } 
    
} 