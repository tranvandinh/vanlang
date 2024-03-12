package contronller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Book;

import java.io.IOException;
import java.util.List;

import dao.Books;

/**
 * Servlet implementation class BookController
 */
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		showBooks(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String button = request.getParameter("button");
		
		switch (button) {
			case "add":
				addBook(request, response);
				break;
			case "edit":
				editBook(request, response);
				break;
			case "delete":
				break;
			case "save":
				saveBook(request, response);
				showBooks(request, response);
				break;
			case "closebook":
				showBooks(request, response);
				break;
			case "close":
				System.out.print(button);
				response.sendRedirect("home");
				break;
			default:
				showBooks(request, response);
				break;
		}
	}
	
	private void showBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> books = Books.getAll();
		request.setAttribute("books", books);
		RequestDispatcher rd=request.getRequestDispatcher("/view/books.jsp");  
        rd.forward(request, response);
	}
	private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd=request.getRequestDispatcher("/view/book.jsp");  
        rd.forward(request, response);
	}
	private void editBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currID = request.getParameter("currBookID");
		
		request.setAttribute("currBookID", currID);
		
		RequestDispatcher rd=request.getRequestDispatcher("/view/book.jsp");  
        rd.forward(request, response);
	}
	private void saveBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id").toString();
		String name = request.getParameter("name").toString();
		double price = Double.parseDouble(request.getParameter("price").toString());
		Book book = new Book(id,name,price);
		
		Books.save(book);
	}
}
