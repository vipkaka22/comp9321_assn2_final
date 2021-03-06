package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.bean.Buyer;
import assign2.bean.Item;
import assign2.dao.AdminDAO;
import assign2.dao.SearchDAO;
import assign2.exception.Comp9321Assign2Exception;

public class SearchBuyerCommand extends GeneralCommand {

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("searchContent");
		AdminDAO search;
		try {
			search = new AdminDAO();
		
		ArrayList<Buyer> buyerList;
		
		if (null == name || name.equals("")) {
			String e = "Nothing is searched";
			session.setAttribute("errMsg", e);
			return "adminError.jsp";
		}
		 
		buyerList = search.searchBuyer(name);
		if (null == buyerList || buyerList.size() == 0) {
			String e = "No result!";
			session.setAttribute("errMsg", e);
			return "adminError.jsp";
		}
		
		session.setAttribute("buyerList", buyerList);
		//System.out.println(resultList.size());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "adminBuyerList.jsp";
	}

}
