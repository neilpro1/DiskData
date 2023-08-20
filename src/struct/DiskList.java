package struct;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.List;

public class DiskList<T> extends Struct {
	private int counter;

	public DiskList(Class pack, String var) {
		super("disklist", String.valueOf(pack), var);
		this.counter = super.size();	
	}
	
	public DiskList(String root, Class pack, String var) {
		super(root, "disklist", String.valueOf(pack), var);
		this.counter = super.size();
	}

	public void add(T value) {
		super.save(value, super.getPathVar() + this.DELIMITER + this.counter);
		this.counter++;
	}

	public T get(int index) {
		return (T) super.read(getNameOfFile(index));
	}

	@Override
	public boolean delete(Object index) {
		int indexInt = Integer.parseInt(String.valueOf(index));
		boolean fileToDelete = super.delete(super.getNameOfFile(indexInt));

		for (int i = indexInt + 1; i < this.counter; i++) {
			File file = new File(this.getNameOfFile(i));
			File newFile = new File(this.getNameOfFile(i - 1));
			file.renameTo(newFile);
		}
		return fileToDelete;
	}

}
