package com.cg.service;

import com.cg.model.Description;
import com.cg.model.Product;
import com.cg.utils.MySQLConnectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {

    private static final String SELECT_PRODUCT_BY_ID_SQL =
            "SELECT * FROM products " +
                    "WHERE products.id = ?";
    private static final String SELECT_PRODUCT_WITH_DESCRIPTION_BY_ID =
            "SELECT * FROM shop_manager.vw_all_products_with_description AS v " +
                    "WHERE v.id = ?";
    private static final String SELECT_ALL_PRODUCTS_SQL =
            "SELECT id, product_name, price, quantity, description, category, locked " +
                    "FROM `products` " +
                    "WHERE `products`.`locked` = 0";

    private static final String SELECT_ALL_PRODUCTS_INCLD_LOCKED_SQL =
            "SELECT id, product_name, price, quantity, description, category, locked " +
                    "FROM `products`";
    private static final String SELECT_DESCRIPTION_BY_PRODUCT_ID_SQL =
            "SELECT `id`, `brand`, `mainboard`, `CPU`, `RAM`, `VGA`, `harddrive`, `insurance`" +
                    "FROM shop_manager.vw_all_products_with_description AS v " +
                    "WHERE v.id = ?;";

    private static final String SELECT_ALL_PRODUCTS_WITH_DESCRIPTION =
            "SELECT * FROM shop_manager.vw_all_products_with_description;";
    private static final String INSERT_PRODUCT_SQL =
            "CALL shop_manager.sp_insert_new_product(?, ?, ?, ?, ?, ?)";
    private static final String INSERT_DESCRIPTION_SQL =
            "CALL shop_manager.sp_insert_new_description(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String LOCK_PRODUCT_SQL = "CALL shop_manager.sp_lock_product(?)";
    private static final String UNLOCK_PRODUCT_SQL = "CALL shop_manager.sp_unlock_product(?)";
    private static final String CHECK_PRODUCT_IF_LOCKED =
            "CALL shop_manager.sp_check_product_if_locked(?, ?);";
    private static final String UPDATE_PRODUCT_SQL =
            "CALL shop_manager.sp_update_product(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_DESCRIPTION_SQL =
            "CALL shop_manager.sp_update_description(?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public Product selectProduct(int id) {
        Product product = null;
        try {
            Connection conn = MySQLConnectionUtils.getConnection();
            CallableStatement cs = conn.prepareCall(SELECT_PRODUCT_BY_ID_SQL);
            cs.setInt(1, id);

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String name = rs.getString("product_name");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity =rs.getInt("quantity");
                int description = rs.getInt("description");
                String category = rs.getString("category");
                boolean locked = rs.getBoolean("locked");

                product = new Product(id, name, price, quantity, description, category, locked);
            }

        } catch (SQLException e) {
            MySQLConnectionUtils.printSQLException(e);
        }
        return product;
    }

    @Override
    public boolean existById(int id) {
        return selectProduct(id) != null;
    }

    @Override
    public List<Product> selectAllProducts() {
        List<Product> productList = new ArrayList<>();
        try {
            Connection conn = MySQLConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_PRODUCTS_SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("product_name");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity = rs.getInt("quantity");
                String category = rs.getString("category");
                boolean locked = rs.getBoolean("locked");

                productList.add(new Product(id, name, price, quantity, category, locked));
            }

        } catch (SQLException e) {
            MySQLConnectionUtils.printSQLException(e);
        }
        return productList;
    }

    @Override
    public List<Product> selectAllProductsInclLocked() {
        List<Product> productList = new ArrayList<>();
        try {
            Connection conn = MySQLConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_PRODUCTS_INCLD_LOCKED_SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("product_name");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity = rs.getInt("quantity");
                String category = rs.getString("category");
                boolean locked = rs.getBoolean("locked");

                productList.add(new Product(id, name, price, quantity, category, locked));
            }

        } catch (SQLException e) {
            MySQLConnectionUtils.printSQLException(e);
        }
        return productList;
    }

    @Override
    public Description selectDescriptionByProductId(int id) {
        Description description = null;
        try {
            Connection conn = MySQLConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_DESCRIPTION_BY_PRODUCT_ID_SQL);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int descriptionId = rs.getInt("id");
                String brand = rs.getString("brand");
                String mainboard = rs.getString("mainboard");
                String CPU = rs.getString("CPU");
                String RAM = rs.getString("RAM");
                String VGA = rs.getString("VGA");
                String harddrive = rs.getString("harddrive");
                BigDecimal insurance = rs.getBigDecimal("insurance");

                description = new Description(descriptionId, brand, mainboard, CPU, RAM, VGA, harddrive, insurance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return description;
    }

    @Override
    public boolean insertProduct(Product newProduct, Description newDescription) {
        boolean isInserted = false;
        int newId;
        try {
            Connection conn = MySQLConnectionUtils.getConnection();
            CallableStatement cs1 = conn.prepareCall(INSERT_PRODUCT_SQL);
            cs1.setString(1, newProduct.getName());
            cs1.setBigDecimal(2, newProduct.getPrice());
            cs1.setInt(3, newProduct.getQuantity());
            cs1.setString(4, newProduct.getCategory());

            cs1.execute();
            isInserted = cs1.getBoolean(5);
            newId = cs1.getInt(6);

            if (newDescription == null)
                return isInserted;

            CallableStatement cs2 = conn.prepareCall(INSERT_DESCRIPTION_SQL);
            cs2.setString(1, newDescription.getBrand());
            cs2.setString(2, newDescription.getMainboard());
            cs2.setString(3, newDescription.getCPU());
            cs2.setString(4, newDescription.getRAM());
            cs2.setString(5, newDescription.getVGA());
            cs2.setString(6, newDescription.getHarddrive());
            cs2.setBigDecimal(7, newDescription.getInsurance());
            cs2.setInt(8, newId);

            cs2.execute();
        } catch (SQLException e) {
            MySQLConnectionUtils.printSQLException(e);
        }
        return isInserted;
    }

    @Override
    public boolean updateProduct(Product updatedProduct, Description updatedDescription) {
        boolean isUpdated = false;
        try {
            Connection conn = MySQLConnectionUtils.getConnection();
            CallableStatement cs1 = conn.prepareCall(UPDATE_PRODUCT_SQL);
            cs1.setInt(1, updatedProduct.getId());
            cs1.setString(2, updatedProduct.getName());
            cs1.setBigDecimal(3, updatedProduct.getPrice());
            cs1.setInt(4, updatedProduct.getQuantity());
            cs1.setString(5, updatedProduct.getCategory());

            cs1.execute();

            isUpdated = cs1.getBoolean(6);

            if (updatedDescription == null)
                return isUpdated;

            CallableStatement cs2 = conn.prepareCall(UPDATE_DESCRIPTION_SQL);
            cs2.setInt(1, updatedDescription.getId());
            cs2.setString(2, updatedDescription.getBrand());
            cs2.setString(3, updatedDescription.getMainboard());
            cs2.setString(4, updatedDescription.getCPU());
            cs2.setString(5, updatedDescription.getRAM());
            cs2.setString(6, updatedDescription.getVGA());
            cs2.setString(7, updatedDescription.getHarddrive());
            cs2.setBigDecimal(8, updatedDescription.getInsurance());

            cs2.execute();
        } catch (SQLException e) {
            MySQLConnectionUtils.printSQLException(e);
        }
        return isUpdated;
    }

    @Override
    public void lockProduct(int id) {
        try {
            Connection conn = MySQLConnectionUtils.getConnection();
            CallableStatement cs = conn.prepareCall(LOCK_PRODUCT_SQL);
            cs.setInt(1, id);

            cs.execute();
        } catch (SQLException e) {
            MySQLConnectionUtils.printSQLException(e);
        }
    }

    @Override
    public void unlockProduct(int id) {
        try {
            Connection conn = MySQLConnectionUtils.getConnection();
            CallableStatement cs = conn.prepareCall(UNLOCK_PRODUCT_SQL);
            cs.setInt(1, id);

            cs.execute();
        } catch (SQLException e) {
            MySQLConnectionUtils.printSQLException(e);
        }
    }

    @Override
    public boolean isLocked(int id) {
        boolean isLocked = false;
        try {
            Connection conn = MySQLConnectionUtils.getConnection();
            CallableStatement cs = conn.prepareCall(CHECK_PRODUCT_IF_LOCKED);
            cs.setInt(1, id);

            cs.execute();

            isLocked = cs.getBoolean(2);
        } catch (SQLException e) {
            MySQLConnectionUtils.printSQLException(e);
        }
        return isLocked;
    }

    @Override
    public HttpServletRequest processProduct(HttpServletRequest request) {
        List<String> errorMessages = new ArrayList<>();
        BigDecimal price = BigDecimal.ZERO;
        int quantity = 0;
        BigDecimal insurance = null;
        String operation = null;

        String name = request.getParameter("productName");
        String rawPrice = request.getParameter("productPrice");
        String rawQuantity = request.getParameter("productQuantity");
        String category = request.getParameter("productCategory");

        String brand = request.getParameter("brand");
        String mainboard = request.getParameter("mainboard");
        String CPU = request.getParameter("cpu");
        String RAM = request.getParameter("ram");
        String VGA = request.getParameter("vga");
        String harddrive = request.getParameter("harddrive");
        String rawInsurance = request.getParameter("insurance");

        if (name.equals(""))
            errorMessages.add("Name cannot be empty.");
        request.setAttribute("name", name);

        if (category.equals(""))
            errorMessages.add("Category cannot be empty.");
        request.setAttribute("category", category);

        if (rawPrice.equals(""))
            errorMessages.add("Price cannot be empty.");
        request.setAttribute("price", rawPrice);

        if (rawQuantity.equals(""))
            errorMessages.add("Quantity cannot be empty.");
        request.setAttribute("quantity", rawQuantity);

        request.setAttribute("brand", brand);
        request.setAttribute("mainboard", mainboard);
        request.setAttribute("CPU", CPU);
        request.setAttribute("RAM", RAM);
        request.setAttribute("VGA", VGA);
        request.setAttribute("harddrive", harddrive);
        request.setAttribute("insurance", rawInsurance);

        if (errorMessages.size() > 0) {
            request.setAttribute("errorMessages", errorMessages);
            return request;
        }

        //parsing
        try {
            price = new BigDecimal(rawPrice);
        } catch (NumberFormatException nfe) {
            errorMessages.add("Price invalid");
        }
        try {
            quantity = Integer.parseInt(rawQuantity);
        } catch (NumberFormatException nfe) {
            errorMessages.add("Quantity invalid");
        }
        try {
            if (!rawInsurance.equals(""))
                insurance = new BigDecimal(rawInsurance);
        } catch (NumberFormatException nfe) {
            errorMessages.add("Insurance invalid");
        }
        if (errorMessages.size() > 0) {
            request.setAttribute("errorMessages", errorMessages);
            return request;
        }

        //validate with personalized conditions
        if (name.length() < 6 || name.length() > 20)
            errorMessages.add("Name length must be between 6 and 20 chars");
        if (price.compareTo(new BigDecimal(20000)) < 0 || price.compareTo(new BigDecimal(100000000)) > 0)
            errorMessages.add("Price must be between 20.000vnd and 100.000.000vnd");
        if (quantity < 1 || quantity > 5000)
            errorMessages.add("Quantity must be between 1 and 5000");
        if (category.length() < 3 || category.length() > 10)
            errorMessages.add("Category length must be between 3 and 10 chars");

        if (!brand.equals("") || !mainboard.equals("") || !CPU.equals("") || !RAM.equals("") || !VGA.equals("")
                || !harddrive.equals("") || !rawInsurance.equals("")) {
            if (!brand.equals(""))
                if (brand.length() < 4 || brand.length() > 20)
                    errorMessages.add("Brand length must be between 4 and 20 chars");
            if (!mainboard.equals(""))
                if (mainboard.length() < 4 || mainboard.length() > 20)
                    errorMessages.add("Mainboard length must be between 4 and 20 chars");
            if (!CPU.equals(""))
                if (CPU.length() < 4 || CPU.length() > 20)
                    errorMessages.add("CPU length must be between 4 and 20");
            if (!RAM.equals(""))
                if (RAM.length() < 4 || RAM.length() > 20)
                    errorMessages.add("RAM length must be between 4 and 20");
            if (!VGA.equals(""))
                if (VGA.length() < 4 || VGA.length() > 20)
                    errorMessages.add("VGA length must be between 4 and 20");
            if (!harddrive.equals(""))
                if (harddrive.length() < 4 || harddrive.length() > 20)
                    errorMessages.add("Hard Drive length must be between 4 and 20 chars");
            if (!rawInsurance.equals(""))
                if (insurance.compareTo(BigDecimal.ZERO) < 0 || insurance.compareTo(new BigDecimal(120)) > 0)
                    errorMessages.add("Insurance length must be between 4 and 20 chars");
        }

        if (errorMessages.size() > 0) {
            request.setAttribute("errorMessages", errorMessages);
            return request;
        }

        //insertion
        Product newProduct = new Product(name, price, quantity, category);
        Description newDescription = new Description(brand, mainboard, CPU, RAM, VGA, harddrive, insurance);
        if (!insertProduct(newProduct, newDescription))
            errorMessages.add("An error has occurred!");
        operation = "Product added.";
        request.setAttribute("operation", operation);
        request.setAttribute("errorMessages", errorMessages);
        return request;
    }

    @Override
    public HttpServletRequest modifyProduct(HttpServletRequest request) {
        String operation = null;
        List<String> errorMessages = new ArrayList<>();
        BigDecimal price = BigDecimal.ZERO;
        int quantity = 0;
        BigDecimal insurance = null;

        int productId = Integer.parseInt(request.getParameter("id"));
        int descriptionId = selectProduct(productId).getDescription();

        String name = request.getParameter("productName");
        String rawPrice = request.getParameter("productPrice");
        String rawQuantity = request.getParameter("productQuantity");
        String category = request.getParameter("productCategory");

        String brand = request.getParameter("brand");
        String mainboard = request.getParameter("mainboard");
        String CPU = request.getParameter("cpu");
        String RAM = request.getParameter("ram");
        String VGA = request.getParameter("vga");
        String harddrive = request.getParameter("harddrive");
        String rawInsurance = request.getParameter("insurance");

        if (name.equals(""))
            errorMessages.add("Name cannot be empty.");
        request.setAttribute("name", name);

        if (category.equals(""))
            errorMessages.add("Category cannot be empty.");
        request.setAttribute("category", category);

        if (rawPrice.equals(""))
            errorMessages.add("Price cannot be empty.");
        request.setAttribute("price", rawPrice);

        if (rawQuantity.equals(""))
            errorMessages.add("Quantity cannot be empty.");
        request.setAttribute("quantity", rawQuantity);

        request.setAttribute("brand", brand);
        request.setAttribute("mainboard", mainboard);
        request.setAttribute("CPU", CPU);
        request.setAttribute("RAM", RAM);
        request.setAttribute("VGA", VGA);
        request.setAttribute("harddrive", harddrive);
        request.setAttribute("insurance", rawInsurance);

        if (errorMessages.size() > 0) {
            request.setAttribute("errorMessages", errorMessages);
            return request;
        }

        //parsing
        try {
            price = new BigDecimal(rawPrice);
        } catch (NumberFormatException nfe) {
            errorMessages.add("Price invalid");
        }
        try {
            quantity = Integer.parseInt(rawQuantity);
        } catch (NumberFormatException nfe) {
            errorMessages.add("Quantity invalid");
        }
        try {
            if (!rawInsurance.equals(""))
                insurance = new BigDecimal(rawInsurance);
        } catch (NumberFormatException nfe) {
            errorMessages.add("Insurance invalid");
        }
        if (errorMessages.size() > 0) {
            request.setAttribute("errorMessages", errorMessages);
            return request;
        }

        //validate with personalized conditions
        if (name.length() < 6 || name.length() > 20)
            errorMessages.add("Name length must be between 6 and 20 chars");
        if (price.compareTo(new BigDecimal(20000)) < 0 || price.compareTo(new BigDecimal(100000000)) > 0)
            errorMessages.add("Price must be between 20.000vnd and 100.000.000vnd");
        if (quantity < 1 || quantity > 5000)
            errorMessages.add("Quantity must be between 1 and 5000");
        if (category.length() < 3 || category.length() > 10)
            errorMessages.add("Category length must be between 3 and 10 chars");

        if (!brand.equals("") || !mainboard.equals("") || !CPU.equals("") || !RAM.equals("") || !VGA.equals("")
                || !harddrive.equals("") || !rawInsurance.equals("")) {
            if (!brand.equals(""))
                if (brand.length() < 4 || brand.length() > 20)
                    errorMessages.add("Brand length must be between 4 and 20 chars");
            if (!mainboard.equals(""))
                if (mainboard.length() < 4 || mainboard.length() > 20)
                    errorMessages.add("Mainboard length must be between 4 and 20 chars");
            if (!CPU.equals(""))
                if (CPU.length() < 4 || CPU.length() > 20)
                    errorMessages.add("CPU length must be between 4 and 20");
            if (!RAM.equals(""))
                if (RAM.length() < 4 || RAM.length() > 20)
                    errorMessages.add("RAM length must be between 4 and 20");
            if (!VGA.equals(""))
                if (VGA.length() < 4 || VGA.length() > 20)
                    errorMessages.add("VGA length must be between 4 and 20");
            if (!harddrive.equals(""))
                if (harddrive.length() < 4 || harddrive.length() > 20)
                    errorMessages.add("Hard Drive length must be between 4 and 20 chars");
            if (!rawInsurance.equals(""))
                if (insurance.compareTo(BigDecimal.ZERO) < 0 || insurance.compareTo(new BigDecimal(120)) > 0)
                    errorMessages.add("Insurance length must be between 4 and 20 chars");
        }

        if (errorMessages.size() > 0) {
            request.setAttribute("errorMessages", errorMessages);
            return request;
        }

        //update
        Product updatedProduct = new Product(productId, name, price, quantity, category);
        Description updatedDescription = new Description(descriptionId, brand, mainboard, CPU, RAM, VGA, harddrive, insurance);
        if (!updateProduct(updatedProduct, updatedDescription))
            errorMessages.add("An error has occurred.");
        operation = "Product updated";
        request.setAttribute("operation", operation);
        request.setAttribute("errorMessages", errorMessages);
        return request;
    }

    @Override
    public HttpServletRequest setAttProductDetail(HttpServletRequest request, Product product) {
        request.setAttribute("name", product.getName());
        request.setAttribute("price", product.getPrice());
        request.setAttribute("quantity", product.getQuantity());
        request.setAttribute("category", product.getCategory());

        return request;
    }

    @Override
    public HttpServletRequest setAttDescription(HttpServletRequest request, Description description) {
        request.setAttribute("brand", description.getBrand());
        request.setAttribute("mainboard", description.getBrand());
        request.setAttribute("CPU", description.getCPU());
        request.setAttribute("RAM", description.getRAM());
        request.setAttribute("VGA", description.getVGA());
        request.setAttribute("harddrive", description.getHarddrive());
        request.setAttribute("insurance", description.getInsurance());

        return request;
    }
}
