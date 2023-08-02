package struct;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.List;

public class DiskList<T> extends Record {

	private final String DELIMITER = "/";

	private final String DISK_LIST = "/disklist/";
	private final String ROOT = String.valueOf(Paths.get("").toAbsolutePath().getParent()) + DISK_LIST;
	private final String VAR;
	private int counter = 0;

	public DiskList(String VAR) {
		this.VAR = VAR;
		super.createPath(ROOT);
		super.createPath(this.getVar());
	}

	public void add(T value) {
		super.save(value, getNameOfFile());
		this.counter++;
	}

	public T get(int index) {
		return (T) super.read(getNameOfFile(index));
	}

	public boolean delete() {
		File[] files = (new File(this.getVar())).listFiles();
		for (File file : files)
			file.delete();
		return super.delete(this.getVar());
	}

	public boolean delete(int index) {
		boolean fileToDelete = super.delete(this.getNameOfFile(index));

		for (int i = index + 1; i < this.counter; i++) {
			File file = new File(this.getNameOfFile(i));
			File newFile = new File(this.getNameOfFile(i - 1));
			file.renameTo(newFile);
		}
		return fileToDelete;
	}

	public int size() {
		File[] files = (new File(this.ROOT + this.VAR)).listFiles();
		return files.length;
	}

	private String getVar() {
		return this.ROOT + this.VAR;
	}

	private String getNameOfFile(int index) {
		return this.getVar() + this.DELIMITER + index;
	}

	private String getNameOfFile() {
		return this.getVar() + this.DELIMITER + this.counter;
	}

}
