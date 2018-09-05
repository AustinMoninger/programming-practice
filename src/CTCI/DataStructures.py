class Node(object):
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


class LinkedList(object):
    def __init__(self, head=None):
        self.head = head

    def get_head(self):
        return self.head

    def set_head(self, new_head):
        self.head = new_head

    def insert_at_head(self, val):
        if not self.head:
            self.head = Node(val)
            return

        new_node = Node(val)
        new_node.set_next(head)
        head = new_node

    def insert_at_tail(self, val):
        if not self.head:
            self.head = Node(val)
            return

        node = self.head
        while(node.get_next() != None):
            node = node.get_next()
        node.next = Node(val)

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




# print 'hi'
li = LinkedList()
li.insert_at_tail(1)
li.insert_at_tail(10)
li.insert_at_tail(5)
# print li
# print li.size()
# print li.search(5)
# print li.search(0)
#
# node = Node(9)
# print node.next
print li

li.delete_value(10)

print li
li.insert_at_tail(7)
li.insert_at_tail(67)

li.insert_at_tail(8)
print li

li.delete_value(8)
print li
li.delete_value(1)
print li
