package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<UserEntry, ObjectId> {
    Optional<UserEntry> findByUsername(String username);

}
