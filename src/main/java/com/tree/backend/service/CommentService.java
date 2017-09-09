
package com.tree.backend.service;

import java.util.List;

import com.tree.backend.model.Comment;

public interface CommentService {

    Comment save(Comment comment);

    Comment findOne(Long commentId);

    List<Comment> findByPhotoId(Long photoId);
}
