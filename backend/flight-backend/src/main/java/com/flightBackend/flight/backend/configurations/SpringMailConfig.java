package com.flightBackend.flight.backend.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class SpringMailConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.user.name}")
    private String userName;

    @Value("${spring.mail.password}")
    private String password;

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(userName);
        javaMailSender.setPassword(password);

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable",true);
        props.put("mail.mtp.ssl.trust",host);
        return javaMailSender;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}