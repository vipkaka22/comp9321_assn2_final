package assign2.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import assign2.bean.Item;
import assign2.dao.ConnectionFactory;

public class RandomTen {
	
	private Item getRandomItem(ResultSet rs) throws SQLException {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setTitle(rs.getString("title"));
		item.setAuthor(rs.getString("author"));
		item.setEditor(rs.getString("editor"));
		item.setYear(rs.getInt("year"));
		item.setType(rs.getString("type"));
		item.setJournal(rs.getString("journal"));
		item.setVolume(rs.getInt("volume"));
		item.setNumber(rs.getInt("number"));
		item.setPublisher(rs.getString("publisher"));
		item.setIsbn(rs.getString("isbn"));
		item.setPages(rs.getString("pages"));
		item.setPicture(rs.getString("picture"));
		item.setBooktitle(rs.getString("booktitle"));
		item.setPrice(rs.getFloat("price"));
		item.setPause(rs.getInt("pause"));
		return item;
	}
	
	public ArrayList<Item> randomTen() throws SQLException {
		Connection connection = ConnectionFactory.Connection();
		ArrayList<Item> list = new ArrayList<Item>();
		
		String sql = "Select * From item order By Rand() Limit 10;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		ResultSet rs = stmnt.executeQuery();
		while (rs.next()) {
			Item item = getRandomItem(rs);
			list.add(item);
		}
		connection.close();
		return list;
	}
}
