package jarreader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JarReader {
	public static void main(String[] args) throws IOException{
		String pathToFile =  "D:\\Java\\workspaces\\JARS\\output.jar";
		processJarFile(pathToFile);
	}
	
	private static void processJarFile(String filePath){
		List jarFileNames = getJarFileNames(filePath);
		
		printEntries(jarFileNames);
	} 
	
	private static List getJarFileNames(String pathToFile){
		List<String> fileNames = new ArrayList<String>();
		try{
			ZipInputStream zip = new ZipInputStream(new FileInputStream(pathToFile));
			for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
				if (!entry.isDirectory()) {
					String className = entry.getName();
					fileNames.add(className);
				}
			}
		} catch(FileNotFoundException e){
			System.out.println("File \"" + pathToFile + "\" wasn't found." );
		} catch (IOException e){
			e.printStackTrace();
		}
		return fileNames;
	}
	
	private static void printEntries(List list){
		for(Object item: list){
			System.out.println(item);
		}
	}
}