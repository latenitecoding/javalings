// Java supports the declaration and assignment of variables. Variable declaration is when a
// variable is named and typed but not assigned a value (e.g., int x). In these cases, we can
// say that the variable was declared but not "defined", since it's ambiguous what the value of the
// variable should be. This notion that variables can be defined comes from formal mathematics
// (where variables work differently) and isn't often discussed in programming languages other than
// functional programming languages, such as Clojure, Scala, OCaml, Lisp, Lean, Haskell, or Erlang.
//
// That aside, you should be aware of the differences between declaring variables and assigning
// them values. A variable is "declared" when it's given a name and type for the first time within
// the current scope. A scope is an abstraction that compilers use to keep track of what classes,
// methods, variables, etc. are available so that it can generate the proper instructions for using
// them and report errors to us when we're using them incorrectly or ambiguously. In Java, every
// file has its own scope and new scopes are declared using a set of matching curly braces {}. So,
// in some sense, you can think of this file as having the following scoping rules:
//
//      file Variables007.java { class Variables007: { method main: { String[] args } } }
//
// We're not going to dive deeper into scoping rules at this moment, but we will later. They're
// something that you should strive to master though. Scoping rules are an essential element of
// every programming language, and if you're going to be a developer, you should take the time to
// properly understand and master your tools.
//
// For now, you can think of declaring a variable like reserving a table at a restaurant. When you
// make the reservation, you also state how many people are coming so that the restaurant can
// reserve a table of the right size. This is like declaring a variable name (the reservation) and
// its type (the table size). You're letting the compiler know that it needs to reserve a certain
// amount of memory for your variable. There are some other similarities in this analogy that are
// helpful as well. For example, when you reserve a table, you're probably not planning to use the
// reservation right away. Similarly, declared variables don't always have a value. This is like
// saying that the restaurant knows how many people will be sitting at the table (the memory size),
// but doesn't know who all will be sitting down (the value). However, even if you have a
// reservation, other people can still use the table before and after you. When programming, you
// can't assume that just because you've declared a variable, that its associated memory has and
// will always be for that variable. All that you are assured is that the memory is yours when you
// need it. We also prefer to sit down at a clean table. Other people might have left messes behind.
// That happens with memory too. Using memory before you've assigned it a value is referred to as
// "undefined behavior". Once we've assigned a value to a variable, the memory is consider to be 
// initialized. If we attempt to use the variable before it's been assigned a value, Java will refer
// to the variable as being uninitialized and will break.
//
// Which brings us to assigning values. Variables can be assigned a value using the assignment
// operator (=). Variables can be assigned when they're declared or after (late assignment). The
// value assigned to the variable must type match the type of the variable. You can't assign a
// string to a number or vice versa. Once a variable has been assigned a value, you can use the
// variable without the variable being marked uninitialized. Getting past compiler errors doesn't
// mean that you're code is good though. You can still have logic bugs in the ways that you both
// assign values to variables and use them afterwards.

// TODO: I AM NOT DONE

public class Variables007 {

  public static void main(String[] args) {
    int x; // declares the variable x of type int
    x = 10; // late assignment

    int y = 5; // declared and defined simultaneously

    float x;

    short v;

    z = v;
    long z;

    // %d is the default format for an integer type
    System.out.printf("v=%d, w=%d, x=%d, y=%d, z=%d\n", v, w, x, y, z); // don't change this line
  }
}
