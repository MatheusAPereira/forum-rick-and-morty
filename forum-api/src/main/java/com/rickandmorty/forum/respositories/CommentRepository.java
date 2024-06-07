package com.rickandmorty.forum.respositories;

import com.rickandmorty.forum.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(nativeQuery = true, value="SELECT * FROM comment c WHERE episode_id=?1 ORDER BY c.date_time DESC")
    Page<Comment> findByEpisodeId(Long episodeId, Pageable pageable);
}
