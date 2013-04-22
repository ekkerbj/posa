import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.ServerSocketChannelFactory;
import org.jboss.netty.channel.socket.oio.OioServerSocketChannelFactory;

public class Program {

    private static final class HandlersFactory implements ChannelPipelineFactory {
        public ChannelPipeline getPipeline() throws Exception {
            ChannelPipeline p = Channels.pipeline();
            p.addLast("echo", new EchoServerHandlerAndAcceptor());
            return p;
        }
    };

    private static final class EchoServerHandlerAndAcceptor extends SimpleChannelUpstreamHandler {
        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            e.getChannel().write(e.getMessage());
            ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
            System.out.println(String.format("Recieved %s", buffer.toString(Charset.defaultCharset())));
        }

        @Override
        public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            System.out.println("Channel opened");
            super.channelOpen(ctx, e);
        }

        @Override
        public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            System.out.println("Channel closed");
            super.channelClosed(ctx, e);
        }
    }

    int port = 8081;
    
    public Program(int port)
    {
        this.port = port;
    }
    
    public void run()
    {
        // The Netty NIO library and the java library used to access sockets are the Facade pattern.
        
        // Create the channel factory, acting as part of the acceptor from acceptor/connector.       
        ServerSocketChannelFactory acceptorFactory = new OioServerSocketChannelFactory();
        ServerBootstrap server = new ServerBootstrap(acceptorFactory);
        
        //The pipelines string together handlers, we set a factory to create pipelines( handlers ) to handle events.
        //For Netty, the handler acts partially in the acceptor role as well with channel setup.
        server.setPipelineFactory(new HandlersFactory());
 
        server.bind(new InetSocketAddress(port));        
    }
    
    public static void main(String[] args) {
        
        int port = (args.length > 1) ? Integer.valueOf(args[0]) : 8081;
        
        System.out.println(" Week 6 Assignment 3: Echo Server w/Netty ");
        System.out.println(" Using port "+port);
        System.out.println(" Ctrl-C to QUIT");
        System.out.println("------------------------------------------");

        new Program(port).run();
    }
}
