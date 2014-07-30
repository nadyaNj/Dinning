package am.ucom.dinning.web.action;

import am.ucom.dinning.service.MeasurementService;
import am.ucom.dinning.service.impl.MeasurementServiceImpl;
import am.ucom.dinning.util.StringUtil;
import am.ucom.dinning.web.model.MeasurementBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Edit measurement action class
 *
 * @author aram
 */
public class MeasurementEditAction extends BaseAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        MeasurementBean measurementBean = new MeasurementBean(request);
        MeasurementService mesService = new MeasurementServiceImpl();
        if (!StringUtil.isEmptyString(measurementBean.getMeasurement()) &&
                isNameUnique(measurementBean.getMeasurement(), mesService.measurementNames(null))) {
            mesService.saveMeasurement(measurementBean);
        } else {
            request.setAttribute("error", "Invalid measure OR measure exists");
            request.setAttribute("mesName", measurementBean);
        }
        int measurementCount = 0;
        Integer pageNumber = 0;//= Integer.parseInt(request.getParameter("pagenumber"));
        String pageNumberReq = request.getParameter("mespageNumber");
        if (pageNumberReq != null) {
            pageNumber = Integer.parseInt(pageNumberReq);
        }

        measurementCount = mesService.getMeasurementCount();
        List<MeasurementBean> measurement = mesService.getMeasurementBeanListbyPage(pageNumber);
        request.setAttribute("measurement", measurement);
        int count = 0;
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
