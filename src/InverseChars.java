class InverseChars {
 public static void main (String[] args) {
  if (args.length==1) {
   for (int i=args[0].length()-1;i>-1;i--) {
    System.out.print(args[0].charAt(i));
   }
  }
  else {System.out.println("No argument!!");}
 }
}