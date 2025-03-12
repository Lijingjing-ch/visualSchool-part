package shop;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
//瀛︾敓鐨勫悇绉嶅姛鑳�
public class item  {
	//鍟嗗搧娴忚
	public List<commodity> browseItems() {
	    List<commodity> commodities = new ArrayList<>();//涓�涓垪琛ㄦ暟缁�
	    try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://F:/澶т笁鏆戞湡瀛︽牎/Database1.accdb");//鏁版嵁搴撶殑浣嶇疆
	         Statement stmt = conn.createStatement()) {

	        String sql = "SELECT * FROM Commodity"; // 鏌ヨ鐨勮〃鍗�
	        ResultSet rs = stmt.executeQuery(sql);//鏌ヨ鍚庣殑琛�

	        while (rs.next()) {//閬嶅巻琛紝鏈変笅涓�琛岃繑鍥瀟rue锛屾病鏈変笅涓�琛岃繑鍥瀎alse
	            commodity comm = new commodity();//涓�涓柊寤虹殑椤�
	            comm.setID(rs.getInt("ID"));//鍚勭灞炴��
	            comm.setCommodity(rs.getString("commodity"));
	            comm.setMoney(rs.getString("money"));
	            comm.setCategory(rs.getString("category"));
	            comm.setIncome(rs.getString("income"));
	            commodities.add(comm);//娣诲姞鍒板垪琛ㄦ暟缁勯噷闈�
	        }
	        if (commodities.isEmpty()) {//濡傛灉鍒楄〃鏄┖
	            System.out.println("鏃犳硶鏌ヨ銆�");
	        } 

	    } catch (SQLException e) {//寮傚父鎹曡幏
	        e.printStackTrace();
	    }

	    return commodities;//杩斿洖list鍒楄〃绫诲瀷鐨勯」
	}
	//鍟嗗搧绉嶇被鏌ヨ
	public List<commodity> browseItemscategory(String categ){
	    List<commodity> commodities = new ArrayList<>();//鏂板缓涓�涓垪琛ㄦ暟缁�
	    String sql = "SELECT * FROM Commodity WHERE category = ?";//鏌ヨ鐨勮〃鍗曞拰琛ㄥ崟涓墍鍖归厤鐨勯」
	    try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://F:/澶т笁鏆戞湡瀛︽牎/Database1.accdb");//鏁版嵁搴撶殑浣嶇疆
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {//涓�涓究浜庢暟鎹簱鏌ヨ鐨勭被鍨嬶紝鏈夋潯浠剁殑鏌ヨ浣跨敤

	        pstmt.setString(1, categ); // 璁剧疆鏌ヨ鍙傛暟

	        ResultSet rs = pstmt.executeQuery();//鏌ヨ鍚庣殑鍙傛暟

	        while (rs.next()) {
	            commodity comm = new commodity();//涓�涓柊寤虹殑椤�
	            comm.setID(rs.getInt("ID"));
	            comm.setCommodity(rs.getString("commodity"));
	            comm.setMoney(rs.getString("money"));
	            comm.setCategory(rs.getString("category"));
	            commodities.add(comm);
	        }
	        if (commodities.isEmpty()) {//鍒楄〃涓虹┖
	            System.out.println("鏃犳硶鏌ヨ銆�");
	        } 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return commodities;
	}
	//鍟嗗搧鍚嶆煡璇�
	public List<commodity> browseItemscommodity(String commod){
	    List<commodity> commodities = new ArrayList<>();
	    String sql = "SELECT * FROM Commodity WHERE commodity = ?";
	    try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://F:/澶т笁鏆戞湡瀛︽牎/Database1.accdb");
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, commod); // 璁剧疆鏌ヨ鍙傛暟

	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            commodity comm = new commodity();
	            comm.setID(rs.getInt("ID"));
	            comm.setCommodity(rs.getString("commodity"));
	            comm.setMoney(rs.getString("money"));
	            comm.setCategory(rs.getString("category"));
	            commodities.add(comm);
	        }
	        if (commodities.isEmpty()) {
	            System.out.println("鏃犳硶鏌ヨ銆�");
	        } 

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return commodities;
	}
	//璐拱
	public void buyItems(String commod, int amoun, String buye) {
	    try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://F:/澶т笁鏆戞湡瀛︽牎/Database1.accdb");//鏁版嵁搴撶殑浣嶇疆
	         Statement stmt = conn.createStatement()) {//鏁版嵁搴撴煡璇㈢殑瀛楁

	        // 鏌ユ壘鍟嗗搧
	        String sqlSelect = "SELECT * FROM Commodity WHERE commodity = ?";
	        PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect);
	        pstmtSelect.setString(1, commod);
	        ResultSet rs = pstmtSelect.executeQuery();

	        if (rs.next() && rs.getInt("amount") >= amoun) {
	            // 鍑嗗鎻掑叆鏁版嵁
	            String sqlInsert = "INSERT INTO buy (ID, buycommodity, buyamount, buydate, buymoney, buyer) VALUES (?, ?, ?, ?, ?, ?)";//鎻掑叆鐨勬暟鎹簱鐨勫悕瀛楋紝鏁版嵁搴撶殑缂哄皯鐨勫瓧娈�
	            PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert);//鎻掑叆琛ㄧず

	            pstmtInsert.setInt(1, rs.getInt("ID")); //灏咺D鎻掑叆鍒扮涓�涓綅缃�
	            pstmtInsert.setString(2, commod);
	            pstmtInsert.setInt(3, amoun);
	            LocalDateTime now = LocalDateTime.now();//鑾峰彇鐜板湪鐨勬椂闂�
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//鏃堕棿鐨勬牸寮�
	            String formattedDate = now.format(formatter);//杞寲鎴怱tring绫诲瀷
	            pstmtInsert.setString(4, formattedDate);
	            double value = Double.parseDouble(rs.getString("money"));//灏唌oney杞寲鎴怱tring绫诲瀷
	            value=value*amoun;//鍋氬姞鍑忔硶
	            // 浣跨敤 DecimalFormat 淇濈暀涓や綅灏忔暟
	            DecimalFormat df = new DecimalFormat("#.00");//鏍煎紡杈撳嚭
	            String formattedValue = df.format(value);//杞寲鎴怱tring
	            pstmtInsert.setString(5, formattedValue);
	            pstmtInsert.setString(6, buye);

	            pstmtInsert.executeUpdate(); // 鎵ц鎻掑叆鎿嶄綔

	            // 鏇存柊鍟嗗搧搴撳瓨
	            String sqlUpdate = "UPDATE Commodity SET amount = amount - ? WHERE commodity = ?";//瀵规煇涓�鍟嗗搧鐨勬暟閲忓仛鍑忔硶
	            PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate);
	            pstmtUpdate.setInt(1, amoun);//鍑忓幓鐨勬暟閲�
	            pstmtUpdate.setString(2, commod);//鍟嗗搧鐨勫悕瀛�
	            pstmtUpdate.executeUpdate(); // 鎵ц鏇存柊鎿嶄綔

	            System.out.println("璐拱鎴愬姛銆�");
	        } else {
	            System.out.println("鎶辨瓑锛屽簱瀛樹笉瓒炽��");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	//璁㈠崟璁板綍锛岀浉鍏崇殑娉ㄩ噴鍚屼笂
	public List<buy> browseItemsbuy(String buyerer) {
        List<buy> buyers = new ArrayList<>();
        String sql = "SELECT * FROM buy WHERE buyer = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://F:/澶т笁鏆戞湡瀛︽牎/Database1.accdb");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, buyerer); // 璁剧疆鏌ヨ鍙傛暟

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // 鍒涘缓buy瀵硅薄骞跺～鍏呮暟鎹�
                    buy b = new buy();
                    b.setID(rs.getInt("ID"));
                    b.setBuycommodity(rs.getString("buycommodity"));
                    b.setBuyamount(rs.getInt("buyamount"));
                    b.setBuydate(rs.getString("buydate"));
                    b.setBuymoney(rs.getString("buymoney"));
                    b.setBuyer(rs.getString("buyer"));

                    // 灏哹uy瀵硅薄娣诲姞鍒癰uyers鍒楄〃
                    buyers.add(b);
                }
            }
            
            // 杈撳嚭鎻愮ず淇℃伅
            if (buyers.isEmpty()) {
                System.out.println("鏃犳硶鏌ヨ銆�");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buyers;
    }
//閫�娆�
public void refundItems(int ID) {
    String DATABASE_URL = "jdbc:ucanaccess://F:/澶т笁鏆戞湡瀛︽牎/Database1.accdb";//鏁版嵁搴撶殑浣嶇疆
    Connection connection = null;//杩炴帴鐨勬暟鎹簱
    PreparedStatement selectStmt = null;//PreparedStatement绫诲瀷鐨勬暟鎹�,琛ㄧず澧炲垹鏀规煡
    PreparedStatement deleteStmt = null;
    PreparedStatement insertStmt = null;
    PreparedStatement updateCommodityStmt = null;
    
    try {
        // Establish connection
        connection = DriverManager.getConnection(DATABASE_URL);
        
        // Prepare SQL statements
        String selectSQL = "SELECT * FROM buy WHERE id = ?";//鏌ヨ鐨勮〃鍗曞拰鐩稿簲鍖归厤鐨勬潯浠�
        selectStmt = connection.prepareStatement(selectSQL);
        selectStmt.setInt(1, ID);
        ResultSet resultSet = selectStmt.executeQuery();
        if (resultSet.next()) {
            // Create Buy and Refund objects
            buy buyItem = new buy();//寤虹珛涓�涓猙uy绫诲瀷鐨勬暟鎹�
            buyItem.setID(resultSet.getInt("ID"));
            buyItem.setBuycommodity(resultSet.getString("buycommodity"));
            buyItem.setBuyamount(resultSet.getInt("buyamount"));
            buyItem.setBuydate(resultSet.getString("buydate"));
            buyItem.setBuymoney(resultSet.getString("buymoney"));
            buyItem.setBuyer(resultSet.getString("buyer"));
            //寤虹珛涓�涓猺efund绫诲瀷鐨勬暟鎹紝涓巄uy绫诲瀷鐨勬暟鎹繘琛屽尮閰�
            refund refundItem = new refund();
            refundItem.setId(buyItem.getID());
            refundItem.setRefundcommodity(buyItem.getBuycommodity());
            refundItem.setRefundamount(buyItem.getBuyamount());
            LocalDateTime now = LocalDateTime.now();//鑾峰彇鐜板湪鐨勬椂闂�
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//鏃堕棿鐨勬牸寮�
            String formattedDate = now.format(formatter);//杞寲鎴怱tring绫诲瀷
            refundItem.setRefunddate(formattedDate);
            refundItem.setRefundmoney(buyItem.getBuymoney());
            refundItem.setRefunder(buyItem.getBuyer());
            
            // Update Commodity table
            String updateCommoditySQL = "UPDATE Commodity SET amount = amount + ? WHERE commodity = ?";//澧炲姞鍟嗗搧鐨勫簱瀛�
            updateCommodityStmt = connection.prepareStatement(updateCommoditySQL);//鏁版嵁搴撶壒瀹氬瓧娈垫煡璇�
            updateCommodityStmt.setInt(1, buyItem.getBuyamount());//鍦ㄥ師鏈夊熀纭�涓婂鍔犻��璐х殑鏁伴噺
            updateCommodityStmt.setString(2, buyItem.getBuycommodity());
            updateCommodityStmt.executeUpdate();
         // Delete record from buy table
            String deleteSQL = "DELETE FROM buy WHERE ID = ?";
            deleteStmt = connection.prepareStatement(deleteSQL);
            deleteStmt.setInt(1, buyItem.getID());
            deleteStmt.executeUpdate();
            // 灏唕efundItem娣诲姞鍒皉efund鐨勮〃鍗曢噷闈�
            String insertSQL = "INSERT INTO refund (id, refundcommodity, refundamount, refunddate, refundmoney, refunder) VALUES (?, ?, ?, ?, ?, ?)";
            insertStmt = connection.prepareStatement(insertSQL);
            insertStmt.setInt(1, refundItem.getId());
            insertStmt.setString(2, refundItem.getRefundcommodity());
            insertStmt.setInt(3, refundItem.getRefundamount());
            insertStmt.setString(4, refundItem.getRefunddate());
            insertStmt.setString(5, refundItem.getRefundmoney());
            insertStmt.setString(6, refundItem.getRefunder());
            insertStmt.executeUpdate();
        } else {
            System.out.println("No matching records found.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Clean up resources
        try {
            if (selectStmt != null) selectStmt.close();
            if (deleteStmt != null) deleteStmt.close();
            if (insertStmt != null) insertStmt.close();
            if (updateCommodityStmt != null) updateCommodityStmt.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
//閫�娆捐褰�
public List<refund> browseItemsrefund(String refunderer) {
    List<refund> refunders = new ArrayList<>();
    String sql = "SELECT * FROM refund WHERE refunder = ?";
    try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://F:/澶т笁鏆戞湡瀛︽牎/Database1.accdb");
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, refunderer); // 璁剧疆鏌ヨ鍙傛暟

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // 鍒涘缓buy瀵硅薄骞跺～鍏呮暟鎹�
                refund b = new refund();
                b.setId(rs.getInt("id"));
                b.setRefundcommodity(rs.getString("refundcommodity"));
                b.setRefundamount(rs.getInt("refundamount"));
                b.setRefunddate(rs.getString("refunddate"));
                b.setRefundmoney(rs.getString("refundmoney"));
                b.setRefunder(rs.getString("refunder"));

                // 灏哹uy瀵硅薄娣诲姞鍒癰uyers鍒楄〃
                refunders.add(b);
            }
        }
        
        // 杈撳嚭鎻愮ず淇℃伅
        if (refunders.isEmpty()) {
            System.out.println("鏃犳硶鏌ヨ銆�");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return refunders;
}

    public static void main(String[] args) {
    	item IT=new item();
    	IT.refundItems(11);
    	 
    }

}
