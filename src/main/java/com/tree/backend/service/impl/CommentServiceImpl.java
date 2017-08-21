
package com.tree.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tree.backend.dao.CommentDao;
import com.tree.backend.model.Comment;
import com.tree.backend.service.CommentService;

@Service
@Transactional( propagation = Propagation.SUPPORTS, readOnly = true )
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	@Transactional( propagation = Propagation.REQUIRED, readOnly = false )
	//@CachePut( value = "tree", key = "#result.commentId" )
	public Comment save (Comment comment) {
		return commentDao.save(comment);
	}

	@Override
	@Cacheable( value = "tree", key = "#commentId" )
	public Comment findOne (Long commentId) {
		return commentDao.findOne(commentId);
	}

	@Override
	public List<Comment> findByPhotoId (Long photoId) {
		return commentDao.findByPhotoId(photoId);
	}

}
