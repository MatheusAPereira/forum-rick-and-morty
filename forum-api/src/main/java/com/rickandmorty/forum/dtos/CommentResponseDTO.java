package com.rickandmorty.forum.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDTO implements Serializable {

    String id;
    String userName;
    String content;
    String date;

    public CommentResponseDTO() {
    }

    public CommentResponseDTO(String id, String userName, String content, String date) {
        this.id = id;
        this.userName = userName;
        this.content = content;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CommentResponseDTO{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
