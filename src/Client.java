import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
	
	public static boolean isBinarySymmetry(String binary) {
		int numBinary = Integer.parseInt(binary);
		int temp = numBinary;
		int reverse = 0;
		while(temp != 0) {
			int tmp = temp % 10;
			reverse = reverse*10 + tmp;
			temp /= 10;
		}
		if(reverse == numBinary) {
			return true;
		}else
			return false;
	}

	public static void main(String[] args)throws Exception {
	
		DatagramSocket clientSocket = new DatagramSocket();
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		
		InetAddress IPAddress = InetAddress.getByName("localhost");
		System.out.println("Connected");
		Scanner sc = new Scanner(System.in);
		boolean checker = true;
		while(checker) {
			System.out.print("Client: ");
			String msg = sc.nextLine();
			
			sendData = msg.getBytes();
			DatagramPacket send = new DatagramPacket(sendData, sendData.length,IPAddress,6969);
			clientSocket.send(send); // gui dl cho server
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			String receive = new String(receivePacket.getData()).trim();
			System.out.println("Binary " + receive);
			if(isBinarySymmetry(receive)) {
				checker = false;
				System.out.println("Disconnect!");
			}
			sc = sc.reset();
		}
		clientSocket.close();
	}
}
