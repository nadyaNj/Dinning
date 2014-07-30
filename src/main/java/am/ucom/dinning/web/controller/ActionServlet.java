/*
 * ActionServlet		1.0 10/20/11 12:06 PM
 *
 * Copyright (c) UPay.
 *
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of UPay. 
 * ("Confidential Information").  You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with UPay.
 */
package am.ucom.dinning.web.controller;

import java.io.IOException;
import java.util.HashMap;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import am.ucom.dinning.util.*;
import am.ucom.dinning.web.action.*;


import am.ucom.dinning.web.action.cashier.GetByCodeAction;
import am.ucom.dinning.web.action.cashier.SaveEmployeeBasketAction;
import am.ucom.dinning.web.action.cashier.SaveSharedBasketAction;
import am.ucom.dinning.web.action.cashier.SearchByTypeAction;
import am.ucom.dinning.web.action.cashier.ShowTodayManuCashierAction;
import am.ucom.dinning.web.action.product.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import am.ucom.dinning.web.action.user.*;


/**
 * Class description here.
 *
 * @author Artur Yolchyan
 * @version 1.0 10/20/11 12:06 PM
 */
public class ActionServlet extends HttpServlet {

    /**
     * Default UUID for serialization.
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ActionServlet.class);

    private static Map<String, BaseAction> actions = new HashMap<String, BaseAction>();

    static {
        LOG.info("STARTING ASOCIATION OF ACTION'S CONSTANTS MAP");

        // product actions
        actions.put(Constants.SHOW_WITH_PAGE_NUMBER, new ShowWithNumberProductAction());
        actions.put(Constants.SEARCH_PAGE_FLAG, new SearchProductAction());
        actions.put(Constants.UPDATE_PAGE_FLAG, new UpdateProductAction());
        actions.put(Constants.DELETE_PAGE_FLAG, new DeleteProductAction());
        actions.put(Constants.CREATE_PAGE_FLAG, new CreateProductAction());
        actions.put(Constants.SHOW_CREATE_PAGE, new ShowProductAction());
        actions.put(Constants.HIDE_PRODUCT_FLAG, new HideProductAction());
        actions.put(Constants.EDIT_PRODUCT_RESET_FLAG, new EditProductAction());
        actions.put(Constants.EDIT_PAGE_FLAG, new EditProductAction());
        actions.put(Constants.DELETE_ALL_PAGE_FLAG, new DeleteAllProductAction());
        actions.put(Constants.WELCOME_PAGE_FLAG, new ShowProductAction());


        // menu actions
        actions.put(Constants.MENU_VIEW_FLAG, new MenuViewAction());
        actions.put(Constants.MENU_UPDATE_FLAG, new MenuUpdateAction());
        actions.put(Constants.MENU_EDIT_PAGE, new MenuEditAction());
        actions.put(Constants.MENU_LIST_PAGING, new MenuListPageAction());
        actions.put(Constants.MENU_LIST_PAGE_FLAG, new MenuListPageAction());
        actions.put(Constants.MENU_SEARCH_PAGING, new MenuPagingAction());
        actions.put(Constants.ADD_ALL_TO_MENU, new AddSelectedToMenuAction());
        actions.put(Constants.CANCEL_MENU_SAVE_FLAG, new MenuCancelAction());
        actions.put(Constants.SAVE_MENU_FLAG, new MenuSaveAction());
        actions.put(Constants.CREATE_MENU_FLAG, new CreateMenuAction());
        actions.put(Constants.ADD_TO_MENU, new AddToMenuAction());
        actions.put(Constants.CREATE_MENU_SEARCH, new MenuProductSearchAction());
        actions.put(Constants.CREATE_MENU_PAGE_VIEW, new MenuAction());


        actions.put(Constants.LOGIN_PAGE_FLAG, new LoginAction());

        actions.put(Constants.EDIT_MEAS_RESET_FLAG, new MeasurementEditAction());
        actions.put(Constants.UPDATE_TYPE_PAGE_FLAG, new UpdateTypeAction());
        actions.put(Constants.CREATE_PRODUCT_TYPE, new CreateTypeAction());
        actions.put(Constants.SHOW_TYPE_PAGE, new ShowTypePageAction());
        actions.put(Constants.EDIT_MEASUREMENT_ID_FLAG, new MeasurementUpdateAction());
        actions.put(Constants.MEASUREMENT_PAGE_FLAG, new MeasurementAction());
        actions.put(Constants.GO_MEASUREMENT_PAGE, new MeasurementAction());
        actions.put(Constants.EDIT_MEASUREMENT_PAGE_FLAG, new MeasurementUpdateAction());

        actions.put(Constants.DELETE_TYPE_ID, new DeleteTypeAction());
        actions.put(Constants.GET_MEASUREMENT_ID, new DeleteMeasurementAction());
        actions.put(Constants.CREATE_USER, new HrCreateUserAction());
        actions.put(Constants.PRINT_USER_PAGE, new HrCreateUserAction());
        actions.put(Constants.DELETE_USER, new DeleteUserAction());
        actions.put(Constants.UPDATE_USER, new UpdateUserAction());
        actions.put(Constants.SETPASSWORD_USER, new ChangeUserPasswordAction());
        actions.put(Constants.SAVEPASSWORD_USER, new ChangeUserPasswordAction());
        actions.put(Constants.SHOW_USER_PAGE, new ShowUserAction());

		actions.put(Constants.UPDATE_USER_PASSWORD, new UpdateUserPasswordAction());
        actions.put(Constants.MANAGE_COMPANY_NAME, new WelcomeAction());
        actions.put(Constants.CREATE_COMPANY_NAME,new ManageCompanyAction());
        actions.put(Constants.DELETE_COMPANY_NAME,new DeleteCompanyNameAction());
        actions.put(Constants.UPDATE_COMPANY_NAME,new UpdateCompanyNameAction());
        actions.put(Constants.MANAGE_DEPARTAMENT, new WelcomeAction());
        actions.put(Constants.CREATE_DEPARTAMENT, new ManageDepartamentAction());
        actions.put(Constants.DELETE_DEPARTAMENT, new DeleteDepartamentAction());
        actions.put(Constants.UPDATE_DEPARTAMENT, new UpdateDepartamentAction());
		actions.put(Constants.TODAYS_MENU, new MenuTodayAction() );

        actions.put(Constants.LOGOUT_PAGE_FLAG, new LogoutAction());

        // user actions
        actions.put(Constants.SHOW_CHANGE_USER_ROLE_PAGE, new ViewRolesUser());
        actions.put(Constants.CREATE_USER_FALG, new CreateNewUser());
        actions.put(Constants.SHOW_DEPARTAMENT_BY_COMPANY, new ShowCompanyByDepartamentUser());
        actions.put(Constants.SHOW_USERS_WITH_PAGE_NUMBER_FLAG, new ShowUser());
        actions.put(Constants.DELETE_USER_PAGE_FLAG, new ChangeStatusUser());
        actions.put(Constants.EDIT_USER_PAGE_FLAG, new EditUser());
        actions.put(Constants.UPDATE_USER_PAGE_FLAG, new UpdateUser());
        actions.put(Constants.DELETE_USER_GLAG, new DeleteUser());
        actions.put(Constants.SET_NEW_USER_PASSWORD, new SetNewPassworUser());
        actions.put(Constants.HIDE_PRODUCT_ID_FLAG, new HideProductAction());
        actions.put(Constants.MENU_VIEW, new LoginAction());
        
        
        // cashier actions
        actions.put(Constants.SEARCH_PRODUCT_BY_TYPE, new SearchByTypeAction());
        actions.put(Constants.SHOW_TODAY_MENU_CASHIER_PAGE, new ShowTodayManuCashierAction());
        actions.put(Constants.ADD_PRODUCT_BY_CODE, new GetByCodeAction());
        actions.put(Constants.SAVE_SHARED_BASKET, new SaveSharedBasketAction());
        actions.put(Constants.SAVE_EMPLOYEE_BASKET, new SaveEmployeeBasketAction());
        
        //surplus menu actions
        actions.put(Constants.SURPLUS_MENU, new SurplasMenuAction());
        actions.put(Constants.SURPLUS_MENU_LIST, new CreateSurplusMenuAction());
        
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String pageFlag = request.getParameter(Constants.PAGE_FLAG);
    	if(pageFlag == null) {
            pageFlag = ImageUpload.getPageFlag(request);
            session.setAttribute("flag", null);
        }
    	if(session.getAttribute("flag") != null) {
            pageFlag = (String) session.getAttribute("flag");
            session.setAttribute("flag", null);
        }

    	if(request.getParameter("searchFlag") == null ||
                !request.getParameter("searchFlag").equals(Constants.SEARCH_PAGE_FLAG)) {
            session.setAttribute("beanList", null);
        }
        BaseAction currentAction = actions.get(pageFlag);
        String redirectToPage = currentAction.execute(request, response);

        RequestDispatcher dispatcher = request.getRequestDispatcher(redirectToPage);
        dispatcher.forward(request, response);
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String pageFlag = request.getParameter(Constants.PAGE_FLAG);
    	if( session.getAttribute("flag") != null) {
            pageFlag = (String) session.getAttribute("flag");
            session.setAttribute("flag", null);
        }
        BaseAction currentAction = actions.get(pageFlag);
            String redirectToPage = currentAction.execute(request, response);
    	//Navigate the pages 
            RequestDispatcher dispatcher = request.getRequestDispatcher(redirectToPage);
            dispatcher.forward(request, response);
        }

}