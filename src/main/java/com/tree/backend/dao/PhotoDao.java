
package com.tree.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tree.backend.model.Photo;
import com.tree.backend.model.User;

@Repository
public interface PhotoDao extends CrudRepository<Photo, Long> {

    @Override
    Photo save(Photo photo);

    List<Photo> findByUser(User user);

    Photo findByPhotoId(Long photoId);

    @Query("from Photo where deleted=0 order by created desc")
    List<Photo> findAll();

    @Query("from Photo where user_user_id=?1 and deleted=0 order by created desc")
    List<Photo> findByUserId(Long userId);

}
