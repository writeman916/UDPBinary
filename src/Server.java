import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

	public static void main(String args[]) throws Exception{
		DatagramSocket serverSocket = new DatagramSocket(6969);
		System.out.println("Server stated");
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		
		while(true) {
			
			DatagramPacket receive = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receive);
			
			InetAddress IPAddress = receive.getAddress();
			int port = receive.getPort();
			
			String decimal = new String(receive.getData());
			System.out.println(decimal);
			String binary = Integer.toBinaryString(Integer.parseInt(decimal.trim()));
			
			sendData = binary.getBytes();
			
			DatagramPacket send = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(send);
				
		}
		
	}
}
