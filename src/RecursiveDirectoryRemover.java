import java.io.*;

class RecursiveDirectoryRemover {
 private static void rmDir(String dirName) {
  File f=new File(dirName);
  if (f.isDirectory()) {
   String[] children=f.list();
   for (int i=0;i<children.length;i++) {
    RecursiveDirectoryRemover.rmDir(dirName+"/"+children[i]);
   }
  }
  f.delete();
 }
 public static void main (String[] args) {
  if (args.length!=1) {
   System.out.println("rmDir (dirName)");
   System.exit(0);
  }
  rmDir(args[0]);
 }
}
