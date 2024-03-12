package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import utils.DataHelper;

public class Books {
	private static final String SELECT_BY_ID = "select id,name,price from tbl_book where id =?";
	private static final String SELECT_ALL = "select * from tbl_book";
	private static final String DELETE = "delete from tbl_book where id = ?;";
	private static final String UPDATE = "update tbl_book set name = ?,price= ? where id = ?;";
	private static final String INSERT = "insert into tbl_book(id,name,price) value (?, ?, ?) ;";
	
	public static Book getBook(String id) {
		try {
			Connection conn = DataHelper.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_BY_ID);
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				double price =  rs.getDouble("price");
				return new Book(id,name,price);
			}

			}catch (Exception e) {
				
				e.printStackTrace();
		}
		return new Book();
	}
	
	public static List<Book> getAll() {
		List<Book> books = new ArrayList<>();
		try {
			Connection conn = DataHelper.getConnection();
			Statement stmt = null;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				double price =  rs.getDouble("price");
				
				books.add(new Book(id, name, price));
			}

			}catch (Exception e) {
				e.printStackTrace();
		}
		return books;
	}
	public static boolean create(Book book) {
		try {
			Connection conn = DataHelper.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(INSERT);
			preparedStatement.setString(1, book.getId());
			preparedStatement.setString(2, book.getName());
			preparedStatement.setDouble(3, book.getPrice());
			String sql = preparedStatement.toString();
			return preparedStatement.execute();
			
			}catch (Exception e) {
				
				e.printStackTrace();
				return false;
		}
		
	}
	public static boolean update(Book book) {
		
		try {
			Connection conn = DataHelper.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(UPDATE);
			preparedStatement.setString(1, book.getName());
			preparedStatement.setDouble(2, book.getPrice());
			preparedStatement.setString(3, book.getId());
			String sql = preparedStatement.toString();
			return preparedStatement.execute();
			
			}catch (Exception e) {
				
				e.printStackTrace();
				return false;
		}
	}
	public static boolean delete(Book book) {
		
		return true;
	}
	public static boolean save(Book book) {
		try {
			if( isExist(book))
				return update(book);
			else
				return create(book);
		}catch (Exception e) {
			return false;
		} 
		
	}
	public static boolean isExist(Book book) {
		try {
			Connection conn = DataHelper.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_BY_ID);
			preparedStatement.setString(1, book.getId());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				return true;
			}

			}catch (Exception e) {
				e.printStackTrace();
		}
		return false;
	}
}
