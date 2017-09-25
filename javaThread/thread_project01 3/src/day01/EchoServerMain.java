package day01;

import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
			System.out.println("server Start!");
			while(true){
				s = ss.accept();
				System.out.println("client accept!");
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

class ClientMgmt{
	private ArrayList<Socket> clients = new ArrayList<Socket>();
	
	private ClientMgmt clientMgmt = new ClientMgmt();
	
	public ClientMgmt getClientMgmt(){
		return clientMgmt;
	}
	
//	public void echoAllClient(String s){
//	}
}
