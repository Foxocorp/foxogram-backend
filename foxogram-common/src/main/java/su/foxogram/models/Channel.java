package su.foxogram.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "channels", indexes = {
		@Index(name = "idx_channel_id", columnList = "id", unique = true),
		@Index(name = "idx_channel_name", columnList = "name", unique = true)
})
public class Channel {
	@Id
	public String id;

	@Column()
	public String name;

	@Column()
	public int type;

	@Column()
	public String ownerId;

	@OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Member> members;

	@OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Message> messages;

	public Channel() {

	}

	public Channel(String id, String name, int type, String ownerId) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.ownerId = ownerId;
	}
}
