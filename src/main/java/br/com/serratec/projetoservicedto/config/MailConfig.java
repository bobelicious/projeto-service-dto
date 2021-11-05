package br.com.serratec.projetoservicedto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {
    @Autowired
    JavaMailSender javaMailSender;

    
    public void enviarEmail(String para, String assunto, String texto){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("augusto.p@estudante.firjan.senai.br");
        simpleMailMessage.setTo(para);
        simpleMailMessage.setSubject(assunto);
        simpleMailMessage.setText("dados da inscrição do usiario" + "\n Serratec Residencia - 2021\n"+ texto);
        javaMailSender.send(simpleMailMessage);
    }
}
