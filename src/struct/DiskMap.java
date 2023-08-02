package struct;

import java.io.File;
import java.nio.file.Paths;

public class DiskMap<K, V> extends Record {

	private final String DISK_MAP = "/diskmap/";
	private final String ROOT = String.valueOf(Paths.get("").toAbsolutePath().getParent()) + DISK_MAP;

	private final String DELIMITER = "/";
	private final String VAR;

	public DiskMap(String VAR) {
		this.VAR = VAR;
		this.createPath(ROOT);
		this.createPath(this.getVar());
	}

	public void put(K key, V value) {
		super.save(value, getNameOfFile(key));
	}

	public V get(K key) {
		return (V) super.read(getNameOfFile(key));
	}

	public boolean delete(K key) {
		return super.delete(getNameOfFile(key));
	}

	public boolean delete() {
		File[] files = (new File(this.getVar())).listFiles();
		for (File file : files) {
			file.delete();
		}
		return super.delete(this.getVar());
	}

	public int size() {
		File[] files = (new File(this.ROOT + this.VAR)).listFiles();
		return files.length;
	}

	private String getVar() {
		return this.ROOT + this.VAR;
	}

	private String getNameOfFile(K key) {
		return this.getVar() + this.DELIMITER + key;
	}
}
