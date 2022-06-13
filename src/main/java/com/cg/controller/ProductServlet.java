package com.cg.controller;

import com.cg.model.Description;
import com.cg.model.Product;
import com.cg.service.IProductService;
import com.cg.service.IUserService;
import com.cg.service.ProductService;
import com.cg.service.UserService;
import com.cg.utils.exception.NonExistingProduct;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {

    IProductService productService = new ProductService();
    IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().removeAttribute("operation");
        String action = request.getParameter("action");
        if (action == null)
            action = "";
        if (!userService.isAuthorized(request))
            action = "unauthorized";
        switch (action) {
            case "unauthorized":
                processUnauthorizedAccess(request, response);
                break;
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditProductForm(request, response);
                break;
            case "lock":
                lockProduct(request, response);
                break;
            case "unlock":
                unlockProduct(request, response);
                break;
            default:
                renderProductsInclLocked(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "";
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
            default:
                break;
        }
    }

    private void processUnauthorizedAccess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add("Please sign in");
        errorMessages.add("Access denied");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/users");
        request.setAttribute("errorMessages", errorMessages);
        dispatcher.forward(request, response);
    }

    private void renderProductsInclLocked(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/product-list.jsp");

        List<Product> productList = productService.selectAllProductsInclLocked();

        request.setAttribute("productList", productList);

        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/add-product.jsp");

        dispatcher.forward(request, response);
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/add-product.jsp");

        request = productService.processProduct(request);

        dispatcher.forward(request, response);
    }

    private void showEditProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/edit-product.jsp");

        String rawId = request.getParameter("id");
        List<String> errorMessages = new ArrayList<>();
        Product product;
        Description description;

        try {
            int id = Integer.parseInt(rawId);
            if (!productService.existById(id))
                throw new NonExistingProduct("product not exist");
            product = productService.selectProduct(id);
            request = productService.setAttProductDetail(request, product);

            description = productService.selectDescriptionByProductId(id);
            if (description != null)
                request = productService.setAttDescription(request, description);

        } catch (NumberFormatException | NonExistingProduct e) {
            errorMessages.add("Product not found");
            request.setAttribute("errorMessages", errorMessages);
            dispatcher = request.getRequestDispatcher("/products?action=");
            dispatcher.forward(request, response);
            return;
        }

        request.setAttribute("product", product);
        request.setAttribute("description", description);
        request.setAttribute("errorMessages", errorMessages);
        dispatcher.forward(request, response);
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/edit-product.jsp");

        request = productService.modifyProduct(request);

        dispatcher.forward(request, response);
    }

    private void lockProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/product-list.jsp");

        String rawId = request.getParameter("id");
        List<String> errorMessages = new ArrayList<>();
        String operation = null;

        try {
            int id = Integer.parseInt(rawId);
            if (!productService.existById(id))
                throw new NonExistingProduct("product not exist");
            productService.lockProduct(id);
            operation = "Product locked";
        } catch (NumberFormatException | NonExistingProduct e) {
            errorMessages.add("Product not found");
        }

        List<Product> productList = productService.selectAllProductsInclLocked();
        request.setAttribute("operation", operation);
        request.setAttribute("productList", productList);
        request.setAttribute("errorMessages", errorMessages);
        dispatcher.forward(request, response);
    }

    private void unlockProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/product-list.jsp");

        String rawId = request.getParameter("id");
        List<String> errorMessages = new ArrayList<>();

        String operation = null;

        try {
            int id = Integer.parseInt(rawId);
            if (!productService.existById(id))
                throw new NonExistingProduct("product not exist");
            productService.unlockProduct(id);
            operation = "Product unlocked";
        } catch (NumberFormatException | NonExistingProduct e) {
            errorMessages.add("Product not found");
        }

        List<Product> productList = productService.selectAllProductsInclLocked();
        request.setAttribute("operation", operation);
        request.setAttribute("productList", productList);
        request.setAttribute("errorMessages", errorMessages);
        dispatcher.forward(request, response);
    }
}
