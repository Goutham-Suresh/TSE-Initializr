package com.examplezip.demozip.repository;
import com.examplezip.demozip.model.Dependency;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
@Repository
public interface DependencyRepsitory extends MongoRepository<Dependency, String> {

}
