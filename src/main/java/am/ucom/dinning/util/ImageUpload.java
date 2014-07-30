package am.ucom.dinning.util;

import am.ucom.dinning.ServiceProperties;
import am.ucom.dinning.web.model.ProductsBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * method for image upload
 *
 * @author nadya
 */
public class ImageUpload {

    /**
     * private constructor...
     */
    private ImageUpload() {

    }

    /**
     * method which help for uploading image and product Bean
     *
     * @param request
     * @return String
     */
    public static String getPageFlag(HttpServletRequest request) {
        //we get request page_flag

        ProductsBean createProduct = new ProductsBean();
        String pageFlag = null;
        String directoy = ServiceProperties.getImageSavePath();
        if (directoy.endsWith(File.separator)) {
            directoy = directoy.substring(0, directoy.length() - 1);
        }
        String finalImage = null;

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List items = new ArrayList<String>();
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        List<String> types = new ArrayList<String>();
        for (Object item1 : items) {
            FileItem item = (FileItem) item1;
            if (item.isFormField()) {
                String name = item.getFieldName();
                String value = item.getString();

                //getting page flag
                if (name.equals(Constants.PAGE_FLAG)) {
                    pageFlag = value;
                }

                //getting product id
                if (name.equals("id")) {
                    try {
                        if (value != null && value.length() > 0) {
                            createProduct.setId(Long.parseLong(value));
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                //getting product name
                if (name.equals("name")) {
                    finalImage = Encode.encodeString(value).trim();
                    createProduct.setName(Encode.encodeString(value).trim());
                }

                //getting product price
                if (name.equals("price")) {
                    createProduct.setPrice(Encode.encodeString(value).trim());
                }

                //getting product description
                if (name.equals("description")) {
                    createProduct.setDescription(Encode.encodeString(value).trim());
                }

                //getting product code
                if (name.equals("code")) {
                    createProduct.setCode(Encode.encodeString(value).trim());
                }

                //getting product measurement
                if (name.equals("measurement")) {
                    createProduct.setMeasurementId(Encode.encodeString(value));
                }

                //getting product hidden
                if (name.equals("hiddenTrue")) {
                    createProduct.setHidden(true);
                } else if (name.equals("hiddenFalse")) {
                    createProduct.setHidden(false);
                }

                //getting product types
                if (name.equals("types")) {
                    types.add(Encode.encodeString(value));
                    createProduct.setProductTypes(initListToStringArray(types));
                }

                // getting img url path
                if (name.equals("picturePath")) {
                    createProduct.setImgUrl(Encode.encodeString(value));
                }
                
                // getting discount price
                if (name.equals("discount")) {
                    createProduct.setDiscountPrice(Encode.encodeString(value));
                }

            }

            //getting product image url from web
            if (!item.isFormField()) {
                (new File(directoy)).mkdir();
                try {
                    String itemName = item.getName();
                    itemName = itemName.toLowerCase();

                    String reg = "[.*]";
                    String replacingtext = "";

                    Pattern pattern = Pattern.compile(reg);
                    Matcher matcher = pattern.matcher(itemName);
                    StringBuffer buffer = new StringBuffer();
                    while (matcher.find()) {
                        matcher.appendReplacement(buffer, replacingtext);
                    }
                    int indexOf = itemName.indexOf(".");
                    String domainName = null;
                    if (indexOf > 0) {
                        domainName = itemName.substring(indexOf);
                    }

                    String mimeType = request.getSession().getServletContext().getMimeType(itemName);

                    //check is input file imge or not
                    if (mimeType != null && mimeType.startsWith("image")) {
                        finalImage += domainName;

                        File savedFile = new File(directoy + File.separator + finalImage);

                        item.write(savedFile);
                    } else if (domainName == null) {
                        finalImage = "noImage.jpg";
                    } else {
                        finalImage = "NonImage";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        createProduct.setImgUrl(finalImage);

        HttpSession session = request.getSession();
        if (Constants.CREATE_PAGE_FLAG.equals(pageFlag)) {
            session.setAttribute("save", null);
        }
        request.setAttribute("imageName", finalImage);
        createProduct.setImgUrl(finalImage);
        request.setAttribute("createProduct", createProduct);

        return pageFlag;
    }

    /**
     * change List of types to string[] of types
     *
     * @param types
     * @return String[]
     */
    private static String[] initListToStringArray(List<String> types) {
        String[] stringArray = new String[types.size()];
        for (int i = 0; i < types.size(); i++) {
            stringArray[i] = types.get(i);
        }
        return stringArray;
    }
}
