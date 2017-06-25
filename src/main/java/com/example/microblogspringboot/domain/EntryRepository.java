package com.example.microblogspringboot.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


/**
 * Created by scottmcallister on 2017-06-24.
 */
public interface EntryRepository extends CrudRepository<Entry, Long> {
    List<Entry> findAll();
    List<Entry> findByTitle(String title);
}
