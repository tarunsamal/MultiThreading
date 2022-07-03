package com.tarun.multi;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadPoolExample {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080),0);
        server.createContext("/doIt", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            Double base = Double.valueOf(query.split("&")[0].split("=")[1]);
            Double power =  Double.valueOf(query.split("&")[1].split("=")[1]);
            byte[] response = String.valueOf(calcPower(base,power)).getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200,response.length);
            exchange.getResponseBody().write(response);
            exchange.getResponseBody().close();

        });
        Executor executor = Executors.newFixedThreadPool(1);
        server.setExecutor(executor);
        server.start();


    }

    public  static double calcPower(double base ,double power)
    {
       return Math.pow(base,power) ;
    }
}
