package damashinesa;

import java.awt.List;
import java.net.*;
import java.io.*;
import java.util.*;
import damashinesa.Mensagem;
import java.awt.Color;

class SrvThread extends Thread {

    ServerSocket serverSocket = null;
    Socket socket = null;
    Socket socket2 = null;
    static ObjectOutputStream ostream1 = null;
    static ObjectOutputStream ostream2 = null;
    static int port = 9090;
    ObjectInputStream istream1 = null;
    ObjectInputStream istream2 = null;
    String MRcv = "";
    int movimetox;
    int movimetoy;
    int row, col;
    Color cor;
    static String MSnd = "";
    List lista;
    cliente1 c1 = new cliente1();
    Thread t1 = new Thread(c1);
    cliente2 c2 = new cliente2();
    Thread t2 = new Thread(c2);
    jogo jogada = new jogo();
    Thread j = new Thread(jogada);
    Mensagem m;

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
                ostream2 = new ObjectOutputStream(socket2.getOutputStream());
                istream2 = new ObjectInputStream(socket2.getInputStream());
                ostream1 = new ObjectOutputStream(socket.getOutputStream());
                istream1 = new ObjectInputStream(socket.getInputStream());

                t1.start();
                t2.start();
                j.start();

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
                    m = (Mensagem) istream1.readObject();
                    String operacao = m.getOperacao();
                    if (operacao.equals("mensagem")) {
                        ostream2.writeObject(m);
                        ostream2.flush();
                        
                    }
                    if (operacao.equals("jogada")) {
            
                        ostream2.writeObject(m);
                        ostream2.flush();

                    }

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
                    m = (Mensagem) istream2.readObject();
                    String operacao = m.getOperacao();
                    if (operacao.equals("mensagem")) {
                        ostream1.writeObject(m);
                        ostream1.flush();
                        
                    }
                    if (operacao.equals("jogada")) {
                       
                        ostream1.writeObject(m);
                        ostream1.flush();

                    }
                    }
                
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class jogo implements Runnable {

        public void run() {

            try {
                while (true) {
//                    m = (Mensagem) istream1.readObject();
//                    String operacao = m.getOperacao();
//                    System.out.println(operacao);
//                    if(operacao.equals("jogada")){
//                     movimetox = (int) m.getParam("posicao");
//                    
//                    ostream1.writeUTF(MRcv);
//                    ostream1.flush();
//                    System.out.println("Remoto2: " + movimetox);
//                    }
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("erro jogo");
            }
        }
    }

    public static void main(String args[]) {
        new SrvThread();
    }
}
