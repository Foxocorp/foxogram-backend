package su.foxogram.dtos.response;

import lombok.Getter;
import lombok.Setter;
import su.foxogram.models.User;

@Getter
@Setter
public class UserDTO {
    private long id;
    private String avatar;
    private String username;
    private String email;
    private long flags;
    private long type;
    private long createdAt;

    public UserDTO(User user) {
        this.id = user.getId();
        this.avatar = user.getAvatar();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.flags = user.getFlags();
        this.type = user.getType();
        this.createdAt = user.getCreatedAt();
    }
}
