package struct;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.File;

public abstract class Record {

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
			out.flush();
			out.close();
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
		return (new File(file)).delete();
	}
	
}
