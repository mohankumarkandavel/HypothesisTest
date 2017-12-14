import java.io.*;
class init {
	public static void main(String args[])	{
		String[] commandServer = {"/bin/sh", "-c", "cd /home/HypothesisTest/HypoServer/src/com/hypothesis/server; java HypoServer"};
		String[] commandClient = {"/bin/sh", "-c", "cd /home/HypothesisTest/HypoTest; python test1.py"};

		Thread runServer = new Thread(new ThreadClass(commandServer));
		Thread runClient = new Thread(new ThreadClass(commandClient));
		runServer.start();
		runClient.start();
		runServer.interrupt();
	}
}
class ThreadClass  extends Thread {
	public String[] command;
	public ThreadClass(String[] command){
		this.command = command;
	}
	public void run() {

		String s = null;
		try {
			Process p =Runtime.getRuntime().exec(command);

		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader error =new BufferedReader(new InputStreamReader(p.getErrorStream()));

		while((s= in.readLine()) != null) {
			System.out.println(s);
		}
		while ((s = error.readLine()) != null) {
			System.out.println(s);
		}
		System.exit(0);
		}
		catch (IOException e) {
			System.out.println("exception occured");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}

