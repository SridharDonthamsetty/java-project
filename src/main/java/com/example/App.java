package com.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class App {

    // SonarQube-friendly logger (replaces System.out.println)
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws IOException {
        int port = 8080;

        // Create HTTP server
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Register endpoint
        server.createContext("/", new RootHandler());

        // Use default executor
        server.setExecutor(null);

        // Start server
        server.start();

        // Log startup message using Logger instead of System.out.println
        LOGGER.info("Server started on port " + port);
    }

    static class RootHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello DevSecOps from Docker!";
            byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);

            // Set response headers
            exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=UTF-8");

            // Send HTTP 200 response
            exchange.sendResponseHeaders(200, responseBytes.length);

            // Write response body
            try (OutputStream outputStream = exchange.getResponseBody()) {
                outputStream.write(responseBytes);
            }
        }
    }
}
