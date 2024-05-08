package com.example.duan.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "blog")
@Data
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "title")
    private String title;

    @Column(name = "number_of_likes")
    private int numberOfLikes;

    @Column(name = "number_of_comments")
    private int numberOfComments;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "blog")
    @JsonIgnore
    private Set<LikeBlog> likeBlogs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "blog")
    @JsonIgnore
    private Set<CommentBlog> commentBlogs;
}
