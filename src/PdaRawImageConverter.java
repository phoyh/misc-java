import java.io.*;

class PdaRawImageConverter {
 public final static String IMGPATH="images/";
 public static void main (String[] args) throws Exception {
  if (args.length==0) {
   System.out.println("Syntax: pdaImage (FileName)");
   System.out.print("\nThis program converts a 8-bit-160x240-greyscale-raw-image ");
   System.out.println("to a 4-bit-160x240-greyscale-raw-image and vice-versa");
  }
  else {
   File f=new File(IMGPATH+args[0]);
   if (!f.exists()) {
    System.out.println("Couldn't open file: doesn't exist");
   }
   else {
    FileInputStream fis=new FileInputStream(f);
    String outputName;
    boolean isToRaw=!args[0].substring(args[0].length()-3).equalsIgnoreCase("raw");
    if (!isToRaw) {
     outputName=args[0].substring(0,args[0].length()-3)+"bmp.fbm";
    }
    else {
     outputName=args[0].substring(0,args[0].length()-7)+"raw";
    }
    FileOutputStream fos=new FileOutputStream(IMGPATH+outputName);
     for (int i=0;i<f.length()/(isToRaw?1:2);i++) {
      if (isToRaw) {
       int a=fis.read();
       fos.write(a & 240);
       fos.write((a&15)*16);
      }
      else {
       int a=fis.read();
       int b=fis.read();
       fos.write(a/16*16+b/16);
      }
     }
    fis.close();
    fos.close();
   }
  }
 }                                        
}
