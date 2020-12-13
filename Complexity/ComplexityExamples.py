#==============================================================================
# Complexity
#==============================================================================

def findMax(numArr, n):
    """Finds the maximum number in the input array."""
    maxValue = numArr[0]            # 2 ops       (assign and access)
    i = 1                           # 1 op        (assign)
    while i < n - 1:                # 2 ops * n   (compare and arithmetic repeated n times)
        if maxValue < numArr[i]:    # 2 ops * n-1 (compare and access repeated for n-1 times)
            maxValue = numArr[i]    # 2 ops * n-1 (assign and access repeated for n-1 times)
        i = i + 1                   # 2 ops * n-1 (assign and arithmetic repeated n-1 times)
    return maxValue                 # 1 op        (return)

numArr = [1, 2, 3]
print(findMax(numArr))