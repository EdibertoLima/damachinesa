/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damashinesa;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Cliente2 extends Thread {

  static DataOutputStream ostream = null;
  DataInputStream istream = null;
  static String host = "127.0.0.2";
  static int port = 9090;
  Socket socket = null;
  String MRcv= "";
  static String MSnd= "";


  Cliente2(){
    try {
     socket = new Socket(host, port);
     System.out.println("Conectado....");
     this.start();
     ostream = new DataOutputStream(socket.getOutputStream());
     istream = new DataInputStream(socket.getInputStream());
     Scanner console = new Scanner(System.in);
     while(true){
	System.out.println("Mensagem: ");
        String MSnd = console.nextLine();
        ostream.writeUTF(MSnd);
        ostream.flush();
      }
    } catch(Exception e) {System.out.println(e);}
  }

  public void run(){
    while (true) {
      try {        
        MRcv = istream.readUTF();
        System.out.println("Remoto: " + MRcv);
      } catch(Exception e) {}
    }
  }


  public static void main(String args[]){
    host = args.length == 0 ? "localhost" : args[0];
      System.err.println("host"+host);
    new Cliente1(); 
  }
}
