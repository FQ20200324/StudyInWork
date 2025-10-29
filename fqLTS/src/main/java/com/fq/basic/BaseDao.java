package com.fq.basic;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

@NoRepositoryBean
public interface BaseDao<T, PK> extends ListCrudRepository<T, PK>, PagingAndSortingRepository<T, PK>, QueryByExampleExecutor<T> {
}
