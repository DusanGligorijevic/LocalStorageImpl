package localStorageImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import storage.Permissions;
import storage.Storage;
import storage.StorageManager;
import storage.User;
import localStorageImpl.*;

public class LocalStorageImpl extends Storage{
	
	static {
		StorageManager.registerStorage(new LocalStorageImpl());
		
	}
	
	public LocalStorageImpl() {
		
	}

	@Override
	public void createFiles(String path, String name, int maxFolders) {

		for(int i = 0; i < maxFolders; i++) {
			
			//Instantiate the File class   
            File storage = new File(StoragePath+"\\"+i+name);  
            //Creating a folder using mkdir() method  
            boolean bool = storage.mkdir();  
            if(bool){  
               System.out.println("folder "+i+name+" je uspesno kreiran!");  
            }else{  
               System.out.println("Greska pri kreiranju foldera " + name + ". Folder sa ovim imenom vec postoji!");  
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
		File target = new File(location);
			if(target.exists())	{
					File dest = new File(destination);
					try {
						Files.copy(target.toPath(), dest.toPath(),StandardCopyOption.REPLACE_EXISTING);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else
					System.out.println("Ne postoji folder ili fajl!");	
	}
	@Override
	public void previewExt(String path, String extension) {
		File f = new File(path);
		File [] prevFiles=f.listFiles();
		System.out.println("Trazeni fajlovi:");
		for(File file:prevFiles) {
			if(file.getName().contains(extension)) {
				System.out.println(file.getName());
			}
		}
		}
	
	@Override
	public void previewAll(String path)  {
		File f = new File(path);
		File [] prevFiles=f.listFiles();
		System.out.println("Trazeni fajlovi:");
		for(File file:prevFiles) {
				System.out.println(file.getName());
				
			
		}		
	}
	
	@Override
	public void previewDir(String path) {
		File f = new File(path);
		File [] prevFiles=f.listFiles();
		System.out.println("Trazeni fajlovi:");
			for(File file:prevFiles) {
				if(file.isDirectory()) {
					System.out.println(file.getName());	
				}
			}	
	}


	@Override
	public void download(String path) {
		File f = new File("C:\\Users\\38160\\git\\SK-FileStorage\\Storage_sk\\Storage\\src\\download\\b");
		File f1 = new File(path);
		if(f1.exists()) {
			try {
				Files.copy(f1.toPath(), f.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void createFolders(String path,String name, String number) {
		for(int i=0; i<Integer.parseInt(number); i++) {
		//Instantiate the File class   
        File storage = new File(path+name+i);  
        //Creating a folder using mkdir() method  
        boolean bool = storage.mkdir();  
        if(bool){  
           System.out.println("Folder je uspesno kreiran!");  
           
        }else{  
           System.out.println("Greska pri kreiranju Foldera!");  
        }  
        }
}

	@Override
	public void initialise(User user, String storagePath) {

    	System.out.println("Korisnik" + user.getUsername() + "  pokusava da kreira skladiste...");
    	
    	if(user.getPrivileges().get(Permissions.create)) {
    		
    		//Instantiate the File class   
            File storage = new File(storagePath);  
            
            //Creating a folder using mkdir() method  
            boolean bool = storage.mkdir();  
            if(bool){  
               System.out.println("Skladiste je uspesno kreirano!");  
               StoragePath=storagePath;
               createConfigFile(user);
            }else{  
               System.out.println("Greska pri kreiranju skladista!");  
            }  
    	}else
    	System.out.println("Korisnik nema dozvolu da kreira skladiste!");
    	this.setConnectedUser(user);
    	addUser(user);
    	System.out.println("Uspesno logovanje!");
  	
	}

		
}