/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogototal;

import damashinesa.Mensagem;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author ediberto
 */
public class jogador_1 extends Thread {

    chat chat = new chat();
    Thread c = new Thread(chat);
    static ObjectOutputStream ostream = null;
    ObjectInputStream istream = null;
    static String host = "192.168.1.8";
    static int port = 9090;
    Socket socket = null;
    Mensagem m;

    jogador_1(String host, int port) {
        try {
            
            socket = new Socket(host, port);
            ostream = new ObjectOutputStream(socket.getOutputStream());
            istream = new ObjectInputStream(socket.getInputStream());
            c.start();

            while (true) {
                System.out.println("Mensagem: ");
                Scanner console = new Scanner(System.in);
                Mensagem m1 = new Mensagem("mensagem");
                String MSnd = console.nextLine();
                m1.setParam("mensagem", MSnd);
                ostream.writeObject(m1);
                ostream.flush();

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    
 

    /**
     * @param args the command line arguments
     */
    public class chat implements Runnable {

        public void run() {

            try {
                while (true) {

                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

//    public static void main(String[] args) {
//        // TODO code application logic here
//        host = args.length == 0 ? "localhost" : args[0];
//        System.out.println(host);
//        new jogador();
//        
//    }

}
