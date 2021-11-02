package localStorageImpl;

import java.io.File;

import storage.Storage;
import storage.StorageManager;

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
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transfer(File f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preview(File f) {
		// TODO Auto-generated method stub
		
	}

}
