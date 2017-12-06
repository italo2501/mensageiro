package com.github.welingtonveiga.mensageiro.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String author;
    private String message;
    @SerializedName("created_at")
    private Date createdAt;
    private Boolean liked;

    public Message(Long id, String author, String message, Date createdAt) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Message(String author, String message) {
        this(null, author, message, new Date());
    }

    public Message() {
        this(null, null, null, null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (id != null ? !id.equals(message1.id) : message1.id != null) return false;
        if (author != null ? !author.equals(message1.author) : message1.author != null)
            return false;
        if (message != null ? !message.equals(message1.message) : message1.message != null)
            return false;
        if (createdAt != null ? !createdAt.equals(message1.createdAt) : message1.createdAt != null)
            return false;
        return liked != null ? liked.equals(message1.liked) : message1.liked == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (liked != null ? liked.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", liked=" + liked +
                '}';
    }

    public boolean isLiked() {
        return liked != null && liked;
    }
}