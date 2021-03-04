package services;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FileService {
	
	private static FileService fileService_instance =null;
	private ArrayList<File> fileStorage = new ArrayList<File>();
	private static final String directory = "F:\\Java Projects\\VirtualKey\\src\\fileStorage\\";
    
	private FileService() {
		File f = new File(directory);
		fileStorage = new ArrayList<File>(Arrays.asList(f.listFiles()));
	}
	public void  update() {
		File f = new File(directory);
		fileStorage = new ArrayList<File>(Arrays.asList(f.listFiles()));
	}
	public static FileService getInstance() {
		if(fileService_instance==null) {
			fileService_instance = new FileService();
		}
		return fileService_instance;
	}
	public void addFile(String fileName) throws Exception {
		
		if(this.searchFile(fileName)) {
			System.out.println("The file name has already been used");
		}
		else {
				File file = new File(directory+fileName.toLowerCase());
				file.createNewFile();
				fileStorage.add(file);
				System.out.println("File created at ->"+file.getPath());
		}
	}
	public void deleteFile(String fileName) {
		for(File file:fileStorage) {
			if(file.getName().equals(fileName)) {

				file.delete();
				//fileStorage.remove(file);
				System.out.println(fileName+" was deleted");
				return;
			}
		}
		System.out.println(fileName+" couldn't be deleted");
    }
	public Boolean searchFile(String fileName) {
		for(File file:fileStorage) {
			if(file.getName().equals(fileName)) {
				return true;
			}
		}
		return false;
	}
	public void printAllFiles() {
		for(File file: fileStorage) {
			System.out.println(file.getName());
		}
	}
	public void sortFiles() {
		Collections.sort(fileStorage,(o1,o2)->o1.getName().compareTo(o2.getName()));
	}

}
