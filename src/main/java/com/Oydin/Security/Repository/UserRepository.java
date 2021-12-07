package com.Oydin.Security.Repository;

import com.Oydin.Security.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByUserName(String userName);
    boolean existsByUserName(String userName);

}
