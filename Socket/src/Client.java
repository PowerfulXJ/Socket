import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) throws IOException {
    	DatagramSocket ds = new DatagramSocket();
    	ds.setSoTimeout(1000);
    	ds.connect(InetAddress.getByName("localhost"), 6666); // 连接指定服务器和端口
    	System.out.println("connected!");
    	Scanner scanner = new Scanner(System.in);
    	// 发送:
    	for(int i = 0; i < 100; i++) {
    		String snt = scanner.nextLine();
    		byte[] data = snt.getBytes();
    		DatagramPacket packet = new DatagramPacket(data, data.length);
    		ds.send(packet);
    		// 接收:
    		byte[] buffer = new byte[1024];
    		packet = new DatagramPacket(buffer, buffer.length);
    		ds.receive(packet);
    		String resp = new String(packet.getData(), packet.getOffset(), packet.getLength());
    		System.out.println(resp);
    	}
    	ds.disconnect();
    }
}