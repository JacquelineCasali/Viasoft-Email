package com.viasoft.service;

import com.viasoft.dto.EmailDTO;

import com.viasoft.exception.EmailProcessingException;
import com.viasoft.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {
    @InjectMocks
    private EmailService emailService;


    @Mock
    private EmailAwsService emailAwsService;

    @Mock
    private EmailOciService emailOciService;

    private EmailDTO email;
    @BeforeEach
    void setup() {
        email = new EmailDTO();
        email.setEmailDestinatario("usuario@teste.com");
        email.setNomeDestinatario("Usuário Teste");
        email.setEmailRemetente("noreply@empresa.com");
        email.setAssunto("Assunto de Teste");
        email.setConteudo("Conteúdo do email.");
    }

    @Test
    void deveUsarAwsQuandoConfiguradoComoAws() {
        TestUtils.setField(emailService, "integracao", "AWS");

        // Arrange
        emailService.processarEmail(email);

        verify(emailAwsService).enviarEmail(email);
        verifyNoInteractions(emailOciService);

         }

    @Test
    void deveUsarOciQuandoConfiguradoComoOci() {
        // Arrange
        TestUtils.setField(emailService, "integracao", "OCI");

        // Act
        emailService.processarEmail(email);

        // Assert
        verify(emailOciService).enviarEmail(email);
        verify(emailAwsService, never()).enviarEmail(any());
    }


    @Test
    void deveLancarExcecaoQuandoIntegracaoForDesconhecida() {
        // Simula propriedade inválida
        TestUtils.setField(emailService, "integracao", "INVALIDO");

             EmailProcessingException ex = assertThrows(
                EmailProcessingException.class,
                () -> emailService.processarEmail(new EmailDTO())
        );

        assertTrue(ex.getMessage().toLowerCase().contains("inválido"));
        assertTrue(ex.getMessage().contains("INVALIDO"));
    }

    @Test
    void deveLancarExcecaoAoFalharEnvioComAws() {
        TestUtils.setField(emailService, "integracao", "AWS");
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmailDestinatario("erro@teste.com");
        doThrow(new RuntimeException("Falha de envio")).when(emailAwsService).enviarEmail(emailDTO);

        EmailProcessingException ex = assertThrows(
                EmailProcessingException.class,
                () -> emailService.processarEmail(emailDTO)
        );
        assertNotNull(ex.getMessage());
        assertTrue(ex.getMessage().toLowerCase().contains("falha de envio"));

    }
}
