import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener extends Thread {

	ServerSocket server;
	int port = 9969;

	WritingOn gui;

	public Listener(WritingOn gui, int port) {
		this.port = port;
		this.gui = gui;

		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Listener() {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		Socket clientSocket;
		try {
			while ((clientSocket = server.accept()) != null) {
				InputStream is = clientSocket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = br.readLine();
				if (line != null) {
					gui.write(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
