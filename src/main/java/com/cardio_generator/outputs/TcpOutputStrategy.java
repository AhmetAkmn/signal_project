package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * TcpOutputStrategy is responsible for outputting data over a TCP connection.
 * It manages the creation of a TCP server socket and handles client connections.
 */
public class TcpOutputStrategy implements OutputStrategy {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;

    /**
     * Constructs a TcpOutputStrategy with a specified port.
     * It initializes the server socket and starts listening for client connections.
     *
     * @param port the port number on which the server will listen for incoming connections
     */

    public TcpOutputStrategy(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("TCP Server started on port " + port);

            // Accept clients in a new thread to not block the main thread
            Executors.newSingleThreadExecutor().submit(() -> {
                try {
                    clientSocket = serverSocket.accept();
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    System.out.println("Client connected: " + clientSocket.getInetAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Outputs data over the TCP connection. The data is formatted as a comma-separated string and sent to the connected client.
     *
     * @param patientId the ID of the patient. This identifies which patient's data is being recorded.
     * @param timestamp the timestamp of the data. This represents the time at which the data was generated or recorded.
     * @param label     the label of the data.
     * @param data      the data to be sent. This is the actual content that will be transmitted over the TCP connection.
     */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        if (out != null) {
            String message = String.format("%d,%d,%s,%s", patientId, timestamp, label, data);
            out.println(message);
        }
    }
}
