package j.jav25.security.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "secuser")
public class SecUser {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "name", nullable = false, unique = true)
	    private String name;

	    @Column(name = "username", nullable = false, unique = true)
	    private String username;

	    @Column(nullable = true, unique = true)
	    private String email;

	    @Column(nullable = false)
	    private String password;

											//Cascade: when it saves user, it also saves role
		@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
		@JoinTable(name = "secuser_secrole", 
			joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name ="role_id", referencedColumnName = "id") 
		) 
		private Set<SecRole> secRoles;
		 
}	    