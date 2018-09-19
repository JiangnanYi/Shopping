package web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.LoginsInMemoryDAO;
import entity.Logins;
import org.apache.catalina.User;

import java.lang.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@WebServlet(value = "/LoginsServlet")
public class LoginsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    LoginsInMemoryDAO dao = new LoginsInMemoryDAO();
    PrintWriter out;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
        out = response.getWriter();
        String action = request.getParameter("action");
        if (action.equals("loginAll")) {
            String result = "[";
            for (Logins l : dao.listAll()) {
                result += toJson(new Logins(l.getlUser(), l.getlPwd(), l.getlPhone(), l.getlMailbox()));
                result += ",";
            }
            if (result.substring(result.length() - 1, result.length()).equals(",")) {
                result = result.substring(0, result.length() - 1);
            }
            result += "]";
            response.getWriter().println(result);
        } else if (action.equals("add")) {
            add(request, response);
        } else if (action.equals("login")) {
            String lUser = request.getParameter("lUser");
            String lPwd = request.getParameter("lPwd");
            int count= dao.logins(lUser, lPwd);
            if (count > 0) {
                request.getSession().setAttribute("lUser",lUser);
                request.getSession().setAttribute("lId",dao.lid(lUser, lPwd));
                response.getWriter().print("{\"msg\":\"登陆成功\",\"name\":\"" +lUser+ "\"}");

            } else
                response.getWriter().print("{\"msg\":\"账号或密码错误，请从新输入\"}");

        }else if(action.equals("gwc")){
            User user = (User) request.getSession().getAttribute("lUser");
            if(user!=null){
                response.getWriter().print("{\"code\":0}");
            }
            else
                response.getWriter().print("{\"code\":1}");
        }
        else if(action.equals("select")){
            User user = (User) request.getSession().getAttribute("lUser");
            if(user!=null){
                response.getWriter().print("{\"msg\":\""+user+"已登入\"}");
            }
            else
                response.getWriter().print("{\"msg\":1}");
        }
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String lUser = request.getParameter("lUser");
        String lPwd = request.getParameter("lPwd");
        String lPhone = request.getParameter("lPhone");
        String lMailbox = request.getParameter("lMailbox");
        dao.add(new Logins(lUser, lPwd, lPhone, lMailbox));
        response.getWriter().print("{\"msg\":\"注册成功\"}");
    }

    public static String toJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
        mapper.setDateFormat(sdf);

        String result = null;
        try {
            result = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
