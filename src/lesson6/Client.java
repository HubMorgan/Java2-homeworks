package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final static String SERVER_ADDRESS = "localhost";
    private final static int SERVER_PORT = 8189;
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;
    private volatile static boolean flag = false;
    private volatile static boolean flag2 = false;

    public static void main(String[] args) throws IOException{

        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            Runnable inputMessage = () -> {
                try {
                    while (true) {
                        String strIn = in.readUTF();
                        if (strIn.equalsIgnoreCase("/end")) {
                            break;
                        }

                        if (!strIn.trim().isEmpty()) {
                            System.out.println("Сервер: " + strIn);
                        }

                    }
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

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
                            System.out.println("Пользователь: " + strOut);
                        }

                    }
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            Thread Thread1 = new Thread(inputMessage);
            Thread1.setDaemon(true);
            Thread1.start();

            Thread Thread2 = new Thread(outputMessage);
            Thread2.setDaemon(true);
            Thread2.start();

            while (true) {
                if (Thread1.isInterrupted()) {
                    in.close();
                    out.close();
                    break;
                }

                if (Thread2.isInterrupted()) {
                    in.close();
                    out.close();
                    socket.close();
                    System.out.println("Работа клиента успешно завершена!");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
