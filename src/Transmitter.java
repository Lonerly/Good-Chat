import java.io.IOException;
import java.net.Socket;

public class Transmitter extends Thread {

	String name, message;
	int port;

	public Transmitter() {

	}

	public Transmitter(String message, String name, int port) {
		this.message = message;
		this.name = name;
		this.port = port;
	}

	public void run() {
		try {
			Socket s = new Socket(name, port);
			s.getOutputStream().write(message.getBytes());
			s.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
