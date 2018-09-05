class Node(object):
    def __init__(self, val, next=None):
        self.val = val
        self.next = next

    def get_val(self):
        return self.val

    def set_val(self, new_value):
        self.val = new_value

    def get_next(self):
        return self.next

    def set_next(self, new_node):
        self.next = new_node

    def __str__(self):
        return str(self.val)


class LinkedList(object):
    def __init__(self, head=None):
        self.head = head

    def get_head(self):
        return self.head

    def set_head(self, new_head):
        self.head = new_head

    def insert_at_head(self, val):
        new_node = Node(val)
        new_node.set_next(head)
        head = new_node

    def insert_at_tail(self, val):
        node = self.get_head()
        while(node.get_next() != None):
            node = node.get_next()
        node.set_next(Node(val))

    def size(self):
        size = 0
        node = self.get_head()
        while node:
            size += 1
            node = node.get_next()
        return size

    def __str__(self):

        result = "["
        node = self.get_head()
        if node:
            result += str(node)
            node = node.get_next()
            while node:
                result += ", " + str(node)
                node = node.get_next()
        result += "]"
        return result




print 'hi'
li = LinkedList(Node(9))
li.insert_at_tail(1)
print li
print li.size()
