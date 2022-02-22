package com.jwon.oauth2.business.user.domain.entity;

import com.jwon.oauth2.business.user.domain.provider.AuthProvider;
import com.jwon.oauth2.business.user.web.dto.OAuth2UserInfoDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import javax.annotation.security.RolesAllowed;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Document(collection = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class OAuth2UserInfo implements OidcUser, Serializable {

    protected static final long serialVersionUID = -5985744816336986462L;

    @MongoId
    protected ObjectId id;

    protected String name;

    protected String email;

    protected String nickName;

    protected String authProvider; // Naver, Kakao, Google

    protected String image;

    protected String role;

    protected Map<String, Object> attributes;

    protected OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.authProvider = getAuthProviderEnum().getProviderType();
        this.role = Role.GUEST.getKey();
        setAttribute();
    }

    protected abstract void setAttribute();

    protected abstract AuthProvider getAuthProviderEnum();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + getAuthProvider()));
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + getRole()));
//        return Collections.singletonList(new SimpleGrantedAuthority(getRole()));
    }

    @Override
    public Map<String, Object> getClaims() {
        return this.getAttributes();
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return null;
    }

    @Override
    public OidcIdToken getIdToken() {
        return null;
    }

    public OAuth2UserInfo update(final OAuth2UserInfoDto.UpdateDto updateDto) {
        if (updateDto.getNickName() != null) {
            this.nickName = updateDto.getNickName();
        }
        if (updateDto.getImage() != null) {
            if (updateDto.getImage().startsWith("http")) {
                this.image = updateDto.getImage();
            } else {
                this.image = "";
            }
        }
        return this;
    }

    @Secured("ROLE_ADMIN")
    public OAuth2UserInfo beGuest() {
        if (this.role != Role.ADMIN.getKey()) {
            this.role = Role.GUEST.getKey();
        }
        return this;
    }

    @Secured("ROLE_ADMIN")
    public OAuth2UserInfo beUser() {
        if (this.role != Role.ADMIN.getKey()) {
            this.role = Role.USER.getKey();
        }
        return this;
    }

    @Secured("ROLE_ADMIN")
    public OAuth2UserInfo beAdmin() {
        if (this.role != Role.ADMIN.getKey()) {
            this.role = Role.ADMIN.getKey();
        }
        return this;
    }
}
