package visualSchool;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    private Connection connection;

    public UserService() {
        try {
            this.connection = DatabaseHelper.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 处理JSON请求
  

    // 注册方法
    public boolean register(String userId, String userPassword, String identity) {
        String sql = "INSERT INTO user (user_id, user_password, identity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setString(2, userPassword);
            stmt.setString(3, identity);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 登录方法
    public String login(String userId, String userPassword, String identity) {
        String sql = "SELECT * FROM user WHERE user_id = ? AND user_password = ? AND identity = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setString(2, userPassword);
            stmt.setString(3, identity);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? "true" : "false";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "false";
        }
    }

    // 创建JSON响应
    private String createJsonResponse(int num, boolean operationResult) {
        String opr = operationResult ? "true" : "false";
        return "{\"num\":" + num + ",\"opr\":\"" + opr + "\"}";
    }
}
