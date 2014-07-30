package am.ucom.dinning.service;

public interface FileManagementService {
    /**
     * method for deleteing image from path
     *
     * @param path
     */
    boolean deleteFile(String path);

    /**
     * method for renameing image with product name
     *
     * @param oldaname
     * @param newaname
     */
    boolean renameFile(String oldaname, String newaname);
}
