package com.viasoft.service;

import com.viasoft.dto.EmailAwsDTO;
import com.viasoft.dto.EmailDTO;
import com.viasoft.util.JsonUtil;
import com.viasoft.util.ValidacaoUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EmailAwsService implements Emails {
    private final Validator validator;

    @Override
    public void enviarEmail(EmailDTO email){
//Map servidor aws
             EmailAwsDTO awsDTO = new EmailAwsDTO(
                email.getEmailDestinatario(),
                email.getNomeDestinatario(),
                email.getEmailRemetente(),
                email.getAssunto(),
                email.getConteudo()
        );
        //validação
        try {
            ValidacaoUtil.validar(awsDTO, validator);
        } catch (ConstraintViolationException e) {
            // Aqui você pode logar o erro, customizar a mensagem, etc
            System.err.println("Falha na validação do email AWS: " + e.getMessage());
            throw e;
        }
        System.out.println("Enviando email via AWS:");
        System.out.println(JsonUtil.serialize(awsDTO));


    }
    public EmailAwsService(Validator validator) {
        this.validator = validator;
    }

}
