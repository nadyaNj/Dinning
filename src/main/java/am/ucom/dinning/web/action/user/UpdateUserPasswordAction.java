package am.ucom.dinning.web.action.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.UserService;
import am.ucom.dinning.service.impl.UserServiceImpl;
import am.ucom.dinning.util.Constants;

/**
 * extends BaseAction
 *
 * @author arthur
 */
public class UpdateUserPasswordAction extends UserAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        String returnPage = null;
        String password = request.getParameter("newPassword");
        String confPassword = request.getParameter("confirmPassword");
        Long userId = Long.parseLong(request.getParameter("userId"));
        String error = isPassword(password, confPassword);
        if (error != null) {
            request.setAttribute("error", error);
            returnPage = "/pages/userPage.jsp";
        } else {
            UserService userService = new UserServiceImpl();
            userService.updateUserPassword(password, userId, Constants.USER_STATE_ACTIVE);
            returnPage = "/pages/current_menu.jsp";
            showTodayMenu(request);
        }
        return returnPage;
    }

    /**
     * @param password
     * @param confPassword
     * @return
     */
    private String isPassword(String password, String confPassword) {
        if (password.length() < 6 || password.trim().equals("")) {
            return "your password should have min 6 character and didn't have only sapces";
        }
        if (!password.equals(confPassword)) {
            return "passwords didn't match";
        }
        Pattern pattern = Pattern.compile("^[A-z]+$");
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            return "your password should have minimum 1 number";
        }
        pattern = Pattern.compile("^[0-9]+$");
        matcher = pattern.matcher(password);
        if (matcher.matches()) {
            return "your password should have minimum 1 letter ";
        }
        return null;
    }
}
