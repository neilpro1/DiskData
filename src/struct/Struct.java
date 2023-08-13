package struct;

import java.util.Map;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;

public abstract class Struct extends Record {

	public final String DELIMITER = "/";
	public final String PATH = "struct";
	private final String ROOT = String.valueOf(Paths.get("").toAbsolutePath());
	private final String PACK, VAR;
	private final String TYPE_STRUCT;

	protected Struct(String TYPE_STRUCT, String PACK, String VAR) {
		this.PACK = getPack(PACK);
		this.TYPE_STRUCT = TYPE_STRUCT;
		this.VAR = VAR;
		super.createPath(this.getPathStruct());
		super.createPath(this.getPathTypeStruct());

		// for package
		String path_struct = this.getPathTypeStruct();
		String[] r = this.PACK.split(this.DELIMITER);

		for (int i = 0; i < r.length; i++) {
			path_struct += this.DELIMITER + r[i];
			this.createPath(path_struct);
		}
		// finish
		super.createPath(this.getPathVar());
	}

	public boolean delete() {
		File[] files = new File(this.getPathVar()).listFiles();
		for (File file : files)
			file.delete();
		return super.delete(this.getPathVar());
	}

	public boolean delete(Object key) {
		boolean fileToDelete = super.delete(this.getNameOfFile(key));
		return fileToDelete;
	}

	public int size() {
		try {
			return (new File(this.getPathVar()).listFiles()).length;
		} catch (Exception e) {
			return 0;
		}
	}

	/* return way for find path struct */
	protected String getPathStruct() {
		return this.ROOT + this.DELIMITER + this.PATH;
	}

	/* join path struct and return way to type of struct */
	protected String getPathTypeStruct() {
		return this.getPathStruct() + this.DELIMITER + this.TYPE_STRUCT;
	}

	/* join type struct and all package, return way to find package and class */
	protected String getPathPackage() {
		return this.getPathTypeStruct() + this.DELIMITER + this.PACK;
	}

	/* join package to var, and return way to find all data */
	protected String getPathVar() {
		return this.getPathPackage() + this.DELIMITER + this.VAR;
	}

	protected String getNameOfFile(Object index) {
		return this.getPathVar() + this.DELIMITER + index;
	}

	private String getPack(String pack) {
		String a = pack.split(" ")[1];
		String result = a.replace(".", this.DELIMITER);
		return result;
	}

}
