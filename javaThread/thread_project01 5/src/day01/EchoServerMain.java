package day01;

import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class EchoServerThread extends Thread{
	private Socket s;
	EchoServerThread(Socket cs){
		this.s = cs;
	}
	@Override
	public void run(){
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		String str = "";
		
			while(!(str = br.readLine()).startsWith("stop")){
				System.out.println("client msg: " + str);
				bw.write("echo:" + str +"\n");
				bw.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

public class EchoServerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket ss = null;
		Socket s = null;
		int clientNum = 0;
		try {
			ss = new ServerSocket(4444);
			while(true){
				s = ss.accept();
				clientNum++;
				new EchoServerThread(s).start();
			}
			//ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
