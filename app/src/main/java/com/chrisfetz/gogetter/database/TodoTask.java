package com.chrisfetz.gogetter.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * The POJO form of user-created tasks.
 */
@Entity(tableName = "task")
public class TodoTask {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private int color;
    @ColumnInfo(name = "created_at")
    private Date createdAt;
    @ColumnInfo(name = "updated_at")
    private Date updatedAt;

    @Ignore
    public TodoTask(String title, String description, int color, Date createdAt, Date updatedAt){
        this.title = title;
        this.description = description;
        this.color = color;
        this. createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TodoTask(int id, String title, String description, int color, Date createdAt, Date updatedAt){
        this.title = title;
        this.description = description;
        this.color = color;
        this. createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


}
