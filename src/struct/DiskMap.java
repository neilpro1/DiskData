package struct;

import java.io.File;
import java.nio.file.Paths;

public class DiskMap<K, V> extends Struct {

	public DiskMap(Class pack, String var) {
		super("diskmap", String.valueOf(pack), var);
	}

	public void put(K key, V value) {
		super.save(value, getNameOfFile(key));
	}

	public V get(K key) {
		return (V) super.read(getNameOfFile(key));
	}
} 
