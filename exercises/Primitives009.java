// As mentioned in an earlier exercise, we have to include a variable's type as a part of its
// declaration so that the Java compiler knows how much memory is needed to store a value associated
// with that variable. But where exactly is that value stored? The vast majority of programming
// languages (Java included) store memory in one of two structures: the stack or the heap.
//
// The stack is a pre-allocated, fixed size structure. The current default size for the stack in
// Java is 512KB. If you exceed that limit, then you get a StackOverflowError, which you may
// recognize as the name of a well-known Q&A forum. When it comes to storing data, the stack is
// generally much faster than the heap, so from a performance perspective, if we have a choice
// between storing values on the stack or storing values on the heap, we'd prefer the stack.
//
// To store values on the stack, we have to satisfy a few conditions. The first condition is that
// the size of the value must be known at compile time. If I were to ask you to store 5 ints or
// 500 ints on the stack, it wouldn't matter because you know exactly how many there are and the
// size of each one. If I were to instead ask you to store a random number of ints on the stack,
// then you would be unable to do so because while you may know the size of each int, you don't how
// many there are. The same is true if you know how many items there are but don't know the size of
// each item. The second condition is that the value must be tied to the scope in which its
// declared. This is called its "lifetime". We'll discuss scoping rules and lifetimes in greater
// detail later. But it should suffice to say that values stored on the stack won't survive past
// the next curly brace (}).
//
// If either or both of these conditions cannot be met, then the value in question must be stored
// on the heap and not the stack. It takes longer to both store and access values stored on the heap
// but if the pre-requisite conditions to store values on the stack cannot be met, then the heap is
// where they must go. We'll discuss more about the heap later. In this exercise, we're focusing on
// stack values.
//
// In Java, values that can be stored on the stack are called "primitives" because they each use a
// well-defined binary encoding for their values. The primitive values available in Java are as
// follows:
//   - boolean
//     - 1-bit
//     - unsigned (non-negative)
//     - true (1) or false (0)
//   - byte
//     - 1-byte
//     - unsigned (non-negative)
//     - whole number
//     - 0..=(2^8 - 1)
//     - 0 <= n < 256
//     - 1000_1000 = 136 (binary)
//     - 0x88 = 136 (hex)
//   - short
//     - 2-bytes
//     - signed (negative, zero, or positive)
//     - whole number
//     - (-2^15)..=(2^15 - 1)
//     - 32,768 <= n < 32,768
//     - 1000_1000_1000_1000 = -30_584 (binary)
//     - 0x8888 = -30_584 (hex)
//   - int
//     - 4-bytes
//     - signed (negative, zero, or positive)
//     - whole number
//     - (-2^31)..=(2^31 - 1)
//     - 2.147e+9 <= x < 2.147e+9
//     - 1000_1000_1000_1000_1000_1000_1000_1000 = -2_004_318_072 (binary)
//     - 0x88888888 = -2_004_318_072 (hex)
//   - long
//     - 8-bytes
//     - signed (negative, zero, or positive)
//     - (-2^63)..=(2^63 - 1)
//     - 9.223e+18 <= x < 9.223e+18
//     - 1000_1000_1000_1000_1000_1000_1000_1000_1000_1000_1000_1000_1000_1000_1000_1000 =
//       -8_608_480_567_731_124_088 (binary)
//     - 0x8888888888888888 = -8_608_480_567_731_124_088 (hex)
//   - float
//     - 4-bytes
//     - signed
//     - IEEE single-precision floating point value (scientific decimal number)
//     - decimal number with 6 to 7 digits of precision
//     - x = (-1)^s * 2^(e - 127) * 1.m, with 1 bit for the sign (s), 8 bits for the exponent (e),
//       and 23 bits for mantissa (m), which represents the fractional component
//     - 1/1000_1000/000_1000_1000_1000_1000_1000 = -5.4613330078125e+2 (binary)
//     - 0xc4088888 = -5.4613330078125e+2 (hex)
//   - double
//     - 8-bytes
//     - signed
//     - IEEE double-precision floating point value (scientific decimal number)
//     - decimal number with 15 to 16 digits of precision
//     - x = (-1)^s * 2^(e - 127) * 1.m, with 1 bit for the sign (s), 11 bits for the exponent (e),
//       and 52 bits for mantissa (m), which represents the fractional component
//     - 1/100_1000_1000/1000_1000_1000_1000_1000_1000_1000_1000_1000_1000_1000_1000_1000 =
//       -2.67144343524064735750645970385e+41 (binary)
//     - 0xc888888888888888 = -2.67144343524064735750645970385e+41 (hex)
//   - char
//     - 2-bytes
//     - unsigned
//     - UTF-16 encoding
//     - a character point value for representing text
//     - 0000_0000_0101_1010 = 'Z' (binary)
//     - 0x005a = 'Z' (hex)
//   - ref
//     - 4- or 8-bytes
//     - unsigned
//     - memory address
//     - location of values in memory using 4- or 8-byte boundaries

public class Primitives009 {

  public static void main(String[] args) { 
    ??? v0 = true;
    ??? v1 = 512;
    ??? v2 = 123.1;
    ??? v3 = 100_000;
    ??? v4 = 'A';
    ??? v5 = 300_000_000_000;
    ??? v6 = 3.141592265358979;
    ??? v7 = 128;
    
    // %b is the default formatter for boolean values
    // %f is the default formatter for float values
    // %c is the default formatter for characters
    System.out.printf(
          "v0=%b, v1=%d, v2=%f, v3=%d, v4=%c, v5=%d, v6=%f, v7=%d\n",
          v0, v1, v2, v3, v4, v5, v6, v7
        );
  }
}
