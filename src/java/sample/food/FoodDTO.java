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
public class FoodDTO {
    private String ID;
    private String Name;
    private String Description;
    private Float Price;
    private int CookingTime;
    private int isDelete;
    private String CreateDate;

    public FoodDTO() {
    }

    public FoodDTO(String ID, String Name, String Description, Float Price, int CookingTime, int isDelete, String CreateDate) {
        this.ID = ID;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.CookingTime = CookingTime;
        this.isDelete = isDelete;
        this.CreateDate = CreateDate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float Price) {
        this.Price = Price;
    }

    public int getCookingTime() {
        return CookingTime;
    }

    public void setCookingTime(int CookingTime) {
        this.CookingTime = CookingTime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    
    
}
