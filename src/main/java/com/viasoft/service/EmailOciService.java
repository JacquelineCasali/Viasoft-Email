package com.viasoft.service;

import com.viasoft.dto.EmailDTO;
import com.viasoft.dto.EmailOciDTO;

import com.viasoft.util.JsonUtil;
import com.viasoft.util.ValidacaoUtil;

import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;
import jakarta.validation.Validator;



@Component
public class EmailOciService implements Emails {
    private final Validator validator;

    public EmailOciService(Validator validator) {
        this.validator = validator;
    }

    @Override
  public void enviarEmail(EmailDTO email){


    EmailOciDTO ociDTO = new EmailOciDTO(
            email.getEmailDestinatario(),
            email.getNomeDestinatario(),
            email.getEmailRemetente(),
            email.getAssunto(),
            email.getConteudo()
    );

// Validação

        try {
            ValidacaoUtil.validar(ociDTO, validator);
        } catch (ConstraintViolationException e) {
            // Aqui você pode logar o erro, customizar a mensagem, etc
            System.err.println("Falha na validação do email AWS: " + e.getMessage());
            throw e;
        }


        System.out.println("Enviando e-mail via OCI");

        System.out.println(JsonUtil.serialize(ociDTO));
    }}