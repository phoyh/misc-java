import java.io.*;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class Chatroom extends UnicastRemoteObject implements ChatServer,ChatListener {
 public static String HOST="rmi://localhost/";
 Vector listeners;
 int listenerNum;
 private Chatroom() throws Exception{
  super();
  listeners=new Vector();
  listenerNum=0;
 }
 public void register(String listenerName) throws Exception {
  listeners.addElement(listenerName);
  System.out.println(listenerName+" registered");
 }
 public void unRegister(String listenerName) throws Exception {
  listeners.removeElement(listenerName);
  System.out.println(listenerName+" registered");
 }
 public void msgRequest(String origin,String content) throws Exception {
  for (int i=0;i<listeners.size();i++) {
   String listenerName=(String)listeners.elementAt(i);
   ((ChatListener)Naming.lookup(HOST+listenerName)).msgIndication(origin,content);
  }
 }
 public String getName() throws Exception {return "ChatListener_"+(listenerNum++);}
 public void msgIndication(String origin,String content) throws Exception {
  System.out.println(origin+": "+content);
 }
 public void observe() throws Exception {
  ChatServer cs=(ChatServer)Naming.lookup(HOST+"ChatServer");
  String listenerName=cs.getName();
  Naming.rebind(HOST+listenerName,(ChatListener)this);
  cs.register(listenerName);
 }
 private static String getCommand(String alias) {
  System.out.print(alias+": ");
  String str="";
  int asc=13;
  while (asc==13 || asc==10) {
   try {asc=System.in.read();}
   catch (IOException io) {asc=13;}
  }
  while (asc!=13 && asc!=10) {
   str+=(char)asc;
   try {asc=System.in.read();}
   catch (IOException io) {asc=13;}
  }
  return str;
 }
 public static void main(String[] args) throws Exception{
  if (args.length==0) {
   Naming.rebind(HOST+"ChatServer",(ChatServer)new Chatroom());
  }
  else {
   if (args[0].equals("-l")) {
    new Chatroom().observe();
   }
   else {
    String origin=args[0];
    ChatServer cs=(ChatServer)Naming.lookup(HOST+"ChatServer");
    String command="";
    while (!command.equalsIgnoreCase("exit")) {
     command=getCommand(origin);
     cs.msgRequest(origin,command);
    }
   }
  }
 }
}

interface ChatServer extends Remote {
 void register(String listenerName) throws Exception;
 void unRegister(String listenerName) throws Exception;
 void msgRequest(String origin,String content) throws Exception;
 String getName() throws Exception;
}

interface ChatListener extends Remote {
 void msgIndication(String origin,String content) throws Exception;
 void observe() throws Exception;
}
