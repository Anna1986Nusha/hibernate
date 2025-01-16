package jm.task.core.jdbc.dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import jm.task.core.jdbc.model.User;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.connection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {


    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age + ")");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = " + id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String sql = "TRUNCATE TABLE users";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
