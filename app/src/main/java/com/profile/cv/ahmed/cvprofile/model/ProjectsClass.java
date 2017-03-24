package com.profile.cv.ahmed.cvprofile.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ahmed on 2/23/2017.
 */
public class ProjectsClass implements Parcelable {

    private int ID;
    private int CompanyID;
    private String Picture;
    private String CreatedDate;
    private String ModifiedDate;
    private int State;
    private int ProjectID;
    private int LanguageID;
    private String Name;
    private String Description;

    public ProjectsClass() {
    }

    protected ProjectsClass(Parcel in) {
        ID = in.readInt();
        CompanyID = in.readInt();
        Picture = in.readString();
        CreatedDate = in.readString();
        ModifiedDate = in.readString();
        State = in.readInt();
        ProjectID = in.readInt();
        LanguageID = in.readInt();
        Name = in.readString();
        Description = in.readString();
    }

    public static final Creator<ProjectsClass> CREATOR = new Creator<ProjectsClass>() {
        @Override
        public ProjectsClass createFromParcel(Parcel in) {
            return new ProjectsClass(in);
        }

        @Override
        public ProjectsClass[] newArray(int size) {
            return new ProjectsClass[size];
        }
    };

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

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
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

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int projectID) {
        ProjectID = projectID;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ID);
        parcel.writeInt(CompanyID);
        parcel.writeString(Picture);
        parcel.writeString(CreatedDate);
        parcel.writeString(ModifiedDate);
        parcel.writeInt(State);
        parcel.writeInt(ProjectID);
        parcel.writeInt(LanguageID);
        parcel.writeString(Name);
        parcel.writeString(Description);
    }
}
