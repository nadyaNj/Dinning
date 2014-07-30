package am.ucom.dinning.web.controller;

import am.ucom.dinning.service.UserService;
import am.ucom.dinning.service.impl.UserServiceImpl;
import am.ucom.dinning.util.Constants;
import am.ucom.dinning.util.SecurityUtil;
import am.ucom.dinning.util.StringUtil;
import am.ucom.dinning.web.action.BaseAction;
import am.ucom.dinning.web.action.MenuTodayAction;
import am.ucom.dinning.web.model.UserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.sasl.AuthenticationException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Login filter class for filtering every input to site/project
 *
 * @author nadya
 */
public class LoginFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(LoginFilter.class);

    protected FilterConfig filterConfig;

    public Map<String, String> errors;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        errors = new HashMap<String, String>();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String pageFlag = request.getParameter(Constants.PAGE_FLAG);
        UserBean user = (UserBean) request.getSession().getAttribute("user");

        if (pageFlag == null && user == null) {

            BaseAction currentAction = new MenuTodayAction();
            currentAction.execute(request, response);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/current_menu.jsp");
            dispatcher.forward(request, response);
        } else {
            if (user == null) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                if (!StringUtil.isEmptyString(username) && !StringUtil.isEmptyString(password)) {
                    UserService userService = new UserServiceImpl();
                    try {
                        if (!"admin".equals(username)) {
                            password = SecurityUtil.encryptString(password);
                        }
                        user = userService.authenticateUser(username, password);
                        request.getSession().setAttribute("user", user);
                        handleActionByUserState(user.getStateId(), request, response);
                    } catch (NoSuchAlgorithmException e) {
                        LOG.error("Encryption algorithm missing: " + e.getMessage(), e);
                    } catch (AuthenticationException ex) {
                        errors.put("loginError", ex.getMessage());
                        request.setAttribute("errors", errors);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
                        dispatcher.forward(request, response);
                    }
                } else if (StringUtil.isEmptyString(username) && !StringUtil.isEmptyString(password)) {
                    errors.put("loginError", "Username is required field");
                    request.setAttribute("errors", errors);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
                    dispatcher.forward(request, response);
                } else if (StringUtil.isEmptyString(password) && !StringUtil.isEmptyString(username)) {
                    errors.put("loginError", "Password is required field");
                    request.setAttribute("errors", errors);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
                    dispatcher.forward(request, response);
                } else {
                    if (!Constants.AUTH_PAGE_FLAG.equals(pageFlag)) {
                        errors.put("loginError", "Username and Password are required fields");
                        request.setAttribute("errors", errors);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
                        dispatcher.forward(request, response);
                    }
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    private void handleActionByUserState(final int state, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        switch (state) {
            case 1:
                BaseAction currentAction = new MenuTodayAction();
                currentAction.execute(request, response);
                dispatchPage("/pages/current_menu.jsp", request, response);
                break;
            case 2:
                dispatchPage("/pages/userPage.jsp", request, response);
                break;
            case 3: {
                errors.put("loginError", "Entered login/password is/are incorrect");
                request.setAttribute("errors", errors);
                dispatchPage("/pages/login.jsp", request, response);
                break;
            }
            default:
                dispatchPage("/pages/current_menu.jsp", request, response);
                break;

        }
    }

    private void dispatchPage(final String pageUrl, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(pageUrl);
        dispatcher.forward(request, response);
    }
}