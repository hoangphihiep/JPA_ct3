package vn.iotstar.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u from User u")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "username", columnDefinition = "NVARCHAR(200) NOT NULL")
	@NotEmpty(message = "Không được phép rỗng")
	private String username;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "fullname", columnDefinition = "NVARCHAR(200) NOT NULL")
	@NotEmpty(message = "Không được phép rỗng")
	private String fullname;
	
	@Column(name = "images", columnDefinition = "NVARCHAR(200) NOT NULL")
	@NotEmpty(message = "Không được phép rỗng")
	private String images;
	
	@Column(name = "creatdate")
	private Date creatDate;
	
	@Column(name = "phone")
	private String phone;
	
	@ManyToOne
	@JoinColumn(name="roleid")
	private Role role;
}
