// Testing email notification system (in NotificationMgr)
package Boundary;

import Control.NotificationMgr;

import javax.mail.MessagingException;

public class NotificationApp
{
    public static void main(String args[]) throws MessagingException {
        NotificationMgr.sendEmail("C190195@e.ntu.edu.sg","CE2002",10045);
    }
}
