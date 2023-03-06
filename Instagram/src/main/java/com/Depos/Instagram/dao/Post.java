package com.Depos.Instagram.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tlb_post")
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    @Column(name = "created_time")
    private Timestamp createdTime;
    @Column(name = "updated_time")
    private Timestamp updatedTime;
    @Column(name = "post_data")
    private String postData;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
