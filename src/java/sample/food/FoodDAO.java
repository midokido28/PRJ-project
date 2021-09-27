/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author Suki
 */
public class FoodDAO {

    public List<FoodDTO> getList(String search) throws SQLException {
        List<FoodDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " Select ID, Name, Description, Price, CookingTime, isDelete, CreateDate "
                        + " FROM tblFoods "
                        + " WHERE Name like ? and isDelete=0"
                        + " ORDER BY CookingTime ASC ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String ID = rs.getString("ID");
                    String Name = rs.getString("Name");
                    String Description = rs.getString("Description");
                    float Price = rs.getFloat("price");
                    int CookingTime = rs.getInt("CookingTime");
                    int isDelete = rs.getInt("isDelete");
                    String CreateDate = rs.getString("CreateDate");

                    list.add(new FoodDTO(ID, Name, Description, Price, CookingTime, isDelete, CreateDate));
                }

            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;

    }
    
    public List<FoodDTO> getListByStatus(String search, String status) throws SQLException {
        List<FoodDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if("all".equals(status))
                sql = " Select ID, Name, Description, Price, CookingTime, isDelete, CreateDate "
                        + " FROM tblFoods "
                        + " WHERE Name like ?"
                        + " ORDER BY CookingTime ASC ";
                else if("active".equals(status)) 
                    sql = " Select ID, Name, Description, Price, CookingTime, isDelete, CreateDate "
                        + " FROM tblFoods "
                        + " WHERE Name like ? AND IsDelete = 0"
                        + " ORDER BY CookingTime ASC ";
                else if("deleted".equals(status))
                    sql = " Select ID, Name, Description, Price, CookingTime, isDelete, CreateDate "
                        + " FROM tblFoods "
                        + " WHERE Name like ? AND IsDelete = 1"
                        + " ORDER BY CookingTime ASC ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String ID = rs.getString("ID");
                    String Name = rs.getString("Name");
                    String Description = rs.getString("Description");
                    float Price = rs.getFloat("price");
                    int CookingTime = rs.getInt("CookingTime");
                    int isDelete = rs.getInt("isDelete");
                    String CreateDate = rs.getString("CreateDate");

                    list.add(new FoodDTO(ID, Name, Description, Price, CookingTime, isDelete, CreateDate));
                }

            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;

    }

    public boolean delete(String ID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblFoods "
                        + " SET  IsDelete=1 "
                        + " WHERE ID=? ";                      
                stm = conn.prepareStatement(sql);
                stm.setString(1, ID);
                result = stm.executeUpdate() > 0 ? true : false;
            }

        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return result;
    }

    public boolean update(FoodDTO food) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = " UPDATE tblFoods "
                        + " SET Name=?, Description=?, Price=?, CookingTime=?, isDelete=?, CreateDate=?  "
                        + " WHERE ID=? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, food.getName());
                stm.setString(2, food.getDescription());
                stm.setFloat(3, food.getPrice());
                stm.setInt(4, food.getCookingTime());
                stm.setInt(5, food.getIsDelete());
                stm.setString(6, food.getCreateDate());
                stm.setString(7, food.getID());
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return check;
    }

    public boolean insert(FoodDTO food) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = " INSERT INTO tblFoods(ID, Name, Description, Price, CookingTime,isDelete,CreateDate) "
                        + " VALUES(?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, food.getID());
                stm.setString(2, food.getName());
                stm.setString(3, food.getDescription());
                stm.setFloat(4, food.getPrice());
                stm.setInt(5, food.getCookingTime());
                stm.setInt(6, food.getIsDelete());
                stm.setString(7, food.getCreateDate());
                check = stm.executeUpdate() > 0;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public boolean checkDuplicate(String ID) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = " SELECT ID "
                        + " FROM tblFoods "
                        + " WHERE ID=? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, ID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return check;
    }
}
