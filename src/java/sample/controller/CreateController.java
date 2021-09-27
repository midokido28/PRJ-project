/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.food.FoodDAO;
import sample.food.FoodDTO;
import sample.food.FoodError;

/**
 *
 * @author Suki
 */
public class CreateController extends HttpServlet {

    private static final String ERROR = "create.jsp";
    private static final String SUCCESS = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        FoodError userError = new FoodError();
        try {
            String ID = request.getParameter("id");
            String Name = request.getParameter("name");
            String Description = request.getParameter("description");
            float Price = Float.parseFloat(request.getParameter("price"));
            int CookingTime = Integer.parseInt(request.getParameter("cookingtime"));
            int isDelete = Integer.parseInt(request.getParameter("isdelete"));
            String CreateDate = request.getParameter("createdate");

            boolean checkValidation = true;
            if (ID.length() > 10 || ID.length() < 3) {
                userError.setIDError("ID length. ... bla bla");
                checkValidation = false;
            }
            if(!ID.matches("^F-\\d{3}$")){
                userError.setIDError("ID format: F-000");
               checkValidation = false;
            }
            if (checkValidation) {

                FoodDAO dao = new FoodDAO();
                boolean checkDuplicate = dao.checkDuplicate(ID);
                if (checkDuplicate) {
                    userError.setIDError("Duplicate ID");
                    request.setAttribute("FOOD_ERROR", userError);
                } else {
                    FoodDTO food = new FoodDTO(ID, Name, Description, Price, CookingTime, isDelete, CreateDate);
                    boolean checkInsert = dao.insert(food);
                    if (checkInsert = true) {
                        url = SUCCESS;
                    }
                }
            } else {
                request.setAttribute("FOOD_ERROR", userError);
            }
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static void main(String[] args) {
        String x = "F-111";
        System.out.println(x.matches("^F-\\d{3}$"));
    }

}
