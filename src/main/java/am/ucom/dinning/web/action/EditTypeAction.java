package am.ucom.dinning.web.action;

import am.ucom.dinning.service.ProductTypeService;
import am.ucom.dinning.service.impl.ProductTypeServiceImpl;
import am.ucom.dinning.util.Constants;
import am.ucom.dinning.web.model.ProductTypeBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditTypeAction extends BaseAction {
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        ProductTypeService productTypeService = new ProductTypeServiceImpl();
        ProductTypeBean productTypeBean = productTypeService.getTypeById(Long.parseLong(request.getParameter(Constants.EDIT_TYPE_ID_FLAG)));

        request.setAttribute("editType", productTypeBean);
        return "/pages/editType.jsp";
    }

}
