# RichTea standard library.

The core RichTea runtime is, by design, incredibly useless :)

It has almost no "out of the box" functionality - Limiting itself to providing the `Import` function.
`Import` loads other RichTea modules and makes them available to the program being executed.

Therefore "out of the box", it has no notion of the most basic programming constructs, such as:
- Variable declarations
- Loops (`For`, `While` etc)
- Branching (`If`, `Switch` etc)

The RichTea standard library module provides the missing functionality typically expected to be part of the core language.

# Example

```
Import("*" from:"modules/std")

Let(message:"I can create variables!")
Print("I can print messages and { message }") // "I can print messages and I can create variables!"

If(true {
  Print("...and branch...")
})
```
