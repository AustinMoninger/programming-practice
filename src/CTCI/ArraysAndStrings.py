
def is_unique(string):
    """
    Determines if a string has all unique chars.
    """
    chars_in_string = set()
    for char in string:
        prev_len = len(chars_in_string)
        chars_in_string.add(char)
        if prev_len == len(chars_in_string):
            return False
    return True

print is_unique('hello there')
print is_unique('Austin')


