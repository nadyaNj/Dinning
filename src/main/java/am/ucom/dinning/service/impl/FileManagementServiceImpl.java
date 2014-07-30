package am.ucom.dinning.service.impl;

import am.ucom.dinning.service.FileManagementService;

import java.io.File;
import java.io.IOException;


/**
 * extends FileManagementService
 *
 * @author arthur
 */
public class FileManagementServiceImpl implements FileManagementService {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.service.FileManagementService#deleteFile(java.lang.String)
      */
    @Override
    public boolean deleteFile(String path) {
        File file = (new File(path));
        boolean bool = false;
        try {
        	if(delete(file)) {
        		bool = true;
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bool;
    }

    /**
     * method for delete picture from path
     *
     * @param file
     * @throws IOException
     */
    private boolean delete(File file) throws IOException {
    	boolean bool = false;
        if (file.isDirectory()) {
            //directory is empty, then delete it
            if (file.list().length == 0) {
                file.delete();
                bool = true;
            } else {
                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                    bool = true;
                }

                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                    bool = true;
                }
            }

        } else {
            //if file, then delete it
            file.delete();
            bool = true;
        }
        
        return bool;
    }

   /*
    * (non-Javadoc)
    * @see am.ucom.dinning.service.FileManagementService#renameFile(java.lang.String, java.lang.String)
    */
    public boolean renameFile(String oldaname, String newaname) {
    	boolean bool = true;
        // File (or directory) with old name
        File file = new File(oldaname);

        // File (or directory) with new name
        File file2 = new File(newaname);

        // Rename file (or directory)
        boolean success = file.renameTo(file2);
        if (!success) {
        	bool = false;
        }
        return bool;
    }
}


