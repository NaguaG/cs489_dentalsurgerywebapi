package miu.edu.dentalsurgery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    @NotBlank(message = "First name is required")
    private String firstName;
    @Column(nullable = false)
    @NotBlank(message = "Last name is required")
    private String lastName;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "User name is required")
    private String userName;
    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 8)
    private String password;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is required")
    private String email;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "roleId")}
    )
    private List<Role> roles;

    //Data fields for Userdetails
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private  boolean enabled;

    public User(String userName, String password) {
        this(null, null, null, userName, password,
                null, true, true,
                true, true);
    }

    public User(Long userId, String firstName, String lastName, String userName, String password,
                String email, boolean accountNonExpired, boolean accountNonLocked,
                boolean credentialsNonExpired, boolean enabled) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }


    public String getFullName(){
        return firstName + " " + lastName;
    }
    @Override
    public boolean isAccountNonExpired(){
        return this.accountNonExpired;
    }
    @Override
    public boolean isAccountNonLocked(){
        return this.accountNonLocked;
    }
    @Override
    public boolean isCredentialsNonExpired(){
        return this.accountNonExpired;
    }
    @Override
    public boolean isEnabled(){
        return this.enabled;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] userRoles = getRoles().stream()
                .map((role) -> role.getRoleName())
                .toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

}
