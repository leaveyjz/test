package cn.dw.servlet;

import java.awt.Window.Type;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import javax.xml.ws.Dispatch;

import org.apache.taglibs.standard.tag.el.fmt.RequestEncodingTag;

import cn.dw.model.Product;
import cn.dw.service.ProductService;
/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService ps = new ProductService();
	String keyword = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type"); 
		HttpSession session = request.getSession();
		if (type.equals("query")) {
			
			String keyword = request.getParameter("keyword");
			session.setAttribute("keyword", keyword);
			ArrayList<Product> prolist = ps.getByName(keyword);
			request.setAttribute("prolist", prolist);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/query.jsp");
		    dispatcher.forward(request, response);
		}
		else if(type.equals("insert")) {
			Product product = new Product();
			product.setName(request.getParameter("name"));
			product.setPrice(Double.parseDouble(request.getParameter("money")));
			product.setRemark(request.getParameter("remark"));
			ps.insert(product);
			response.sendRedirect(request.getContextPath() + "/query.jsp");
		}
		else if (type.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			ps.deleteById(id);
			String keyword = (String)session.getAttribute("keyword");
			ArrayList<Product> prolist= ps.getByName(keyword);
			request.setAttribute("prolist", prolist);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/query.jsp");
		    dispatcher.forward(request, response);
		}
		else if(type.equals("getById")) {
			ProductService ps = new ProductService();
			int id = Integer.parseInt(request.getParameter("id"));
			Product product = ps.getById(id);
			request.setAttribute("product",product);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update.jsp");	
			dispatcher.forward(request, response);
		}
		else if (type.equals("update")) {
			ProductService ps = new ProductService();
			Product product = new Product();
			product.setId(Integer.parseInt(request.getParameter("id")));
			product.setName(request.getParameter("name"));
			product.setPrice(Double.parseDouble(request.getParameter("money")));
			product.setRemark(request.getParameter("remark"));
		
			System.out.println(product);
			ps.update(product);
			response.sendRedirect(request.getContextPath() + "/query.jsp");
		  
		}
	}
}
