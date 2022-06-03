package group.siip.userapi.user.gateway.mongo;

import group.siip.userapi.user.domain.User;
import group.siip.userapi.user.gateway.UserGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;

@Slf4j
public class UserGatewayMongoDb implements UserGateway {

    private UserRepository userRepository;
    private UserDocumentMapper userDocumentMapper;

    public UserGatewayMongoDb(UserRepository userRepository, UserDocumentMapper userDocumentMapper) {
        this.userRepository = userRepository;
        this.userDocumentMapper = userDocumentMapper;
    }

    @Override
    public User get(String mobile) {
        return userDocumentMapper.from(userRepository.findByMobile(mobile));
    }

    @Override
    public User create(User user) {
        try {
            return userDocumentMapper.from(userRepository.insert(userDocumentMapper.to(user)));
        } catch (DuplicateKeyException e) {
            log.error("Could not create user document due to duplicate key.");
            throw e;
        }
    }

    @Override
    public User update(User user) {
        try {
            return userDocumentMapper.from(userRepository.save(userDocumentMapper.to(user)));
        } catch (DuplicateKeyException e) {
            log.error("Could not create user document due to duplicate key.");
            throw e;
        }
    }

    @Override
    public void delete(User user) {
        userRepository.delete(userDocumentMapper.to(user));
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
