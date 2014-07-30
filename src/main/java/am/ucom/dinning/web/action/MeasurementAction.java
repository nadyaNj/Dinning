package am.ucom.dinning.web.action;

import am.ucom.dinning.service.MeasurementService;
import am.ucom.dinning.service.impl.MeasurementServiceImpl;
import am.ucom.dinning.web.model.MeasurementBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class for insert measurement.
 *
 * @author arthur
 */
public class MeasurementAction extends BaseAction {
    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int measurementCount;
        Integer pageNumber = 0;//= Integer.parseInt(request.getParameter("pagenumber"));
        String pageNumberReq = request.getParameter("typePageNumber");
        if (pageNumberReq != null) {
            pageNumber = Integer.parseInt(pageNumberReq);
        }
        MeasurementService mesService = new MeasurementServiceImpl();
        measurementCount = mesService.getMeasurementCount();
        List<MeasurementBean> measurement = mesService.getMeasurementBeanListbyPage(pageNumber);
        request.setAttribute("measurement", measurement);
        int count;
        if ((double) measurementCount % 10 != 0) {
            count = (measurementCount / 10) + 1;
        } else {
            count = measurementCount / 10;
        }
        request.setAttribute("count", count);
        request.setAttribute("measurementCount", measurementCount);

        return "pages/measurement.jsp";
    }

}
