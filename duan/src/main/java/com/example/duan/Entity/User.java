package com.example.duan.Entity;

import com.example.duan.Enum.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

//    @Column(name = "username")
    String userName;

    @Column(name = "create_time")
    @Temporal(TemporalType.DATE)
    Date createTime;

    @PrePersist
    protected void onCreate() {
        if (createTime == null) {
            createTime = new Date();
        }
    }

    @Column(name = "avatar")
    String avatar;

    @Column(name = "email")
    String email;

    @Column(name = "update_time")
    @Temporal(TemporalType.DATE)
    Date updateTime;

//    @Column(name = "password")
    String password;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "date_of_birth")
    Date dateOfBirth;

    @Column(name = "is_active")
    boolean isActive = false;


    @Column(name = "address")
    String address;

    @Enumerated(EnumType.STRING)
    Status userStatus;

    @Column(name = "is_locked")
    boolean isLocked = false;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    @JsonIgnore
    District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    @JsonIgnore
    Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    @JsonIgnore
    Ward ward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificate_id")
    @JsonIgnore
    Certificate certificate;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    @JsonIgnore
//    Set<Permission> permissions;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    Set<RefreshToken> refreshTokens;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    Set<Answers> answers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    Set<MakeQuestion> makeQuestions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    Set<ConfirmEmail> confirmEmails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    Set<DoHomeWork> doHomeWorks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    Set<RegisterStudy> registerStudies;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
    @JsonIgnore
    Set<Blog> blogs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    Set<CommentBlog> commentBlogs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    Set<Notification> notifications;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    Set<LearningProgress> learningProgress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    Set<LikeBlog> likeBlogs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private Set<Bill> bills;
}
