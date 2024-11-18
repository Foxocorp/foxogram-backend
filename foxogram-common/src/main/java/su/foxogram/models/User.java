package su.foxogram.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends BaseUser {

    @Column()
    private String email;

    @Column()
    private boolean emailVerified;

    @Column()
    private String password;

    @Column()
    private String refreshToken;

    @Column()
    private long deletion;

    @Column()
    private boolean disabled;

    @Column()
    private boolean mfaEnabled;

    public User() {
    }

    public User(long id, String avatar, String username, String email, boolean emailVerified, String password, String accessToken, String refreshToken, long createdAt, long flags, int type, long deletion, boolean disabled, boolean mfaEnabled) {
        super(id, avatar, username, accessToken, createdAt, flags, type);
        this.id = id;
        this.avatar = avatar;
        this.username = username;
        this.email = email;
        this.emailVerified = emailVerified;
        this.password = password;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.flags = flags;
        this.type = type;
        this.createdAt = createdAt;
        this.deletion = deletion;
        this.disabled = disabled;
        this.mfaEnabled = mfaEnabled;
    }
}
