package am.ucom.dinning.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringUtil {

    private static StringUtil instance = null;

    private StringUtil() {
    }

    public static StringUtil getInstance() {
        if (instance == null) {
            instance = new StringUtil();
        }

        return instance;
    }

    public static boolean isEmptyString(final String value) {
        boolean empty = false;

        if (value == null) {
            empty = true;
        } else if (value.isEmpty()) {
            empty = true;
        }

        return empty;
    }
    
    /**
     * This method convert delete product ids array to array list .
     * @param delProductIds
     * @return List<String> - deleteAllByIds
     */
    public static List<String> arrayToArrayList(String[] delProductIds) {
    	List<String> deleteAllByIds = null;
    	if(delProductIds != null){
    		deleteAllByIds = new ArrayList<String>();
    		StringBuilder ides = new StringBuilder();
    		for(int a = 0; a < delProductIds.length; a++ ) {
    			ides.append(delProductIds[a]); 
    		}
    		StringTokenizer st = new StringTokenizer(ides.toString(),",");
    		while (st.hasMoreTokens()){
    			 String token = st.nextToken().trim();
    			 deleteAllByIds.add(token);
    		}
	    }
	    return deleteAllByIds;
	}
}
