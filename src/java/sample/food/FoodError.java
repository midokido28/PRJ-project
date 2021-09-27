/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.food;

/**
 *
 * @author Suki
 */
public class FoodError {
    private String IDError;
    private String NameError;
    
    
    public FoodError() {
        this.IDError = "";
        this.NameError = "";
    }

    public FoodError(String IDError, String NameError) {
        this.IDError = IDError;
        this.NameError = NameError;
    }

    public String getIDError() {
        return IDError;
    }

    public void setIDError(String IDError) {
        this.IDError = IDError;
    }

    public String getNameError() {
        return NameError;
    }

    public void setNameError(String NameError) {
        this.NameError = NameError;
    }
    
    
}
