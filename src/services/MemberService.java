package services;

import dao.MemberDAO;
import models.Book;
import utils.DBConnection;
import utils.FileLogger;

import java.sql.*;
import java.util.*;
import models.Member;

public class MemberService implements MemberDAO {
    // Instantiate the file logger class to save the member to file
    FileLogger fileLogger = new FileLogger();


    @Override
    public void addMember(Member member) {
        // declare the sql query to be executed
        String sql = "INSERT INTO Members (name, email, phone) values (?, ?, ?)";

        // Use Try-with resources to establish a connection and make a prepared statement
        try (
                Connection conn = DBConnection.connectToDb();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            // Set the values to be saved to the database
            statement.setString(1, member.getName());
            statement.setString(2, member.getEmail());
            statement.setString(3, member.getPhone());

            // Execute the query
            statement.executeUpdate();
            System.out.println("Member added to database successfully");
            fileLogger.writeToCSV(member.getMember_id() + "," + member.getName() + "," + member.getEmail() + "," + member.getPhone(), "members.csv");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMember(Member member) {
        // declare the sql query to be executed
        String sql = "UPDATE Members SET name = ?, email = ?, phone = ? WHERE member_id = ?";

        // Use Try-with resources to establish a connection and make a prepared statement
        try (
                Connection conn = DBConnection.connectToDb();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // Set the values to be passed to the query
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getPhone());
            pstmt.setInt(4, member.getMember_id());

            // execute the query
            int result = pstmt.executeUpdate();
            if (result == 0) {
                System.out.println("Member was not updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMember(int memberId) {
        // declare the sql query to be executed
        String sql = "DELETE FROM Members WHERE member_id = ?";

        // Use Try-with resources to establish a connection and make a prepared statement
        try (
                Connection conn = DBConnection.connectToDb();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, memberId);
            pstmt.executeUpdate();
            System.out.println("Member deleted from the Database successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Member> getAllMembers() {
        // declare an array to store the list of books gotten from the DB
        List<Member> memberList = new ArrayList<>();

        // declare the query to execute
        String sql = "SELECT * FROM Members";

        // Use Try-with resources to establish a connection and make a prepared statement
        try (
                Connection conn = DBConnection.connectToDb();
                Statement statement = conn.createStatement();
                ResultSet response = statement.executeQuery(sql);
        ) {
            // iterate through the response to create a book object
            while (response.next()) {
                memberList.add(new Member(
                        response.getInt("member_id"), response.getString("name"), response.getString("email"),
                        response.getString("phone"))
                );
            }
        }
        catch (SQLException e)  {
            e.printStackTrace();
        }

        return memberList;
    }

    @Override
    public Member getMemberById(int memberId) {
        // declare the sql query to be executed
        String sql = "SELECT * FROM Members WHERE member_id=?";

        // Use Try-with resources to establish a connection and make a prepared statement
        try (
                Connection conn = DBConnection.connectToDb();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {

            // set the values to be queried
            statement.setInt(1, memberId);

            // execute the query
            ResultSet response = statement.executeQuery();
            if (response.next()) {
                return new Member(
                        response.getInt("member_id"), response.getString("name"), response.getString("email"),
                        response.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Member cannot be found in the Database");
        return null;
    }
}
