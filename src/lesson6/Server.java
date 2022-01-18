package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static Socket socket;
    private static volatile boolean flag = false;

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Runnable outputMessage = () -> {
                try {
                    Scanner scOut = new Scanner(System.in);
                    while (true) {
                        String strOut = scOut.nextLine();
                        if (strOut.equals("/end")) {
                            out.writeUTF(strOut);
                            scOut.close();
                            break;
                        }

                        if (!strOut.trim().isEmpty()) {
                            out.writeUTF(strOut);
                            System.out.println("Сервер: " + strOut);
                        }

                    }
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            Runnable inputMessage = () -> {
                try {
                    while (true) {
                        String strIn = in.readUTF();
                        if (strIn.equalsIgnoreCase("/end")) {
                            break;
                        }

                        if (!strIn.trim().isEmpty()) {
                            System.out.println("Пользователь: " + strIn);
                        }

                    }
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            Thread Thread1 = new Thread(outputMessage);
            Thread1.setPriority(2);
            Thread1.setDaemon(true);
            Thread1.start();

            Thread Thread2 = new Thread(inputMessage);
            Thread2.setDaemon(true);
            Thread2.start();


            while (true) {
                if (Thread1.isInterrupted() || Thread2.isInterrupted()) {
                    in.close();
                    out.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Работа сервера успешно завершена!");
        socket.close();
    }
}