package n1;

import java.net.*;

public class Server extends Thread{
	public DatagramSocket socket;
	public static String hostname = "myHost";
	public int port = 4242;
	public InetAddress addressPlayer1, addressPlayer2;
	
	public Server(int port) {
		try {
			socket = new DatagramSocket(port);
		} catch(Exception e) {
			
		}
	}
	
	public void run() {
		boolean hasWon = true;
		byte[] buf = new byte[256];
		try {
			boolean play= true;
			int randValue = (int)(Math.random()*1000);
			
			DatagramPacket info = new DatagramPacket(buf, buf.length);
			socket.receive(info);
			addressPlayer1 = info.getAddress();
			// Waiting for player connexion
			socket.receive(info);
			addressPlayer2 = info.getAddress();
			
			while(play) {

				// get message
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				InetAddress address = packet.getAddress();
				// the name to send to other player when 1 player won
				String sendThis;
				if (address == addressPlayer1) {
					sendThis = "player_1 win";
					packet = new DatagramPacket(buf, buf.length, addressPlayer2, port);
					socket.send(packet);
				} else {
					sendThis = "player_2 win";
					packet = new DatagramPacket(buf, buf.length, addressPlayer1, port);
					socket.send(packet);
				}
		
				int port = packet.getPort();
				buf = sendThis.getBytes();
				// send message
				packet = new DatagramPacket(buf, buf.length, address, port);
				socket.send(packet);
				// change turn
				hasWon = !hasWon;
			}
		} catch(Exception e) {
			
		}
	}
	
	public void init() {
		
	}
	
	public static void main(String argv[]) {
		new Server(53).start();

	}
}