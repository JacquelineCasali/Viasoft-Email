package com.viasoft.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data

public class EmailAwsDTO {

    @NotBlank(message = "Preencha Email do destinatário campo obrigatório ")
    @Email(message = "Informe um email do destinatário válido.")
    @Length(max = 45, message = "O Email do destinatário deve conter no máximo 45 caracteres")
    private String recipient;

    @NotBlank(message = "Preencha o Nome do destinatário campo obrigatório")

    @Length(max = 60, message = "O Nome do destinatário deve conter no máximo 60 caracteres")
    private String recipientName;

    @NotBlank(message = "Preencha Email do Remetente campo obrigatório")
    @Email(message = "Email inválido")
    @Length(max = 45, message = "O Email do remetente deve conter no máximo 45 caracteres")
    private String sender;

    @NotBlank(message = "Preencha o Assunto do Email campo obrigatório")
    @Length(max = 120, message = "O Assunto do email deve conter no máximo 120 caracteres")
    private String subject;

    @NotBlank(message = "Preencha o conteudo campo obrigatório")
    @Length(max = 256, message = "O Conteúdo deve conter no máximo 256 caracteres")
    private String content;

    public EmailAwsDTO() {

    }

    public EmailAwsDTO(String recipient, String recipientName, String sender, String subject, String content) {
        this.recipient = recipient;
        this.recipientName = recipientName;
        this.sender = sender;
        this.subject = subject;
        this.content = content;
    }
}
