package com.fetch.users.repository;

import com.fetch.users.domain.FranchiseOwner;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by RaoSa on 10/17/2015.
 */
public interface FranchiseRepository extends ElasticsearchRepository<FranchiseOwner, Integer > {
}
