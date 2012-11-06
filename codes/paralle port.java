import parport.ParallelPort;

class ProIO {
public static void main ( String []args )
{
ParallelPort lpt1 = new ParallelPort(0x378);
int aByte;
aByte = lpt1.readOneByte(0x378);
System.out.println("Input from parallel port: " + aByte);
lpt1.write(255);
System.out.println("Output to port: " + lpt1.readOneByte(0x378));
}
}