package com.qph.springmicorservices.photoapp.api.users.data;

import com.qph.springmicorservices.photoapp.api.users.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
