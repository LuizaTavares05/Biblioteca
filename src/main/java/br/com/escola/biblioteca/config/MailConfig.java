package br.com.escola.biblioteca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String para, String assunto, String texto) {

        try {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("seuemail@gmail.com");
            message.setTo(para);
            message.setSubject(assunto);

            message.setText(
                    "Dados da inscrição:\n\n"
                            + texto
                            + "\n\n"
                            + "Serratec Residência de Software"
            );

            javaMailSender.send(message);

            System.out.println("E-mail enviado com sucesso!");

        } catch (MailException e) {

            System.out.println("Erro ao enviar e-mail: "
                    + e.getMessage());

            throw new RuntimeException(
                    "Falha ao enviar e-mail.",
                    e
            );
        }
    }
}