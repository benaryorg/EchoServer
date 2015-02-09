/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 benaryorg (binary@benary.org)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.benary.echoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author benaryorg
 */
public class Server extends Thread
{

    protected ServerSocket server;

    private Server(ServerSocket server)
    {
        this.server=server;
    }

    @Override
    public void run()
    {
        System.out.println(String.format("[%11d] ",System.currentTimeMillis()/1000)+"Server started!");
        Socket client;
        try
        {
            System.out.println(String.format("[%11d] ",System.currentTimeMillis()/1000)+"Listening on port "+server.getLocalPort());
            while((client=server.accept())!=null)
            {
                System.out.println(String.format("[%11d] ",System.currentTimeMillis()/1000)+client.getPort()+": Connected from \""+client.getInetAddress().getHostName()+"\"");
                new ConnectionHandler(client).start();
            }
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            ServerSocket server=new ServerSocket(1337);
            System.out.println(String.format("[%11d] ",System.currentTimeMillis()/1000)+"New ServerSocket created!");
            new Server(server).start();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }

}
