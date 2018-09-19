package web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ProductInMemoryDAO;
import entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@WebServlet(value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    ProductInMemoryDAO dao=new ProductInMemoryDAO();
    PrintWriter out;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
        out = response.getWriter();
        String action = request.getParameter("action");
        if (action.equals("list")) {
            String result = "[";
            for (Product p : dao.listAll()) {
                result += toJson(new Product(p.getpId(),p.getpName(),p.gettId(),p.getpPrice(),p.getpPhoto(),p.getpRemark(),p.getpState()));
                result += ",";
            }
            if (result.substring(result.length() - 1, result.length()).equals(",")) {
                result = result.substring(0, result.length() - 1);
            }
            result += "]";
            response.getWriter().println(result);
        }
        else if(action.equals("tlist")){
            int tId= Integer.parseInt(request.getParameter("tId"));
            String result = "[";
            for (Product p : dao.getProductById(tId)) {
                result += toJson(new Product(p.getpId(),p.getpName(),p.gettId(),p.getpPrice(),p.getpPhoto(),p.getpRemark(),p.getpState()));
                result += ",";
            }
            if (result.substring(result.length() - 1, result.length()).equals(",")) {
                result = result.substring(0, result.length() - 1);
            }
            result += "]";
            response.getWriter().println(result);
        }
        else if(action.equals("nlist")){
            String pName=request.getParameter("pName");
            System.out.println(pName);
            String result = "[";
            for (Product p : dao.getProductByName(pName)) {
                result += toJson(new Product(p.getpId(),p.getpName(),p.gettId(),p.getpPrice(),p.getpPhoto(),p.getpRemark(),p.getpState()));
                result += ",";
            }
            if (result.substring(result.length() - 1, result.length()).equals(",")) {
                result = result.substring(0, result.length() - 1);
            }
            result += "]";
            response.getWriter().println(result);
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
