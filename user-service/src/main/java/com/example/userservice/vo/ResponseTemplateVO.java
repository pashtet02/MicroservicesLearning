package com.example.userservice.vo;


import com.example.userservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private User user;
    private List<Card> cards;
}
