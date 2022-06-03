package group.siip.userapi.user.gateway.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserDocument, String> {

    @Query(value = "{'attributes.mobile' : ?0}")
    UserDocument findByMobile(String mobile);

}
