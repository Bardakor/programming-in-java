# Part 2 : Stream Methods (I)

- skip = Skips first 'n' elements
- Peek = Applies function without altering souce
- takeWhile = Takes elements until condition fails
- dropWhile = Drops elements until condition fails
- distinct = Removed duplicate elements
- sorted = Sorts Elements naturally or by comparator
- max = Finds maximum element
- min = Finds minimum element
- cout = Counts elements in stream
- findFirst = Returns first element (if present)
- findAny = Returns any element (if present)
- anyMatch = True if any element matches
- allMatch = True if all elements match
- noneMatch = True if no elements match

## Intermediate Operations 

- distinct = Removed duplicate elements
- limit: Truncates the stream to contain no more than a given number of elements.
- skip: Skips the first N elements of the stream.
- peek: Performs an action on each element of the stream without altering the stream.
- takeWhile: Takes elements while the predicate is true, and stops when it's false 
- dropWhile: Drops elements while the predicate is true, and starts streaming elements once it's false
- sorted: Sorts the elements of the stream.

## Terminal Operations

- count: Returns the count of elements in the stream.
- max: Finds the maximum element of the stream according to a given comparator.
- min: Finds the minimum element of the stream according to a given comparator.
- findFirst: Returns an Optional for the first element of the stream.
- findAny: Returns an Optional for any element of the stream (useful in parallel streams).
- allMatch: Checks if all elements match a given predicate.
- anyMatch: Checks if any element matches a given predicate.
- noneMatch: Checks if no elements match a given predicate.
- forEach: Performs an action for each element of the stream.

## Potential Applications of peek method

The `peek` method in Java's Stream API is used for debugging to inspect elements without altering them and for performing side-effects, like logging, as elements pass through the stream.

## Diff between skip and dropWhile

``skip`` in Java's Stream API skips the first N elements of the stream regardless of their values, while ``dropWhile`` discards elements as long as they match a specified condition and begins streaming once the condition is false.

## Rule of chaining method distinct and sorted

Chaining ``distinct`` before ``sorted`` can be more efficient, as it reduces the number of elements to sort. Conversely, ``sorted`` before ``distinct`` sorts all elements first, which can be less efficient due to a larger set, but ensures a stable sort order before eliminating duplicates.

