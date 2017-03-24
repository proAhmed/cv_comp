package com.profile.cv.ahmed.cvprofile.model;

/**
 * Created by ahmed on 2/23/2017.
 */
public class BranchesClass {

    private int ID;
    private int CompanyID;
    private String CreatedDate;
    private String ModifiedDate;
    private int State;
    private int BranchID;
    private int LanguageID;
    private String Name;

    public BranchesClass() {
    }

    public BranchesClass(int ID, int companyID, String createdDate, String modifiedDate, int state, int branchID, int languageID, String name) {
        this.ID = ID;
        CompanyID = companyID;
        CreatedDate = createdDate;
        ModifiedDate = modifiedDate;
        State = state;
        BranchID = branchID;
        LanguageID = languageID;
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(int companyID) {
        CompanyID = companyID;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public int getBranchID() {
        return BranchID;
    }

    public void setBranchID(int branchID) {
        BranchID = branchID;
    }

    public int getLanguageID() {
        return LanguageID;
    }

    public void setLanguageID(int languageID) {
        LanguageID = languageID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
