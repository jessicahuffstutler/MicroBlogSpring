package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by jessicahuffstutler on 11/10/15.
 */
public interface MicroBlogRepository extends CrudRepository<Message, Integer> {
}
