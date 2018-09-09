"""
LinkedList stuff
"""

class LinkedNode:
    def __init__(self, val, next=None):
        self.val = val
        self.next = next

    def get_val(self):
        return self.val

    def set_val(self, val):
        self.val = val

    def get_next(self):
        return self.next

    def set_next(self, next):
        self.next = next

    def __str__(self):
        return str(self.val)


class LinkedList:
    def __init__(self, head=None):
        self.head = head

    def get_head(self):
        return self.head

    def set_head(self, new_head):
        self.head = new_head

    def insert_at_head(self, val):
        if not self.head:
            self.head = LinkedNode(val)
            return

        new_node = LinkedNode(val)
        new_node.set_next(head)
        head = new_node

    def insert_at_tail(self, val):
        if not self.head:
            self.head = LinkedNode(val)
            return

        node = self.head
        while(node.get_next() != None):
            node = node.get_next()
        node.next = LinkedNode(val)

    def size(self):
        size = 0
        node = self.head
        while node:
            size += 1
            node = node.get_next()
        return size

    def search(self, val):
        node = self.head
        while node:
            if node.get_val() == val:
                return True
            node = node.get_next()
        return False

    def delete_value(self, val):
        if self.head.get_val() == val:
            self.head = self.head.get_next()
            return
        node = self.head
        while node:
            if node.get_next().get_val() == val:
                if not node.get_next().get_next():
                    node.set_next(None)
                else:
                    node.set_next(node.get_next().get_next())
                return
            node = node.get_next()

    def __str__(self):
        result = "["
        node = self.head
        if node:
            result += str(node)
            node = node.get_next()
            while node:
                result += ", " + str(node)
                node = node.get_next()
        result += "]"
        return result


"""
Queue stuff
"""
class Queue:
    def __init__(self):
        self.items = []

    def is_empty(self):
        return self.items == []

    def enqueue(self, data):
        self.items.append(data)

    def dequeue(self):
        return self.items.pop(0)

    def size(self):
        return len(self.items)

    def __str__(self):
        return str(self.items)

# s = Queue()
# s.enqueue(1)
# s.enqueue(3)
# s.enqueue(10)
# print s
# s.dequeue()
# print s


"""
Tree stuff
"""
class TreeNode:
    def __init__(self, data, left=None, right=None):
        self.data = data
        self.left = left
        self.right = right

    def preorder(self):
        print self.data
        if self.left:
            self.left.preorder()
        if self.right:
            self.right.preorder()

    def inorder(self):
        if self.left:
            self.left.inorder()
        print self.data
        if self.right:
            self.right.inorder()

    def postorder(self):
        if self.left:
            self.left.postorder()
        if self.right:
            self.right.postorder()
        print self.data

    def levelorder(self):
        q = Queue()
        if not self:
            return
        q.enqueue(self)
        while not q.is_empty():
            node = q.dequeue()
            print str(node) + ' '
            if node.left:
                q.enqueue(node.left)
            if node.right:
                q.enqueue(node.right)

    def __str__(self):
        return str(self.data)

# t = TreeNode(1)
# t.left = TreeNode(2)
# t.right = TreeNode(3)
# t.left.left = TreeNode(4)
# t.left.right = TreeNode(5)
# print t.levelorder()


"""
Graph stuff
"""
def get_edges(graph):
    edge_list = []
    for node in graph:
        for other_node in graph[node]:
            edge_list.append((node, other_node))
    return edge_list

def get_isolated_nodes(graph):
    isolated_list = []
    for node in graph:
        if not graph[node]:
            isolated_list.append(node)
    return isolated_list

# graph = {1: [2, 3],
#          2: [1],
#          3: [1, 4],
#          4: [3],
#          5: []}



"""
Stack stuff
"""
class Stack:
    def __init__(self):
        self.items = []

    def is_empty(self):
        return self.items == []

    def push(self, data):
        self.items.append(data)

    def pop(self):
        return self.items.remove(self.items[len(self.items) - 1])

    def size(self):
        return len(self.items)

    def __str__(self):
        return str(self.items)


# s = Stack()
# s.push(1)
# s.push(3)
# s.push(10)
# print s
# s.pop()
# print s

