package com.rickandmorty.forum.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="comment")
@JsonIgnoreProperties({"episode"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="user_name")
    String userName;

    @Column(name="content")
    String content;

    @Column(name="date_time")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="episode_id")
    Episode episode;

    public Comment() {
    }

    public Comment(Long id, String userName, String content, LocalDateTime date, Episode episode) {
        this.id = id;
        this.userName = userName;
        this.content = content;
        this.date = date;
        this.episode = episode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
}
