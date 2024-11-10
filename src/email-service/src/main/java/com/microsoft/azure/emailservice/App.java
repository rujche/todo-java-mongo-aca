package com.microsoft.azure.emailservice;
import com.azure.communication.email.*;
import com.azure.communication.email.models.*;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;

public class App {
    public static void main(String[] args) {
        String connectionString = "endpoint=https://xiada-cs.unitedstates.communication.azure.com/;accesskey=1afAyogQi6QF9Bzw2sWmenWo7GDTdXFxqtwBXA5YsRoV26Ud4yfSJQQJ99AKACULyCpAArohAAAAAZCSwiyp";
        EmailClient emailClient = new EmailClientBuilder().connectionString(connectionString).buildClient();

        EmailAddress toAddress = new EmailAddress("xiada@microsoft.com");

        EmailMessage emailMessage = new EmailMessage()
                .setSenderAddress("DoNotReply@f4e35a7b-90d4-4697-82fc-a1c4f818937a.azurecomm.net")
                .setToRecipients(toAddress)
                .setSubject("Test Email")
                .setBodyPlainText("Hello world via email.")
                .setBodyHtml("""
			<html>
				<body>
					<h1>Hello world via email.</h1>
				</body>
			</html>""");


        SyncPoller<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(emailMessage, null);
        PollResponse<EmailSendResult> result = poller.waitForCompletion();
    }
}