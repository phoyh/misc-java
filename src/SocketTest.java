import java.net.*;

class SocketTest {
 private static String[] splitAddress(String address) {
  String[] ret=new String[2];
  int index=address.indexOf(":");
  ret[0]=address.substring(0,index);
  ret[1]=address.substring(index+1);
  return ret;
 }
 public static void main(String[] args) {
  String[] address=splitAddress(args[0]);
  String ip=address[0];
  int port=new Integer(address[1]).intValue();
  String message="";
  if (args.length==5) message=args[1]+((char)10)+args[2]+((char)10)+args[3]+((char)10)+args[4];
  if (args.length==4) message=args[1]+((char)10)+args[2]+((char)10)+args[3];
  try {
   DatagramSocket soc=new DatagramSocket();
   DatagramPacket p=new DatagramPacket(message.getBytes(),message.length(),InetAddress.getByName(ip),port);
   soc.send(p);
   if (args[1].equals("info")) {
    DatagramPacket dp=new DatagramPacket(new byte[1024],1024);
    soc.receive(dp);
    System.out.println("Got:\""+new String(dp.getData(),0,dp.getLength())+"\"");
   }
  }
  catch (Exception e) {System.out.println("Error");}
 }
}
