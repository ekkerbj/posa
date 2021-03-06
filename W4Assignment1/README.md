Week 4 Assignment 1
===================

You are to design a simple Java program where you create two threads, Ping and Pong, to alternately display “Ping” and “Pong” respectively on the console.  The program should create output that looks like this:

    Ready… Set… Go!
    
    Ping!
    Pong!
    Ping!
    Pong!
    Ping!
    Pong!
    Done!

It is up to you to determine how to ensure that the two threads alternate printing on the console, and how to ensure that the main thread waits until they are finished to print: “Done!”  The order does not matter (it could start with "Ping!" or "Pong!").

Consider using any of the following concepts discussed in the videos:

* wait() and notify()
* Semaphores
* Mutexes
* Locks

Please design this program in Java without using extra frameworks or libraries (you may use java.util.concurrent) and contain code in a single file.  Someone should be able to run something like “javac Program.java” and “java Program” and your program should successfully run!
