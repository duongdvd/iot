package com.future.iot.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "posts", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class Posts {

    @Id
    @Column(name = "id")
    private int id;


    @Size(max = 100)
    @Column(name = "title")
    private String title;

    @Column(name = "drafters")
    private int draftersId;

    @Column(name = "createDate")
    private Date   createDate;

    @Column(name = "content")
    private String content;

    @Column(name = "theme")
    private String theme;

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

    public int getDraftersId() {
        return draftersId;
    }

    public void setDrafters(int draftersId) {
        this.draftersId = draftersId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
