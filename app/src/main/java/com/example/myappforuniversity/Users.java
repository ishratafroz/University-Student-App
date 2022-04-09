package com.example.myappforuniversity;

public class Users {
 String id,name,email,image,roll;
    public Users() {
    }
    public Users(String id, String name, String email, String image, String roll) {
        this.email = email; this.id=id; this.name=name; this.image=image; this.roll=roll;
    }
    public String getRoll() {
        return roll;
    }
    public void setRoll(String roll) {
        this.roll = roll;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
