package com.projects.up.services;

import javax.mail.MessagingException;

import com.projects.up.entities.Mail;

public interface SendMailService {
    void sendMail(Mail mail);

    void sendMailWithAttachments(Mail mail) throws MessagingException;
}
