/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gorsel_socket_halife_turan_203801005;

import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author halif
 */
public class main_server extends javax.swing.JFrame {
static Socket client=null;
    /**
     * Creates new form mesajlariGoster
     */
    public main_server() {
        initComponents();
    }
public static String c_ad=null;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextArea1CaretUpdate(evt);
            }
        });
        jTextArea1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTextArea1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTextArea1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextArea1PropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextArea1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextArea1PropertyChange
        
    }//GEN-LAST:event_jTextArea1PropertyChange

    private void jTextArea1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTextArea1AncestorAdded
       
    }//GEN-LAST:event_jTextArea1AncestorAdded

    private void jTextArea1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextArea1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea1CaretUpdate
static String line=null;
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main_server().setVisible(true);
            }
        });
           ServerSocket server = null; //server socketini tutacak bir nesne tanımlıyorum
  
        try {
  
 
            server = new ServerSocket(9999); //serverim 9999 portundan bağlanılmayı beklenir hale geliyor
            server.setReuseAddress(true);

            while (true) {//bu döngü sonsuz sayıda clientin giriş yapabilmesi için sürekli çalışır hâlde, thread ile de sırayı ortadan kaldırıp hepsini aynı anda işleyebilir hâle getiriyorum
  
                
                Socket client = server.accept();// soket nesnem(client) serverden gelen requestleri kabul ediyor
  
               Date date = new Date(); //loglama işlemleri için date nesnesi oluşturdum
               SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");//loglama işlemleri için formatter nesnesi oluşturdum ve saat,dakika,saniye şeklinde date formatlayacak
                c_ad=client.getLocalSocketAddress().toString();//c_ad değişkenime(kullanıcı adı) clientimin socket adresinin değerini veriyorum
                jTextArea1.append("\n"+formatter.format(date)+" saatinde "+c_ad+" Bağlantı Kurdu! "+"\n");//textareaya bağlantının kurulduğunu ve bağlantı zamanını yazdırıyorum
  
              
                ClientHandler clientSock= new ClientHandler(client); //thread işlemi için clienthandler nesnesi oluşturuldu
  
               
                new Thread(clientSock).start();//her bir clientsock nesnesi için yeni thread başlatılacak
               
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) { //server kontrolü
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
  
    // ClientHandler class
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket; //socket nesnesi oluşturdum
  
        // Constructor
        public ClientHandler(Socket socket) //client handler classı constructorına socket parametresi ile çalışacağını bildirdik 
        {
            this.clientSocket = socket;
        }
  
        public void run() 
        {
            
            BufferedReader in = null;//nesne referansı oluşturuldu
            try {
                    
  
                  
                in = new BufferedReader(
                    new InputStreamReader(
                        clientSocket.getInputStream()));
  
                
                 
                while ((line = in.readLine()) != null) { //clientten gelen mesaj null olmadığı sürece dönecek
  
                 
                  
                   Date date = new Date(); //loglama işlemleri için date nesnesi oluşturdum
             SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");//loglama işlemleri için formatter nesnesi oluşturdum ve saat,dakika,saniye şeklinde date formatlayacak
 
        jTextArea1.append("\n"+formatter.format(date)+"\n"+c_ad+" : "+line+"\n"); //loglama, clientten gelen mesajın gönderilmesi ve client bilgisinin yazıldığı yer
                  
                    
          
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (in != null) { //null check
                        in.close();
                        clientSocket.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        
    

       
    }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
    
