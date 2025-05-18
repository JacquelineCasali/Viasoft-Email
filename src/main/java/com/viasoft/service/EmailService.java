package com.viasoft.service;
import com.viasoft.dto.EmailDTO;
import com.viasoft.enums.Provedor;
import com.viasoft.exception.EmailProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
@Service
@RequiredArgsConstructor
public class EmailService {

    //chamando o servidor
    @Value("${mail.integracao}")
    private String integracao;

      private final EmailAwsService emailAwsService;
    private final EmailOciService emailOciService;
    public void processarEmail(@Valid EmailDTO email) {
        try {
            // Determina o provedor de e-mail com base na configuração
            Provedor provedor = Provedor.from(integracao);

            Emails servicoEnvio;

            switch (provedor) {
                case AWS -> servicoEnvio = emailAwsService;
                case OCI -> servicoEnvio = emailOciService;
                default -> throw new EmailProcessingException("Provedor inválido: " + provedor);
            }
          servicoEnvio.enviarEmail(email);

        } catch (Exception e) {
            throw new EmailProcessingException("Mensagem: " + e.getMessage());
        }
    }
    }