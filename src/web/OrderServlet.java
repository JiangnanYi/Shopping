package web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.OrderInMemoryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(value = "/OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        String action = request.getParameter("action");

        if(action.equals("storage")){
            String message = request.getParameter("message");  //保存到Session
            request.getSession().setAttribute("oId",message); //oId保存
            response.getWriter().print("{\"msg\":\"true\"}");
        }else if(action.equals("add")){   //添加一个订单
            //订单编号  地址 *修改购物车中购物项的状态（c_state 1） 购物车订单号的关联
            // String UUID.randomUUID(),int a_id,String c_id
            int sId = Integer.parseInt(request.getParameter("sId"));
            Object oId = request.getSession().getAttribute("oId");
            OrderInMemoryDAO add = new OrderInMemoryDAO();
            if(add.addOrderUpdCart(sId,oId)){  //成功
                response.getWriter().print("{\"msg\":\"我们会尽快将您的商品给您送过去，谢谢支持\"}");
            }else{
                response.getWriter().print("{\"msg\":\"订单提交失败！\"}");
            }

        }
        else if(action.equals("order")){  //请求要提交的订单信息
            String oIds = String.valueOf(request.getSession().getAttribute("oId"));
            response.getWriter().print(toJson(new OrderInMemoryDAO().listAll(oIds)));
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
