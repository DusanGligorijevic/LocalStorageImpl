package localStorageImpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import storage.Storage;
import storage.StorageManager;
import localStorageImpl.*;

public class LocalStorageImpl extends Storage{
	
	static {
		StorageManager.registerStorage(new LocalStorageImpl());
		
	}
	
	public LocalStorageImpl() {
		
	}

	@Override
	public void create(String path, int maxFolders) {
		String name ="file";
		for(int i = 0; i < maxFolders; i++) {
			
			//Instantiate the File class   
            File storage = new File(StoragePath+"\\"+name+i);  
            //Creating a folder using mkdir() method  
            boolean bool = storage.mkdir();  
            if(bool){  
               System.out.println("folder "+name+i+" je uspesno kreiran!");  
            }else{  
               System.out.println("Greska pri kreiranju foldera "+name);  
            }
		}
		
	}

	@Override
	public void delete(String path) {
		File f = new File(path);
		if(f.delete()) {
			System.out.println("Folder je izbrisan");
		}else {
			System.out.println("Folder nije izbrisan");
		}		
	}

	@Override
	public void transfer(String location,String destination,String file) {
		File folder = new File(location);
		File [] prevFiles=folder.listFiles();
		for(File f:prevFiles) {
				File file1 = new File(file);
				if(file1.exists()) {
					File dest = new File(destination);
					try {
						Files.copy(file1.toPath(), dest.toPath(),StandardCopyOption.REPLACE_EXISTING);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else
					System.out.println("Ne postoji folder!");	
		}			
	}

	@Override
	public void preview(File f,String extension) {
		File [] prevFiles=f.listFiles();
		System.out.println("Trazeni fajlovi:");
		for(File file:prevFiles) {
			if(f.getName().contains(extension)) {
				System.out.println(f.getName());
				
			}
		}		
	}
	
	@Override
	public void preview(File f) {
		File [] prevFiles=f.listFiles();
		System.out.println("Trazeni fajlovi:");
		for(File file:prevFiles) {
				System.out.println(f.getName());
				
			
		}		
	}
	
	@Override
	public void preview(File f,boolean directoriesOnly) {
		File [] prevFiles=f.listFiles();
		System.out.println("Trazeni fajlovi:");
		if (directoriesOnly==true) {
			for(File file:prevFiles) {
				if(f.isDirectory()) {
					System.out.println(f.getName());
					
				}
			}
		}else {
			for(File file:prevFiles) {
				if(f.isDirectory()==false) {
					System.out.println(f.getName());
					
				}
			}
			
		}
	
	}

}