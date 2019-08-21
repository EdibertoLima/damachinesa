package damashinesa;
import java.net.*;
import java.io.*;
import java.util.*;

public class Cliente1 extends Thread {

  static ObjectOutputStream ostream = null;
  ObjectInputStream istream = null;
  static String host = "";
  static int port = 9090;
  Socket socket = null;
  String MRcv= "";
  static String MSnd= "";


  Cliente1(){
    try {
     socket = new Socket(host, port);
     System.out.println("Conectado....");
     this.start();
     ostream = new ObjectOutputStream(socket.getOutputStream());
     istream = new ObjectInputStream(socket.getInputStream());
     Scanner console = new Scanner(System.in);
     while(true){
	 System.out.println("Mensagem: ");
         Mensagem m1 = new Mensagem("mensagem");
         String MSnd = console.nextLine();
         m1.setParam("mensagem",MSnd);
         ostream.writeObject(m1);
         ostream.flush();
         
//        System.out.println("jogada: ");
//        
//        int jogada = console.nextInt();
//        Mensagem m = new Mensagem("jogada");
//        m.setParam("posicao",jogada);
//        ostream.writeObject(m);
//        ostream.flush();
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
