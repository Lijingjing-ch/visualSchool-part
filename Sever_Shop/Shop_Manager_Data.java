package Sever_Shop;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
//绠＄悊鍛樼殑鍚勭鍔熻兘锛岄噸澶嶅姛鑳借鍙傝�冨鐢熷姛鑳�
public class Shop_Manager_Data { 
	//鏌ョ湅搴撳瓨鍔熻兘鍙傝�冨鐢熺殑鍟嗗搧娴忚
	//璐拱璁板綍绠＄悊
	private String url = "jdbc:ucanaccess://D:/暑期学校-2/专业实训/Database2.accdb";
	public List<Shop_Buy> buymanage() {
	    List<Shop_Buy> buys = new ArrayList<>();
	    
	    try (Connection conn = DriverManager.getConnection(url);
	         Statement stmt = conn.createStatement()) {
	        String sql = "SELECT * FROM buy"; // 鏇挎崲涓轰綘鐨勮〃鍚�
	        ResultSet rs = stmt.executeQuery(sql);
	        while (rs.next()) {
	        	Shop_Buy comm = new Shop_Buy();
	            comm.setID(rs.getInt("id"));
	            comm.setBuycommodity(rs.getString("buycommodity"));
	            comm.setBuyamount(rs.getInt("buyamount"));
	            comm.setBuydate(rs.getString("buydate"));
	            comm.setBuymoney(rs.getString("buymoney"));
	            comm.setBuyer(rs.getString("buyer"));
	            buys.add(comm);
	        }
	        if (buys.isEmpty()) {
	            System.out.println("鍒楄〃涓虹┖銆�");
	        } 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return buys;
	}
	//閫�娆捐褰曠鐞�
		public List<Shop_Refund> refundmanage() {
		    List<Shop_Refund> refunds = new ArrayList<>();
		    
		    try (Connection conn = DriverManager.getConnection(url);
		         Statement stmt = conn.createStatement()) {
		        String sql = "SELECT * FROM refund"; // 鏇挎崲涓轰綘鐨勮〃鍚�
		        ResultSet rs = stmt.executeQuery(sql);
		        while (rs.next()) {
		        	Shop_Refund comm = new Shop_Refund();
		            comm.setId(rs.getInt("id"));
		            comm.setRefundcommodity(rs.getString("refundcommodity"));
		            comm.setRefundamount(rs.getInt("refundamount"));
		            comm.setRefunddate(rs.getString("refunddate"));
		            comm.setRefundmoney(rs.getString("refundmoney"));
		            comm.setRefunder(rs.getString("refunder"));
		            refunds.add(comm);
		        }
		        if (refunds.isEmpty()) {
		            System.out.println("鍒楄〃涓虹┖銆�");
		        } 
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return refunds;
		}
	//杩涜揣
		public void stock(int Amount,int Commod)
		{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		 PreparedStatement pstmt1 = null;
	        try {
	            // Establish connection
	            conn = DriverManager.getConnection(url);

	            // Query to insert new stock
	            String sql = "INSERT INTO Stock (stockamount, ID) VALUES (?, ?)";//澧炲姞stock鐨勯」
	            pstmt1 = conn.prepareStatement(sql);
	            pstmt1.setInt(1, Amount);
	            pstmt1.setInt(2, Commod);
	            
	            // Execute insert
	            int rowsAffected = pstmt1.executeUpdate();//鍙楀奖鍝嶇殑琛屾暟
	            
	            if (rowsAffected > 0) {
	            } else {
	                System.out.println("Insert failed.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // Close resources
	            try {
	                if (pstmt1 != null) pstmt1.close();
	                if (conn != null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

		        try {
		            // Establish connection
		            conn = DriverManager.getConnection(url);

		            // Query to update the amount of the commodity
		            String sql = "UPDATE Commodity SET amount = amount + ? WHERE ID = ?";//澧炲姞鍟嗗搧鐨勫簱瀛橈紝鏀瑰彉鐨勬槸commodity鐨勭被
		            pstmt = conn.prepareStatement(sql);
		            pstmt.setInt(1, Amount);
		            pstmt.setInt(2, Commod);
		            
		            // Execute update
		            int rowsAffected = pstmt.executeUpdate();
		            
		            if (rowsAffected > 0) {
		                System.out.println("杩涜揣鎴愬姛銆�");
		            } else {
		                System.out.println("杩涜揣澶辫触銆�");
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } finally {
		            // Close resources
		            try {
		                if (pstmt != null) pstmt.close();
		                if (conn != null) conn.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }	
				
			}
		//杩涜揣娌℃湁鐨勫晢鍝�
		 public void newstock(String money, int amount, String income, String category, String commodity) {
		        // Database URL
		      
		        // SQL insert statement
		        String sql = "INSERT INTO Commodity (money, amount, income, category, commodity) VALUES (?, ?, ?, ?, ?)";

		        try (Connection conn = DriverManager.getConnection(url);
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		            // Set parameters
		            pstmt.setString(1, money);
		            pstmt.setInt(2, amount);
		            pstmt.setString(3, income);
		            pstmt.setString(4, category);
		            pstmt.setString(5, commodity);

		            // Execute the update
		            pstmt.executeUpdate();

		            System.out.println("娣诲姞鎴愬姛!");

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

}
