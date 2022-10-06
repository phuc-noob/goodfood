package com.example.goodfood.dto.request;

import com.example.goodfood.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String phone;
    private String email;
    private float money;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createAt;
    public UserDto(User user)
    {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.name = user.getName();
        this.money = user.getMoney();
        this.createAt = user.getCreateAt();
    }
}
