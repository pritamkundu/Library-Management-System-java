package com.library.dao;

import com.library.model.Member;
import com.library.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    // CREATE
    public void addMember(Member member) {
        String sql = "INSERT INTO members (name, email, phone) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setString(3, member.getPhone());
            stmt.executeUpdate();
            System.out.println("Member added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    // UPDATE
    public void updateMember(Member member) {
        String sql = "UPDATE members SET name=?, email=?, phone=? WHERE member_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setString(3, member.getPhone());
            stmt.setInt(4, member.getMemberId());
            stmt.executeUpdate();
            System.out.println("Member updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteMember(int memberId) {
        String sql = "DELETE FROM members WHERE member_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            stmt.executeUpdate();
            System.out.println("Member deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}