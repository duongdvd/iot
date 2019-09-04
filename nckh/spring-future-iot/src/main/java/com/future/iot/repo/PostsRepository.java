package com.future.iot.repo;

import com.future.iot.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Integer> {
}
