package vn.iotstar.entity;

import java.io.Serializable;
import java.util.List;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c from Category c")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategoryId")
	private int categoryId;

	@Column(name = "Categoryname", columnDefinition = "NVARCHAR(200) NOT NULL")
	@NotEmpty(message = "Không được phép rỗng")
	private String categoryname;

	@Column(name = "Images", columnDefinition = "NVARCHAR(MAX) NULL")
	private String images;

	@Column(name = "Status")
	private int status;

	@OneToMany(mappedBy = "category")
	private List<Video> videos;

	public Video addVideo(Video video) {
		getVideos().add(video);
		video.setCategory(this);
		return video;
	}

	public Video removeVideo(Video video) {
		getVideos().remove(video);
		video.setCategory(null);
		return video;
	}

}
