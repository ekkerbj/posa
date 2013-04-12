Week 6 Assignment 3
===================

The purpose of this assignment is to deepen your understanding of the Wrapper Facade, Reactor, and Acceptor-Connector patterns in the context of Java Netty. In particular, you will write a platform-independent reactive server program that accepts a connection from a client and echos back what the client sent. In brackets below are some hints about what kinds of Netty classes you might want to look up to do these (see the Netty Javadocs and examples for more background).

In addition to the Netty NIO Socket wrappers, the component that you should use for this assignment are the ServerBootstrap and ServerSocketChannelFactory. The other hints are simply for your convenience.

The reactive server program should do the following activities:

* Create an EchoServerHandler that inherits from SimpleChannelUpstreamHandler and implement its messageReceived() hook method so that it echos back to the client whatever the client sends it. You can additionally override methods such as channelOpen for example to show logging information.
* Create a ChannelPipelineFactory which sets up a pipeline for processing the inbound messages; the EchoServerHandler should be the only handler in the pipeline.
* Configure the ServerSocketChannelFactory that inherits from ServerChannelFactory, and binds the ServerBootstrap class to an appropriate InetSocketAddress.
* Configure the ServerBootstrap class with appropriate thread pools to run the events. Configure the bootstrap object with a PipelineFactory.
* When a client connection request arrives, the ServerChannelFactory and ChannelPipeline work together to pass events to the EchoServerHandler. Note that implementing SimpleChannelUpstreamHandler takes care of the connection acceptance.
* For this assignment, you can simply exit the server process (using Ctrl+C) when you're done, which will close down all the open sockets. 

Make sure your solution clearly indicates which classes play which roles in the Wrapper Facade, Reactor, and/or Acceptor-Connector patterns.

Please implement this program in Java using the the Netty library (which you can download from http://netty.io/) and contain code in a single file.  An evaluator should be able to compile it with a command such as "javac -cp netty.jar Program.java"  and execute it with 'java -cp .:netty.jar Program Server' (and 'java -cp .;netty.jar Program Server' for Windows) and your program should successfully run! Note that the netty's distribution jar ( which can be downloaded here http://netty.io/downloads.html ) is provided by the evaluator. 