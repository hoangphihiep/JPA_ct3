package vn.iotstar.entity;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="videos")
@NamedQuery(name="Video.findAll", query="SELECT v from Video v")
public class Video implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="VideoId")
	private String videoId;
	
	@Column(name="Active")
	private boolean active;
	
	@Column(name="Description",columnDefinition = "NVARCHAR(MAX)")
	private String description;
	
	@Column(name="Poster", columnDefinition = "NVARCHAR(MAX)")
	private String poster;
	
	@Column(name="Title", columnDefinition = "NVARCHAR(MAX)")
	private String title;
	
	@Column(name="Views")
	private String views;

	@ManyToOne
	@JoinColumn(name="CategoryId")
	private Category category;
}
