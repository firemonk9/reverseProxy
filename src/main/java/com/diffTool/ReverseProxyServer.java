package com.diffTool;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by dhiraj on 9/26/16.
 */

public class ReverseProxyServer {
    public static class MyProxyServlet extends ProxyServlet {
        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);
//            System.out.println("1  "+config.getInitParameter("targetUri"));
        }

        @Override
        public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//            System.out.println(">>> got a request !");
            super.service(req, res);
        }
    }

    public static void main(String... args) throws Exception {

        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setResourceBase(System.getProperty("java.io.tmpdir"));
        server.setHandler(context);


        context.addServlet(MyProxyServlet.class, "/*");
        context.setInitParameter("targetUri", "http://localhost:7991/");

        server.start();
        server.join();


    }
}