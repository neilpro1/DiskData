package struct;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

public class Record {
	
	private String ROOT;
	private final String STATUS = "/status";
	private int counter;
	
	public Record() {
		this.counter = 0;
	}
	
	public Record(String root) {
		this.ROOT = root;
		this.counter = this.recoveryCounter();
	}
	
	public boolean createPath(String path) {
		File file = new File(path);
		return file.mkdir();
	} 
	
	public boolean existPath(String path) {
		return (new File(path).isDirectory());
	}
	
	public boolean existFile(String file) {
		return (new File(file).exists());
	}
	
	public void save(Object obj, String file) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(file)));
			out.writeObject(obj);
			this.counter++;
			out.flush();
			out.close();
			this.updateSize();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object read(String file) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(file)));
			return in.readObject();
		}catch(Exception e) {
			return null;
		}
	}
	
	public boolean delete(String file) {
		this.counter--;
		this.updateSize();
		return (new File(file)).delete();
	}
	
	public void setRoot(String root) {
		this.ROOT = root;
	}
	
	protected int getCounter() {
		return this.counter;
	}
	
	private int recoveryCounter() {
		try  {
			BufferedReader bw = new BufferedReader(new FileReader(new File(this.ROOT + this.STATUS)));
			String result[] = bw.readLine().split(":");
			return Integer.parseInt(result[1]);
		}catch(Exception e) {
			return 0;
		}
	}
	
	private void updateSize() {
		try  {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(this.ROOT + this.STATUS)));
			bw.write("size:"+this.counter);
			bw.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
