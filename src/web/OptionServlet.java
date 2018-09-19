package web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.OptionInMemoryDAO;
import entity.Option;
import entity.POtion;
import org.apache.catalina.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@WebServlet(value = "/OptionServlet")
public class OptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    OptionInMemoryDAO dao = new OptionInMemoryDAO();
    PrintWriter out;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
        out = response.getWriter();
        String action = request.getParameter("action");
        if (action.equals("Optionlist")) {
            String result = "[";
            for (POtion o : dao.listAll()) {
                result += toJson(new POtion(o.getoId(),o.getpPhoto(),o.getpName(),o.getpPrice(),o.getBuy_num(),o.getoCount()));
                result += ",";
            }
            if (result.substring(result.length() - 1, result.length()).equals(",")) {
                result = result.substring(0, result.length() - 1);
            }
            result += "]";
            response.getWriter().println(result);
        } else if (action.equals("addOption")) {
            addOption(request, response);
        }
        else if(action.equals("price_num")){   //计算价格总和的数据
            String term = request.getParameter("condition");
            int lId = (int) request.getSession().getAttribute("lId");
            response.getWriter().print(toJson(dao.num_price(term,lId)));
        }
        else if(action.equals("updOption")){
            updOption(request,response);
        }
        else if(action.equals("getNum")){   //获得购物车数量
            int lId = (int) request.getSession().getAttribute("lId");
            response.getWriter().print("{\"num\":\""+dao.getCartCount(lId)+"\"}");
        }
        else if(action.equals("deleteOption")){
            deleteOption(request,response);
        }
    }

    public void addOption(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int pId = Integer.parseInt(request.getParameter("pId"));
        int lId = (int) request.getSession().getAttribute("lId");
        int Buy_num = Integer.parseInt(request.getParameter("num"));
        System.out.println(pId + "," + lId + "," + Buy_num );
       if(dao.addoption(new POtion(pId,lId,Buy_num))) {
           response.getWriter().print("{\"msg\":\"添加成功\"}");
       }
    }
    public void updOption(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int oId = Integer.parseInt(request.getParameter("oId"));
        int buy_num = Integer.parseInt(request.getParameter("buy_num"));
        if(dao.update(oId,buy_num)){
            response.getWriter().print("{\"msg\":\"0\"}");
        }else{
            response.getWriter().print("{\"msg\":\"1\"}");
        }
    }
    public void deleteOption(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int oId = Integer.parseInt(request.getParameter("oId"));
       if( dao.delete(oId)){
           response.getWriter().print("{\"msg\":\"删除成功\"}");
       }
       else {
           response.getWriter().print("{\"msg\":\"删除失败\"}");
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
