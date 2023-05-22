// When Java code is compiled, it is compiled into a **class** file, which can be somewhat
// confusing at first. We should remember that every Java file must have a single outer
// class, which means that every Java program can be described by a set of Java classes, one for
// each file. As such, when a Java file is compiled, it produces a corresponding .class file, which
// is a description of the class defined in that file. These .class files also include instructions
// that can be executed by the JVM in much the same way that machine code would be executed by a
// CPU. This is why we use the term Virtual Machine when referring to the Java runtime, since it's
// virtual software that simulates the mechanics of the hardware in your computer, or machine.
//
// Since the JVM (and not your CPU) is parsing the instructions of your Java program, it is possible
// to write code that act like commands to the JVM itself. One such example is the statement
// `System.out.println()`, which looks like a Java method. However, this is a command to the JVM
// to log data to console.
//
// From the perspective of the OS, all running programs and applications are processes. Every
// process is given the stdin, stdout, and stderr data streams. We'll learn more about stdin and
// stderr later. For now, all we need to understand is that since the JVM is a process, it has a
// stdout data stream, so when the JVM executes Java code that includes `System.out.println`, that
// code is treated as an instruction to log data to the JVM's stdout data stream. Other processes
// can listen to this stdout data stream. That in turn is why you can see the printed data in
// a terminal or graphical window, which would allow you to observe what your program has computed.
//
// Java also includes other variations of printing. A less common variation is `print`. The
// difference between `print` and `println` is that `println` will log a line break after the data
// has been logged, which is a newline character ('\n') for Linux and Mac or a carriage-return
// line-feed ('\r\n') for Windows.
//
// Fix the following code so that it can perform a proper greeting and introduction.
// Execute `javalings hint Print004` or use the `hint` watch-subcommand for a hint.

// TODO: I AM NOT DONE

public class Print004 {

  public static void main(String[] args) {
    ???.???.???("Hello, World!"); // this should include a line break
    System.out.print("My name is ???\n");
  }
}
