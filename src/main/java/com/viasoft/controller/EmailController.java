package com.viasoft.controller;

import com.viasoft.dto.EmailDTO;
import com.viasoft.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {


    private final EmailService emailService;

    /**
     * Envia e-mails conforme o provedor configurado (AWS ou OCI).
     * @param email Dados do e-mail a ser enviado
     * @return HTTP 204 se sucesso, 400/500 em caso de erro
     */
    @Operation(summary = "Envia e-mails conforme o provedor configurado (AWS ou OCI).")

    @PostMapping
     public ResponseEntity<Void> enviarEmail(@Valid @RequestBody EmailDTO email) {
        emailService.processarEmail(email);
        return ResponseEntity.noContent().build();
    }
}