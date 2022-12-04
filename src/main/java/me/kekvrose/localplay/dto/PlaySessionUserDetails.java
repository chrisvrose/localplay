package me.kekvrose.localplay.dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kekvrose.localplay.entity.PlaySessionUser;
import me.kekvrose.localplay.utils.Constants;

@AllArgsConstructor
@NoArgsConstructor
public class PlaySessionUserDetails implements UserDetails {
    @Getter
    private PlaySessionUser playSessionUser;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return playSessionUser.getRoles()
                .stream().map(Constants.ROLE_PREFIX_STRING::concat).map(SimpleGrantedAuthority::new)
                .toList();
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
