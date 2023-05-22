// In Java, `println` and 'print` aren't the only variations of printing. Another very
// common variation of printing is `printf`, or print formatted. In fact, you should probably
// be using this variation of printing as much, if not more, than `print` and even `println`.
//
// There are a few notable differences between `printf`, `print`, and `println`. First off, like
// `print`, `printf` also doesn't include a line break, so you'll have to manually add one
// yourself. A line break uses the special character '\n', which is the "escape sequence" (/)
// followed by the letter 'n' signifying newline. Many languages include the / escape
// sequence to support special characters, such as line breaks and tabs ('\t'). These sequences
// aren't printed literally and are instead printed as the special character they represent.
// Second, unlike `print` and `println`, `printf` uses format strings. Format strings are
// strings (ordered sequences of characters, letters, numbers, symbols, etc.) that contain special
// characters that can be used to format data. These will be discussed below. This brings
// us to our last point, which is that `printf` can only print format strings, whereas `print`
// and `println` can print other kinds of data too, such as numbers.
//
// Despite `printf` only handling format strings, it is preferred for printing. This is in part
// due to our preference for formatted (or pretty) printing and also in part due to the terrible
// way in which Java handles combining strings, which has become so well known that repeating the
// mistakes in this exercise will distinguish you as a "rookie" programmer, or, in less kind terms,
// a total noob. In many ways, it is exercises like this that show Java's value as an instructive
// programming language, since it provides so many opportunities to learn what not to do.
//
// `printf` is a great tool to make use of, but it can also be very confusing the first or second
// time around. Thankfully, what makes it confusing is its use of special formatting characters,
// which happen to be universally accepted due to them originally being defined in C, the
// grandfather of (nearly) all iterative programming languages. So what you learn here will
// translate to other programming languages as well.
//
// All special formatting characters start with a % followed by the formatting rules, which are
// composed of letters, numbers, and symbols (such as the '.'). We'll be learning about
// different compositions of formatting characters over several exercises. The special
// formatting character we'll use in this exercise is %s which is the default string format. This
// term %s does nothing other than replace %s with the content of the string being formatted.
// A KEY takeaway from this is that formatting terms that start with %, like %s, can also be
// thought of as variable terms in a string that can be replaced with any appropriate value of
// your choosing.
//
// Fix the errors below to print a greeting using printf
// Execute `javalings hint Print005` or use the `hint` watch-subcommand for a hint.

// TODO: I AM NOT DONE

public class Print005 {

  public static void main(String[] args) {
    System.out.println("Hello" + ", " + "World" + "!"); // this is BAD
    // string concatenation ("abc" + "def" => "abcdef") is very bad in Java
    // Since a Java String is immutable (cannot be changed once defined),
    // Java only has one way to take "Hello" + ", " + "World" + "!"
    // and produce "Hello, World!"
    // It must define intermediate Strings that are created and then thrown away
    // "Hello" + ", " => "Hello, "
    // "Hello, " + "World" => "Hello, World"
    // "Hello, World" + "!" => "Hello, World!"
    // So to produce "Hello, World!" via string concatenation,
    // Java has to use the word "Hello" four times:
    //     - Once in the original string
    //     - Twice in intermediate strings
    //     - Once in the final string
    // You can use a rough approximation of stating that string concatenation
    // in Java requires the number of characters in the final string multiplied
    // by the number of concatenations; which is terrible
    // If you've been doing this in Java, you need to STOP
    // string concatenation is fine in most other languages, but you should always double
    // check how it works in that language just to be sure

    System.out.printf("%s, %s!", ???, ???); // this is fine
    // `printf` (or print format) provides an alternative
    // rather than using string concatenation to build "Hello, World!",
    // Java will instead create a mutable string called a StringBuilder
    // to incrementally build the final string by replacing format characters (%s)
    // with their argument substitution; e.g., "Hello" or "World"
    // This means that the final String can be built without any intermediate strings
    // If you ever find yourself needing to use string concatenation, please use
    // `printf` or `String.format` instead
    // Just don't forget to add a line break ('\n') to the end of the string
  }
}
