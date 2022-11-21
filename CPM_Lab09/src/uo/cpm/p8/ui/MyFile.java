package uo.cpm.p8.ui;

import java.io.File;

public class MyFile {
	
	private File file;
	
	public MyFile (File file) {
		this.file = file;
	}
	
	public File getFile() {
		return file;
	}
	
	@Override
	public String toString() {
		String fullName = file.getName();
		return fullName.substring(0, fullName.length() - 4);
	}

}
