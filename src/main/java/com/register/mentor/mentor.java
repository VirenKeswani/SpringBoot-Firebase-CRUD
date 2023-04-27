package com.register.mentor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class mentor {
    // private String user_id;
    private String phone;
    private String name;
    private String expertise;
    private String email;
    private String linkedin_url;
    private Boolean is_verified;
}
