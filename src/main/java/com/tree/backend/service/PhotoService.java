
package com.tree.backend.service;

import java.util.List;

import com.tree.backend.model.Photo;
import com.tree.backend.model.User;

public interface PhotoService {

	Photo save (Photo photo);

	List<Photo> findByUser (User user);

	Photo findByPhotoId (Long photoId);
	
	List<Photo> findAll();
	
	List<Photo> findByUserId (Long userId);

}
