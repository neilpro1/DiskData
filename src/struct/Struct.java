package struct;

import java.util.Map;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;

public class Struct extends Record {

	public final int MAX_INFORMATION = 1000;
	public final int MAX_FILE = Integer.MAX_VALUE;

	private final String DELIMITER = "/";
	private final String PATH;

	private int numberOfFile;
	private int counter;

	private ObjectOutputStream obj;

	public Struct(String PATH) {
		this.PATH = PATH;
		this.counter = 0;
		this.numberOfFile = 0;
		try {

			File file = new File(PATH);
			file.mkdir();
			this.obj = new ObjectOutputStream(
					new FileOutputStream(new File(this.PATH + this.DELIMITER + this.numberOfFile)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if (this.counter + 1 < this.MAX_INFORMATION) {
			this.counter++;
		} else {
			this.counter = 1;
			this.numberOfFile++;
			try {
				this.obj = new ObjectOutputStream(
						new FileOutputStream(new File(this.PATH + this.DELIMITER + this.numberOfFile)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getWay() {
		return this.PATH + this.DELIMITER + this.numberOfFile;
	}

}
