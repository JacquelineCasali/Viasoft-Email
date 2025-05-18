package com.viasoft.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailDTO {
    @NotBlank(message = "Preencha Email do destinatário campo obrigatório ")
    @Email(message = "Informe um email válido.")

    private String emailDestinatario;
    @NotBlank(message = "Preencha o Nome do destinatário campo obrigatório")
    private String nomeDestinatario;
    @NotBlank(message = "Preencha Email do Remetente campo obrigatório")
    @Email(message = "Email inválido")
    private String emailRemetente;
    @NotBlank(message = "Preencha o Assunto campo obrigatório")
    private String assunto;
    @NotBlank(message = "Preencha o conteúdo  campo obrigatório")
    private String conteudo;

    public EmailDTO() {

    }

    public EmailDTO(String emailDestinatario, String nomeDestinatario, String emailRemetente, String assunto, String conteudo) {
        this.emailDestinatario = emailDestinatario;
        this.nomeDestinatario = nomeDestinatario;
        this.emailRemetente = emailRemetente;
        this.assunto = assunto;
        this.conteudo = conteudo;
    }
}
