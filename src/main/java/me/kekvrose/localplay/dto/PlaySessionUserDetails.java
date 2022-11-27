package me.kekvrose.localplay.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.kekvrose.localplay.entity.PlaySessionUser;

@AllArgsConstructor
@NoArgsConstructor
public class PlaySessionUserDetails implements UserDetails {

    private PlaySessionUser playSessionUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return playSessionUser.getRoles()
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return playSessionUser.getPassword();
    }

    @Override
    public String getUsername() {
        return playSessionUser.getUsername();
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
        return playSessionUser.isEnabled();
    }

}
