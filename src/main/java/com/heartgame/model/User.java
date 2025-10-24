package com.heartgame.model;

public class User {
    private String id;
    private String name;
    private String virtualIdentity;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getVirtualIdentity() { return virtualIdentity; }
    public void setVirtualIdentity(String virtualIdentity) { this.virtualIdentity = virtualIdentity; }
}