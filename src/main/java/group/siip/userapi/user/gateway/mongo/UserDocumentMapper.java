package group.siip.userapi.user.gateway.mongo;

import group.siip.userapi.user.domain.User;

public class UserDocumentMapper {

    public UserDocument to(User user) {
        UserDocument userDocument = new UserDocument();
        userDocument.setMobile(user.getMobile());
        userDocument.setFirst(user.getFirst());
        userDocument.setLast(user.getLast());
        userDocument.setKeycloakId(user.getKeycloakId());
        return userDocument;
    }

    public User from(UserDocument userDocument) {
        if (userDocument == null) {
            return null;
        }
        return User.builder()
                .mobile(userDocument.getMobile())
                .first(userDocument.getFirst())
                .last(userDocument.getLast())
                .keycloakId(userDocument.getKeycloakId())
                .build();
    }
}
