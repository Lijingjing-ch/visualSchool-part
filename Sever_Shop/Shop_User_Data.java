package Sever_Shop;

import java.awt.EventQueue;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import visualSchool.Loginframe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

//瀛︾敓鐨勫悇绉嶅姛鑳�
public class Shop_User_Data {
    // 鍟嗗搧娴忚
    private String url = "jdbc:ucanaccess://D:/暑期学校-2/专业实训/Database2.accdb";

    public List<Shop_Commodity> browseItems() {
        List<Shop_Commodity> commodities = new ArrayList<>();// 
        try (Connection conn = DriverManager.getConnection(url); // 
                Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM Commodity"; // 
            ResultSet rs = stmt.executeQuery(sql);// 

            while (rs.next()) {// 
                Shop_Commodity comm = new Shop_Commodity();// 涓�涓柊寤虹殑椤�
                comm.setID(rs.getInt("ID"));// 鍚勭灞炴��
                comm.setCommodity(rs.getString("commodity"));
                comm.setMoney(rs.getString("money"));
                comm.setAmount(rs.getInt("amount"));
                comm.setCategory(rs.getString("category"));
                comm.setIncome(rs.getString("income"));
                commodities.add(comm);// 娣诲姞鍒板垪琛ㄦ暟缁勯噷闈�
            }
            if (commodities.isEmpty()) {// 濡傛灉鍒楄〃鏄┖
                System.out.println("鏃犳硶鏌ヨ銆�");
            }

        } catch (SQLException e) {// 寮傚父鎹曡幏
            e.printStackTrace();
        }

        return commodities;// 杩斿洖list鍒楄〃绫诲瀷鐨勯」
    }

    // 鍟嗗搧绉嶇被鏌ヨ
    public List<Shop_Commodity> browseItemscategory(String categ) {
        List<Shop_Commodity> commodities = new ArrayList<>();// 鏂板缓涓�涓垪琛ㄦ暟缁�
        String sql = "SELECT * FROM Commodity WHERE category = ?";// 鏌ヨ鐨勮〃鍗曞拰琛ㄥ崟涓墍鍖归厤鐨勯」
        try (Connection conn = DriverManager.getConnection(url); // 鏁版嵁搴撶殑浣嶇疆
                PreparedStatement pstmt = conn.prepareStatement(sql)) {// 涓�涓究浜庢暟鎹簱鏌ヨ鐨勭被鍨嬶紝鏈夋潯浠剁殑鏌ヨ浣跨敤

            pstmt.setString(1, categ); // 璁剧疆鏌ヨ鍙傛暟

            ResultSet rs = pstmt.executeQuery();// 鏌ヨ鍚庣殑鍙傛暟

            while (rs.next()) {
                Shop_Commodity comm = new Shop_Commodity();// 涓�涓柊寤虹殑椤�
                comm.setID(rs.getInt("ID"));
                comm.setCommodity(rs.getString("commodity"));
                comm.setMoney(rs.getString("money"));
                comm.setCategory(rs.getString("category"));
                commodities.add(comm);
            }
            if (commodities.isEmpty()) {// 鍒楄〃涓虹┖
                System.out.println("鏃犳硶鏌ヨ銆�");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commodities;
    }

    // 鍟嗗搧鍚嶆煡璇�
    public List<Shop_Commodity> browseItemscommodity(String commod) {
        List<Shop_Commodity> commodities = new ArrayList<>();
        String sql = "SELECT * FROM Commodity WHERE commodity = ?";
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, commod); // 璁剧疆鏌ヨ鍙傛暟

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Shop_Commodity comm = new Shop_Commodity();
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

    public void buyItems(int commod, int amoun, String buye) {
        try (Connection conn = DriverManager.getConnection(url)) {
            // 查找商品
            String sqlSelectCommodity = "SELECT * FROM Commodity WHERE ID = ?";
            PreparedStatement pstmtSelectCommodity = conn.prepareStatement(sqlSelectCommodity);
            pstmtSelectCommodity.setInt(1, commod);
            ResultSet rsCommodity = pstmtSelectCommodity.executeQuery();

            if (rsCommodity.next() && rsCommodity.getInt("amount") >= amoun) {
                double price2 = Double.parseDouble(rsCommodity.getString("money"));
                
                // 查找用户余额
                String sqlSelectBalance = "SELECT * FROM balance WHERE userID = ?";
                PreparedStatement pstmtSelectBalance = conn.prepareStatement(sqlSelectBalance);
                pstmtSelectBalance.setString(1, buye);
                ResultSet rsBalance = pstmtSelectBalance.executeQuery();

                if (rsBalance.next()) {
                    double price1 = Double.parseDouble(rsBalance.getString("userbalance"));

                    if (price1 >= price2 * amoun) {
                        // 准备插入数据
                        String sqlInsert = "INSERT INTO buy (ID, buycommodity, buyamount, buydate, buymoney, buyer) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert);

                        pstmtInsert.setInt(1, rsCommodity.getInt("ID"));
                        pstmtInsert.setString(2, rsCommodity.getString("commodity"));
                        pstmtInsert.setInt(3, amoun);
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String formattedDate = now.format(formatter);
                        pstmtInsert.setString(4, formattedDate);

                        double value = price2 * amoun;
                        DecimalFormat df = new DecimalFormat("#.00");
                        String formattedValue = df.format(value);
                        pstmtInsert.setString(5, formattedValue);
                        pstmtInsert.setString(6, buye);

                        pstmtInsert.executeUpdate();

                        // 更新商品库存
                        String sqlUpdateCommodity = "UPDATE Commodity SET amount = amount - ? WHERE ID = ?";
                        PreparedStatement pstmtUpdateCommodity = conn.prepareStatement(sqlUpdateCommodity);
                        pstmtUpdateCommodity.setInt(1, amoun);
                        pstmtUpdateCommodity.setInt(2, commod);
                        pstmtUpdateCommodity.executeUpdate();

                        // 更新用户余额
                        double price3 = price1 - value;
                        String price4 = df.format(price3);
                        String sqlUpdateBalance = "UPDATE balance SET userbalance = ? WHERE userID = ?";
                        PreparedStatement pstmtUpdateBalance = conn.prepareStatement(sqlUpdateBalance);
                        pstmtUpdateBalance.setString(1, price4);
                        pstmtUpdateBalance.setString(2, buye);
                        pstmtUpdateBalance.executeUpdate();

                        System.out.println("购买成功。");
                    } else {
                        System.out.println("抱歉，余额不足。");
                    }
                } else {
                    System.out.println("用户未找到。");
                }
            } else {
                System.out.println("抱歉，库存不足。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 璁㈠崟璁板綍锛岀浉鍏崇殑娉ㄩ噴鍚屼笂
    public List<Shop_Buy> browseItemsbuy(String buyerer) {
        List<Shop_Buy> buyers = new ArrayList<>();
        String sql = "SELECT * FROM buy WHERE buyer = ?";
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, buyerer); // 璁剧疆鏌ヨ鍙傛暟

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // 鍒涘缓buy瀵硅薄骞跺～鍏呮暟鎹�
                    Shop_Buy b = new Shop_Buy();
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

    // 閫�娆�
    public void refundItems(int ID) {

        Connection connection = null;
        PreparedStatement selectStmt = null;
        PreparedStatement deleteStmt = null;
        PreparedStatement insertStmt = null;
        PreparedStatement updateCommodityStmt = null;
        PreparedStatement selectBalanceStmt = null;
        PreparedStatement updateBalanceStmt = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection(url);

            // Prepare SQL statements
            String selectSQL = "SELECT * FROM buy WHERE id = ?";
            selectStmt = connection.prepareStatement(selectSQL);
            selectStmt.setInt(1, ID);
            ResultSet resultSet = selectStmt.executeQuery();
            
            if (resultSet.next()) {
                // Create Buy and Refund objects
                Shop_Buy buyItem = new Shop_Buy();
                buyItem.setID(resultSet.getInt("ID"));
                buyItem.setBuycommodity(resultSet.getString("buycommodity"));
                buyItem.setBuyamount(resultSet.getInt("buyamount"));
                buyItem.setBuydate(resultSet.getString("buydate"));
                buyItem.setBuymoney(resultSet.getString("buymoney"));
                buyItem.setBuyer(resultSet.getString("buyer"));
                
                // Retrieve and update user balance
                String selectBalanceSQL = "SELECT userbalance FROM balance WHERE userID = ?";
                selectBalanceStmt = connection.prepareStatement(selectBalanceSQL);
                selectBalanceStmt.setString(1, buyItem.getBuyer());
                ResultSet balanceResultSet = selectBalanceStmt.executeQuery();

                double price2 = 0;
                if (balanceResultSet.next()) {
                    String userBalance = balanceResultSet.getString("userbalance");
                    price2 = Double.parseDouble(userBalance);
                }

                double price3 = Double.parseDouble(buyItem.getBuymoney());
                double price4 = price2 + price3;
                String price5 = String.format("%.2f", price4);

                String updateBalanceSQL = "UPDATE balance SET userbalance = ? WHERE userID = ?";
                updateBalanceStmt = connection.prepareStatement(updateBalanceSQL);
                updateBalanceStmt.setString(1, price5);
                updateBalanceStmt.setString(2, buyItem.getBuyer());
                updateBalanceStmt.executeUpdate();

                // Create Refund object
                Shop_Refund refundItem = new Shop_Refund();
                refundItem.setId(buyItem.getID());
                refundItem.setRefundcommodity(buyItem.getBuycommodity());
                refundItem.setRefundamount(buyItem.getBuyamount());
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDate = now.format(formatter);
                refundItem.setRefunddate(formattedDate);
                refundItem.setRefundmoney(buyItem.getBuymoney());
                refundItem.setRefunder(buyItem.getBuyer());

                // Update Commodity table
                String updateCommoditySQL = "UPDATE Commodity SET amount = amount + ? WHERE commodity = ?";
                updateCommodityStmt = connection.prepareStatement(updateCommoditySQL);
                updateCommodityStmt.setInt(1, buyItem.getBuyamount());
                updateCommodityStmt.setString(2, buyItem.getBuycommodity());
                updateCommodityStmt.executeUpdate();

                // Delete record from buy table
                String deleteSQL = "DELETE FROM buy WHERE ID = ?";
                deleteStmt = connection.prepareStatement(deleteSQL);
                deleteStmt.setInt(1, buyItem.getID());
                deleteStmt.executeUpdate();

                // Insert into refund table
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
                if (selectStmt != null)
                    selectStmt.close();
                if (deleteStmt != null)
                    deleteStmt.close();
                if (insertStmt != null)
                    insertStmt.close();
                if (updateCommodityStmt != null)
                    updateCommodityStmt.close();
                if (selectBalanceStmt != null)
                    selectBalanceStmt.close();
                if (updateBalanceStmt != null)
                    updateBalanceStmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 閫�娆捐褰�
    public List<Shop_Refund> browseItemsrefund(String refunderer) {
        List<Shop_Refund> refunders = new ArrayList<>();
        String sql = "SELECT * FROM refund WHERE refunder = ?";
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, refunderer); // 璁剧疆鏌ヨ鍙傛暟

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // 鍒涘缓buy瀵硅薄骞跺～鍏呮暟鎹�
                    Shop_Refund b = new Shop_Refund();
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

    // 鐢ㄦ埛浣欓鏌ヨ
    public List<Shop_Balance> checkbalance(String userID) {
        List<Shop_Balance> balances = new ArrayList<>();
        String sql = "SELECT * FROM balance WHERE userID = ?";
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userID); // 璁剧疆鏌ヨ鍙傛暟
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // 鍒涘缓buy瀵硅薄骞跺～鍏呮暟鎹�
                    Shop_Balance b = new Shop_Balance();
                    b.setUserID(rs.getString("userID"));
                    b.setUserbalance(rs.getString("userbalance"));

                    // 灏哹uy瀵硅薄娣诲姞鍒癰uyers鍒楄〃
                    balances.add(b);
                }
            }

            // 杈撳嚭鎻愮ず淇℃伅
            if (balances.isEmpty()) {
                Shop_Balance b1 = new Shop_Balance();
                b1.setUserID("userID");
                b1.setUserbalance("0.00");

                // 灏哹uy瀵硅薄娣诲姞鍒癰uyers鍒楄〃
                balances.add(b1);
                String sql1 = "INSERT INTO balance (userID,userbalance) VALUES (?, ?)";

                try (Connection conn1 = DriverManager.getConnection(url);
                        PreparedStatement pstmt1 = conn.prepareStatement(sql1)) {

                    // Set parameters
                    pstmt1.setString(1, userID);
                    pstmt1.setString(2, "0");

                    // Execute the update
                    pstmt1.executeUpdate();

                    System.out.println("娣诲姞鎴愬姛!");

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balances;
    }
    public void addBalance(String userID, String addMoney) {
        Connection conn = null;
        PreparedStatement selectPstmt = null;
        PreparedStatement updatePstmt = null;
        ResultSet rs = null;

        try {
            // Establish connection
            conn = DriverManager.getConnection(url);

            // 1. 璇诲彇褰撳墠浣欓
            String selectSql = "SELECT userbalance FROM balance WHERE userID = ?";
            selectPstmt = conn.prepareStatement(selectSql);
            selectPstmt.setString(1, userID);
            rs = selectPstmt.executeQuery();

            double currentBalance = 0.0;
            if (rs.next()) {
                currentBalance = Double.parseDouble(rs.getString("userbalance"));
            }

            // 2. 杞崲 addMoney 涓� double
            double moneyToAdd = Double.parseDouble(addMoney);

            // 3. 璁＄畻鏂扮殑浣欓
            double newBalance = currentBalance + moneyToAdd;

            // 4. 鏇存柊浣欓
            String updateSql = "UPDATE balance SET userbalance = ? WHERE userID = ?";
            updatePstmt = conn.prepareStatement(updateSql);
            updatePstmt.setDouble(1, newBalance);
            updatePstmt.setString(2, userID);

            int rowsAffected = updatePstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("鍏呭�兼垚鍔熴��");
            } else {
                System.out.println("鍏呭�煎け璐ャ��");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (selectPstmt != null) selectPstmt.close();
                if (updatePstmt != null) updatePstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public List<Shop_Commodity> Personalizedrecommendations(String userID) {
        List<Shop_Commodity> recommendedCommodities = new ArrayList<>();
        Shop_Buy buy = new Shop_Buy();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 连接数据库
            conn = DriverManager.getConnection(url);

            // 查询最近的购买记录
            String sql = "SELECT * FROM buy WHERE buyer = ? ORDER BY ID DESC LIMIT 1";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                buy.setID(rs.getInt("ID"));
                buy.setBuycommodity(rs.getString("buycommodity"));
                buy.setBuyamount(rs.getInt("buyamount"));
                buy.setBuydate(rs.getString("buydate"));
                buy.setBuymoney(rs.getString("buymoney"));
                buy.setBuyer(rs.getString("buyer"));
            }

            // 查询所有商品
            String sqlCommodities = "SELECT * FROM Commodity";
            pstmt = conn.prepareStatement(sqlCommodities);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Shop_Commodity commodity = new Shop_Commodity();
                commodity.setID(rs.getInt("ID"));
                commodity.setCommodity(rs.getString("commodity"));
                commodity.setMoney(rs.getString("money"));
                commodity.setAmount(rs.getInt("amount"));
                commodity.setCategory(rs.getString("category"));
                commodity.setIncome(rs.getString("income"));

                // 根据最后购买的商品进行推荐
                if (containsPartialMatch(buy.getBuycommodity(), commodity.getCommodity())) {
                    recommendedCommodities.add(commodity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return recommendedCommodities;
    }

    private boolean containsPartialMatch(String buyCommodity, String commodity) {
        // 将两个字符串转换为字符数组
        char[] buyChars = buyCommodity.toCharArray();
        char[] commodityChars = commodity.toCharArray();

        // 将字符存储在集合中
        Set<Character> buyCharSet = new HashSet<>();
        for (char c : buyChars) {
            buyCharSet.add(c);
        }

        // 检查是否有重叠字符
        for (char c : commodityChars) {
            if (buyCharSet.contains(c)) {
                return true;
            }
        }
        
        return false;
    }
    public List<Shop_Remark> remarkcheck(String userID) {
        List<Shop_Remark> remarksList = new ArrayList<>();
        String query = "SELECT id, commodity, commodityremark FROM remark WHERE commodity = ?"; // Adjust the query as needed

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setString(1, userID); // Assuming userID is matched with commodity

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Shop_Remark remark = new Shop_Remark();
                    remark.setId(rs.getInt("ID"));
                    remark.setCommodity(rs.getString("commodity"));
                    remark.setCommodityremark(rs.getString("commodityremark"));
                    remarksList.add(remark);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions as needed
        }

        return remarksList;
    }
    public void remark(String commodity, String commodityremark) {
        String query = "INSERT INTO remark (commodity, commodityremark) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setString(1, commodity);
            stmt.setString(2, commodityremark);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions as needed
        }
    }

    public static void main(String[] args) {
        Shop_User_Data shopUserData = new Shop_User_Data();
        // Define the userID for the query
        String userID = "半岛良品垃圾袋"; // Replace with actual userID
        String commo="差评";
        shopUserData.remark(userID,commo);
    }
}
