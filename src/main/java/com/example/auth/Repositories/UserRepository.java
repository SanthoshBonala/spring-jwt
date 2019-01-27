package com.example.auth.Repositories;

import com.example.auth.domain.CustomUser;
import jdk.Exported;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<CustomUser, Long> {
    CustomUser findByUsername(String username);

    @Override
    @RestResource(exported = false)
    CustomUser save(CustomUser user);
}
