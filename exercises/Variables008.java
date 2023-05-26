// Java also permits multi-variable declaration, which is when more than one variable is declared
// as part of the same instruction, or line. You can think of an instruction in Java as everything
// leading up to a curly brace ({) or semicolon (;).
//
// Since Java does not use line breaks to separate instructions, it is possible to put multiple
// instructions on the same line. In 99% of all cases, you should only have one instruction per
// line. Having multiple instructions on the same is against commonly held style conventions, of
// which Google Java Style Guidelines are the most popular. It hurts code readability and therefore
// maintainability, so you should adhere to the style guidelines in most cases especially if
// you're developing code that will be maintained or reviewed by someone else.
//
// With that said, everyone finds something in the style guidelines that they don't like. As long as
// your preferences are readable and your team agrees on them, you should be fine. For example, this
// Javalings repo doesn't follow the Google Java Style Guidelines for breaking a single long line
// across multiple lines. When a single line of code surpasses 100 characters, the guidelines
// recommend that the line be broken after commas or before operators and that each next line
// is twice indented or that params/args are lined up, if the long line is a method. For example:
//
//            MyReallyLongClassNameThatHasTooManyChars.aReallyLongMethodName(theFirstArg,
//                                                                           theSecondArg,
//                                                                           aReallyLongVariableName);
//
// In most cases, this approach doesn't work because class names, method names, and variable names
// tend to all be too long in Java, so lining up the parameters/args can lead to parameters/args
// still extending beyond the 100 character line length. As such, we opt for a different style:
//  
//            MyReallyLongClassNameThatHasTooManyChars
//                .aReallyLongMethodName(
//                  theFirstArg,
//                  theSecondArg,
//                  aReallyLongVariableName
//                );
//
// This allows the reader to quickly parse all of the information about their method just by
// scanning the left hand side. Even though this appears more readable (to the author), the choice
// to use this style still has to be weighed against the fact that it breaks convention, which can
// hurt readability by other authors and may not be easily supported by dev tools, such as linters.
//
// I mention all of this because it often comes as a surprise to new developers that even simple
// things like style and convention are import design decisions that have to be documented and
// strictly adhered to when developing a project. If you want to be a developer, you'll be expected
// to follow these as well on a per project basis.

// TODO: I AM NOT DONE

public class Variables008 {

  public static void main(String[] args) {
    int x; int y; // don't do this

    int u, v; // multi-line declaration is preferred
    u = 1, v = 1; // don't break this line
    
    ??? n = ???, m = ???; // you can assign as part of a multi-line declaration

    System.out.printf("m=%d, n=%d, u=%d, v=%d, x=%d, y=%d\n", m, n, u, v, x, y);
  }
}
