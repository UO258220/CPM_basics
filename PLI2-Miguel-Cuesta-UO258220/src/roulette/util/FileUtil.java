package roulette.util;

import java.io.*;
import java.util.*;

import roulette.model.Prize;

public abstract class FileUtil {

	public static void loadFile(String fileName, List<Prize> listCatalog) {
		String line;
		String[] infoPrice = null;
		try {
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			while (file.ready()) {
				line = file.readLine();
				infoPrice = line.split("@");
				listCatalog.add(new Prize(infoPrice[0],infoPrice[1],infoPrice[2],Integer.parseInt(infoPrice[3])));
			}
			file.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error");
		}
	}
}
