package shop1;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
//管理员的各种功能，重复功能请参考学生功能
public class manage { 
	//查看库存功能参考学生的商品浏览
	//购买记录管理
	public List<buy> buymanage() {
	    List<buy> buys = new ArrayList<>();
	    
	    try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://F:/大三暑期学校/Database1.accdb");
	         Statement stmt = conn.createStatement()) {
	        String sql = "SELECT * FROM buy"; // 替换为你的表名
	        ResultSet rs = stmt.executeQuery(sql);
	        while (rs.next()) {
	            buy comm = new buy();
	            comm.setID(rs.getInt("id"));
	            comm.setBuycommodity(rs.getString("buycommodity"));
	            comm.setBuyamount(rs.getInt("buyamount"));
	            comm.setBuydate(rs.getString("buydate"));
	            comm.setBuymoney(rs.getString("buymoney"));
	            comm.setBuyer(rs.getString("buyer"));
	            buys.add(comm);
	        }
	        if (buys.isEmpty()) {
	            System.out.println("列表为空。");
	        } 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return buys;
	}
	//退款记录管理
		public List<refund> refundmanage() {
		    List<refund> refunds = new ArrayList<>();
		    
		    try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://F:/大三暑期学校/Database1.accdb");
		         Statement stmt = conn.createStatement()) {
		        String sql = "SELECT * FROM refund"; // 替换为你的表名
		        ResultSet rs = stmt.executeQuery(sql);
		        while (rs.next()) {
		            refund comm = new refund();
		            comm.setId(rs.getInt("id"));
		            comm.setRefundcommodity(rs.getString("refundcommodity"));
		            comm.setRefundamount(rs.getInt("refundamount"));
		            comm.setRefunddate(rs.getString("refunddate"));
		            comm.setRefundmoney(rs.getString("refundmoney"));
		            comm.setRefunder(rs.getString("refunder"));
		            refunds.add(comm);
		        }
		        if (refunds.isEmpty()) {
		            System.out.println("列表为空。");
		        } 
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return refunds;
		}
	//进货
		public void stock(int Amount,String Commod)
		{
		String url = "jdbc:ucanaccess://F:/大三暑期学校/Database1.accdb";
		Connection conn = null;
		PreparedStatement pstmt = null;
		 PreparedStatement pstmt1 = null;
	        try {
	            // Establish connection
	            conn = DriverManager.getConnection(url);

	            // Query to insert new stock
	            String sql = "INSERT INTO Stock (stockamount, stockcommodity) VALUES (?, ?)";//增加stock的项
	            pstmt1 = conn.prepareStatement(sql);
	            pstmt1.setInt(1, Amount);
	            pstmt1.setString(2, Commod);
	            
	            // Execute insert
	            int rowsAffected = pstmt1.executeUpdate();//受影响的行数
	            
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
		            String sql = "UPDATE Commodity SET amount = amount + ? WHERE commodity = ?";//增加商品的库存，改变的是commodity的类
		            pstmt = conn.prepareStatement(sql);
		            pstmt.setInt(1, Amount);
		            pstmt.setString(2, Commod);
		            
		            // Execute update
		            int rowsAffected = pstmt.executeUpdate();
		            
		            if (rowsAffected > 0) {
		                System.out.println("进货成功。");
		            } else {
		                System.out.println("进货失败。");
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
		//进货没有的商品
		 public void newstock(String money, int amount, String income, String category, String commodity) {
		        // Database URL
		        String url = "jdbc:ucanaccess://F:/大三暑期学校/Database1.accdb";
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

		            System.out.println("添加成功!");

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		manage manager = new manage();
	    manager.newstock("200.00", 30, "100.00", "数码","MP3");
	}

}
