package TecnoTienda.tienda.entity;


import TecnoTienda.tienda.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;



    /**
     * Retorna la lista de roles del usuario. Esta lista es utilizada por Spring Security para verificar
     * los permisos del usuario. Se crea una lista de SimpleGrantedAuthority con el rol del usuario.
     * SimpleGrantedAuthority sirve para representar autoridades (roles) de seguridad.
     * @return Lista de roles del usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Retorna el nombre de usuario del usuario. Este nombre es utilizado por Spring Security para verificar
     * los permisos del usuario. Se retorna el email del usuario.
     * @return
     */
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
