import gnu.io.*;
import java.util.*;
import java.io.*;

public class testmodules{
	public static void main(String args[]){
		try{
			CommPortIdentifier com= CommPortIdentifier.getPortIdentifier("LPT1");
			CommPort theport= com.open("PortOpener",10);
			CopyThread input = new CopyThread(System.in,theport.getOutputStream());
			CopyThread output = new CopyThread(theport.getInputStream(),System.out);
			input.start();
			output.start();
		}
			catch(Exception e){
				System.out.println(e);
				}
			
		}
	
}

class CopyThread extends Thread{
	private InputStream theInput;
	private OutputStream theOutput;
	
	CopyThread(InputStream in){
		this(in,System.out);
	}

	CopyThread(OutputStream out){
		this(System.in,out);
	}
	CopyThread(InputStream in, OutputStream out){
		theInput = in;
		theOutput = out;
	}
	public void run(){
		try{
			byte[] buffer = new byte[256];
			while(true){
				//int bytesread = theInput.read(buffer);
				//if(bytesread == -1) break;
				buffer[0] = 1;
				theOutput.write(buffer, 0 , 1);
				
			}
		}
		catch(IOException ex){System.err.println(ex);}
	}
}
	

