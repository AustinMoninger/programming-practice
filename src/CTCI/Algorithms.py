"""
Binary search
"""
def binary_search(target, arr):
    lo = 0
    hi = len(arr) - 1
    while lo <= hi:
        mid = lo + (hi - lo) / 2
        if arr[mid] == target:
            return True
        elif arr[mid] < target:
            lo = mid + 1
        else:
            hi = mid - 1
    return False

# print binary_search(5, [1, 5, 8, 13, 14])


"""
Merge sort
"""
def mergesort(arr):
    if len(arr) <= 1:
        return arr
    result = []
    mid = len(arr) / 2
    left = mergesort(arr[:mid])
    right = mergesort(arr[mid:])

    while len(left) > 0 and len(right) > 0:
        if left[0] > right[0]:
            result.append(right[0])
            right.pop(0)
        else:
            result.append(left[0])
            left.pop(0)
    return result + left + right


# arr = [10, 9, 21, 1, 2, 5]
# print mergesort(arr)

"""
Graph BFS
"""
graph = {'A': ['B', 'C', 'E'],
         'B': ['A','D', 'E'],
         'C': ['A', 'F', 'G'],
         'D': ['B'],
         'E': ['A', 'B','D'],
         'F': ['C'],
         'G': ['C']}

def bfs(graph, start):
    visited = []
    queue = [start]

    while queue:
        node = queue.pop(0)
        if node not in visited:
            visited.append(node)

            for neighbor in graph[node]:
                queue.append(neighbor)
    return visited

# print bfs(graph, 'A')

def dfs(graph, start):
    pass




