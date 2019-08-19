package damashinesa;

import java.awt.List;
import java.net.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

class SrvThread extends Thread {

    ServerSocket serverSocket = null;
    Socket socket = null;
    Socket socket2 = null;
    static DataOutputStream ostream1 = null;
    static DataOutputStream ostream2 = null;
    static int port = 9090;
    DataInputStream istream1 = null;
    DataInputStream istream2 = null;
    String MRcv = "";
    static String MSnd = "";
    List lista;
    cliente1 c1 = new cliente1();
    Thread t1 = new Thread(c1);
    cliente2 c2 = new cliente2();
    Thread t2 = new Thread(c2);

    SrvThread() {
        while (true) {
            try {
                serverSocket = new ServerSocket(port);

                System.out.println("Aguardando conexão1...");
                socket = serverSocket.accept();
                System.out.println("Conexão1 Estabelecida.");
                System.out.println("Aguardando conexão2...");
                socket2 = serverSocket.accept();
                System.out.println("Conexão2 Estabelecida.");
                ostream2 = new DataOutputStream(socket2.getOutputStream());
                ostream1 = new DataOutputStream(socket.getOutputStream());
                istream1 = new DataInputStream(socket.getInputStream());
                istream2 = new DataInputStream(socket2.getInputStream());

                t1.start();
                t2.start();

                Scanner console = new Scanner(System.in);
                while (true) {
                    System.out.println("Mensagem: ");
                    String MSnd = console.nextLine();
                    ostream1.writeUTF(MSnd);
                    ostream1.flush();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class cliente1 implements Runnable {

        public void run() {
            try {
                while (true) {

                    MRcv = istream1.readUTF();
                    ostream2.writeUTF(MRcv);
                    ostream2.flush();
                    System.out.println("Remoto1: " + MRcv);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class cliente2 implements Runnable {

        public void run() {
            
            try {
                while (true) {
                MRcv = istream2.readUTF();
                ostream1.writeUTF(MRcv);
                ostream1.flush();
                System.out.println("Remoto2: " + MRcv);
            }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String args[]) {
        new SrvThread();
    }
}
