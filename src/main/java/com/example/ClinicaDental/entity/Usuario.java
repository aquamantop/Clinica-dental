package com.example.ClinicaDental.entity;

import com.example.ClinicaDental.entity.Rol;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "Usuarios")
public class Usuario implements UserDetails {

    // id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // columnas
    @Column
    private String email;
    @Column
    private String clave;

    // rol
    @Enumerated(EnumType.STRING)
    private Rol rol;

    // constructores
    public Usuario() {
    }
    public Usuario(String email, String clave, Rol rol) {
        this.email = email;
        this.clave = clave;
        this.rol = rol;
    }


    // getters y setters
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /*** Sobreescribir metodos ***/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rol.name());
        return Collections.singleton(grantedAuthority);
    }

    // metodos para obtener email y clave
    @Override
    public String getPassword() {
        return clave;
    }
    @Override
    public String getUsername() {
        return email;
    }

    // metodos hardcodeados
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
