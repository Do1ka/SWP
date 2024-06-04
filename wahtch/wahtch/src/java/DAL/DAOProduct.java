/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import entity.Brand;
import entity.Categories;
import entity.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author admin
 */
public class DAOProduct {

    Connection conn;
    List<Product> pro;
    List<Brand> brand;
    List<Categories> cate;

    public List<Product> getPro() {
        return pro;
    }

    public void setPro(List<Product> pro) {
        this.pro = pro;
    }

    public List<Brand> getBrand() {
        return brand;
    }

    public void setBrand(List<Brand> brand) {
        this.brand = brand;
    }

    public List<Categories> getCate() {
        return cate;
    }

    public void setCate(List<Categories> cate) {
        this.cate = cate;
    }

    public DAOProduct() {
        conn = new DBContext().connection;
    }

    public void getAllBrand() {
        String sql = "SELECT [id]\n"
                + "      ,[brand_name]\n"
                + "      ,[brand_img]\n"
                + "  FROM [brand]";
        brand = new Vector<Brand>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String img = rs.getString(3);
                brand.add(new Brand(id, name, img));
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        }
    }

    public void getAllCate() {
        String sql = "SELECT [id]\n"
                + "      ,[ca_name]\n"
                + "  FROM [Categories]";
        cate = new Vector<Categories>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                cate.add(new Categories(id, name));
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        }
    }

    public void getAllProduct() {
        String sql = "SELECT [id]\n"
                + "      ,[proName]\n"
                + "      ,[caid]\n"
                + "      ,[description]\n"
                + "      ,[img]\n"
                + "      ,[price]\n"
                + "      ,[rate]\n"
                + "      ,[brand_id]\n"
                + "      ,[stockQuantity]\n"
                + "      ,[publication_date]\n"
                + "  FROM [Product]";
        pro = new Vector<Product>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int caid = rs.getInt(3);
                String description = rs.getString(4);
                String img = rs.getString(5);
                double price = rs.getDouble(6);
                float rate = rs.getFloat(7);
                int brand_id = rs.getInt(8);
                int stockQuantity = rs.getInt(9);
                String publication_date = rs.getString(10);
                pro.add(new Product(id, name, caid, description, img, price, rate, brand_id, stockQuantity, publication_date));
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        }
    }

    public void getBestProduct() {
        String sql = "SELECT TOP 8 [id]\n"
                + "      ,[proName]\n"
                + "      ,[caid]\n"
                + "      ,[description]\n"
                + "      ,[img]\n"
                + "      ,[price]\n"
                + "      ,[rate]\n"
                + "      ,[brand_id]\n"
                + "      ,[stockQuantity]\n"
                + "      ,[publication_date]\n"
                + "  FROM [Product]\n"
                + "  ORDER BY [price] DESC;";
        pro = new Vector<Product>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int caid = rs.getInt(3);
                String description = rs.getString(4);
                String img = rs.getString(5);
                double price = rs.getDouble(6);
                float rate = rs.getFloat(7);
                int brand_id = rs.getInt(8);
                int stockQuantity = rs.getInt(9);
                String publication_date = rs.getString(10);
                pro.add(new Product(id, name, caid, description, img, price, rate, brand_id, stockQuantity, publication_date));
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        }
    }
    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        dao.getAllBrand();
        List<Brand> all = dao.getBrand();
        if(all.isEmpty())System.out.println("that bai");
    }

    public Product getProductbyId(int proid) {
        Product product = new Product();
        String sql = "SELECT [id]\n"
                + "      ,[proName]\n"
                + "      ,[caid]\n"
                + "      ,[description]\n"
                + "      ,[img]\n"
                + "      ,[price]\n"
                + "      ,[rate]\n"
                + "      ,[brand_id]\n"
                + "      ,[stockQuantity]\n"
                + "      ,[publication_date]\n"
                + "  FROM [Product]\n"
                + "  Where id = ?;";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, proid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int caid = rs.getInt(3);
                String description = rs.getString(4);
                String img = rs.getString(5);
                double price = rs.getDouble(6);
                float rate = rs.getFloat(7);
                int brand_id = rs.getInt(8);
                int stockQuantity = rs.getInt(9);
                String publication_date = rs.getString(10);
                product = new Product(id, name, caid, description, img, price, rate, brand_id, stockQuantity, publication_date);
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        }
        return product;
    }
    public void getProductbyBrandId(int proid) {
        pro = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[proName]\n"
                + "      ,[caid]\n"
                + "      ,[description]\n"
                + "      ,[img]\n"
                + "      ,[price]\n"
                + "      ,[rate]\n"
                + "      ,[brand_id]\n"
                + "      ,[stockQuantity]\n"
                + "      ,[publication_date]\n"
                + "  FROM [Product]\n"
                + "  Where brand_id = ?;";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, proid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int caid = rs.getInt(3);
                String description = rs.getString(4);
                String img = rs.getString(5);
                double price = rs.getDouble(6);
                float rate = rs.getFloat(7);
                int brand_id = rs.getInt(8);
                int stockQuantity = rs.getInt(9);
                String publication_date = rs.getString(10);
                pro.add( new Product(id, name, caid, description, img, price, rate, brand_id, stockQuantity, publication_date));
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        }
    }
    public void getProductbyCateId(int proid) {
        pro = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[proName]\n"
                + "      ,[caid]\n"
                + "      ,[description]\n"
                + "      ,[img]\n"
                + "      ,[price]\n"
                + "      ,[rate]\n"
                + "      ,[brand_id]\n"
                + "      ,[stockQuantity]\n"
                + "      ,[publication_date]\n"
                + "  FROM [Product]\n"
                + "  Where caid = ?;";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, proid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int caid = rs.getInt(3);
                String description = rs.getString(4);
                String img = rs.getString(5);
                double price = rs.getDouble(6);
                float rate = rs.getFloat(7);
                int brand_id = rs.getInt(8);
                int stockQuantity = rs.getInt(9);
                String publication_date = rs.getString(10);
                pro.add( new Product(id, name, caid, description, img, price, rate, brand_id, stockQuantity, publication_date));
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        }
    }
    
    public void getAllProductPagging(List<Product> newsList, int start, int end) {
        pro = new Vector<Product>();
        for (int i = start; i < end; i++) {
            pro.add(newsList.get(i));
        }
    }

}
