
package com.tree.backend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tree.backend.model.Comment;

@Repository
public interface CommentDao extends CrudRepository<Comment, Long> {

    @Override
    Comment save(Comment comment);

    @Override
    Comment findOne(Long commentId);

    List<Comment> findByPhotoId(Long photoId);
}
