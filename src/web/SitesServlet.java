package web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.SitesDAO;
import dao.SitesInMemoryDAO;
import entity.Sites;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(value = "/sitesServlet")
public class SitesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    SitesInMemoryDAO dao = new SitesInMemoryDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        String action = request.getParameter("action");
        int lId = (int) request.getSession().getAttribute("lId");
        if (action.equals("liastAll")) {  //查询当前所有收货地址信息
            String result = "[";
            for (Sites s : dao.listAlls(lId)) {
                result += toJson(new Sites(s.getsId(),s.getlId(),s.getsName(),s.getsPhone(),s.getsLocation()));
                result += ",";
            }
            if (result.substring(result.length() - 1, result.length()).equals(",")) {
                result = result.substring(0, result.length() - 1);
            }
            result += "]";
            response.getWriter().println(result);
          //  response.getWriter().print(toJson(new SitesInMemoryDAO().listAll(lId)));
        } else if (action.equals("add")) {
            int lIds = lId;
            String sName = request.getParameter("sName");
            String sPhone = request.getParameter("sPhone");
            String sLocation = request.getParameter("sLocation");
            if (dao.add(new Sites(lIds, sName, sPhone, sLocation)) > 0) {
                response.getWriter().print("{\"msg\":\"新增地址成功！\"}");
            } else {
                response.getWriter().print("{\"msg\":\"操作失败！\"}");
            }
        }
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
