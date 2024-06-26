package com.ceos19.springboot.comment.entity;

import com.ceos19.springboot.common.BaseEntity;
import com.ceos19.springboot.post.entity.Post;
import com.ceos19.springboot.reply.entity.Reply;
import com.ceos19.springboot.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    private Integer contentLike;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment",orphanRemoval = true) //, cascade = CascadeType.REMOVE
    private List<Reply> replyList = new ArrayList<>();


    public Comment(String content, User user, Post post) {

        this.content = content;
        this.user = user;
        this.post = post;
    }
}

