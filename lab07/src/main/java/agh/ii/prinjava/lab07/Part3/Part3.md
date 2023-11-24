# Part 3 : ``throw-catch`` and functional programming mismatch

## Problem of `throw-catch` and functional programming mismatch

Highlights the clash between exception handling, which introduces side effects, and functional programming's focus on pure, predictable functions. This mismatch leads functional preograming to favor alternative methods over exceptions. 

## Compare the way of handling checked and unchecked exceptions in Java stream pipelines (and lambda expressions)

In java streams and lambdas, checked exceptions are often wrapped in unchecked exceptions for ease of use, while unchecked exceptions are used as-is, simplifying the code but bypassing explicit error handling.

## Advantages of using Optional when compared to throwing exceptions or using the null

`Optional` in Java enhances code readability and safety handling absent values, avoiding `NullPointerExceptions` and offering a more stuctured amternative to `null` or exceptions.



