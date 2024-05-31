package com.example.duan.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class MailBodyRequest{
    String to;
    String subject;
    String text;
}
