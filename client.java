package gorsel_socket_halife_turan_203801005;

import java.io.*;

import java.net.*;
import java.util.*;

public class client {
public static String Username;

  public static BufferedReader bufferedReader;
    public static void main(String[] args) {

           
            
            
            // Scanner girdi = new Scanner(System.in);        
            // main_server server=new main_server();
             //server.c_ad=girdi.toString();
            
        try (Socket socket = new Socket("localhost", 9999)) {     // bağlanılacak sunucunun bilgileri 
        
                     
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); //bağlanılan sunucuya yazılacak yazı
  
            
           // System.out.println(line.toString());    
            
          
            BufferedReader in= new BufferedReader(new InputStreamReader(socket.getInputStream())); //gönderilen yazının okunması
            
           
            Scanner sc = new Scanner(System.in);//burada okuma yapması için scanner nesnei tanımlanıyor
            String line=null;//formda yani sunucuda göstermek için burada tanımladığım line değişkeni, sunucuda textareada bu değişken yazılacak
            

            while (true) {//cikis yazısı yazana kadar okuma devam edecek
            
                line = sc.nextLine();//clientin yazdığı yazının okunması
                
           
                out.println(line);//flush ile birlikte sunucuya (line) değişkeni gönderiliyor
                out.flush();
                
            
               // System.out.println("Server replied " + in.readLine());//buna aslında gerek yok fakat test amaçlı gönderdiğim yazının sunucuda okunmasını sağlıyor, bu proje başta console projesiydi forma dönüştürünce gerek kalmadı bu satıra
           
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
