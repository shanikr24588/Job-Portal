package com.jobportal.Utility;

public class Data {
    public static String getMessageBody(String otp) {

        return """
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>{subject}</title>
  <style>
    body {{
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }}
    .email-container {{
      max-width: 600px;
      margin: 0 auto;
      background-color: #ffffff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }}
    .email-header {{
      text-align: center;
      padding-bottom: 20px;
    }}
    .email-header h1 {{
      margin: 0;
      color: #4CAF50;
    }}
    .email-content {{
      color: #333333;
      font-size: 16px;
      line-height: 1.6;
    }}
    .otp {{
      font-size: 24px;
      font-weight: bold;
      color: #4CAF50;
      margin: 20px 0;
      text-align: center;
    }}
    .footer {{
      text-align: center;
      color: #777777;
      font-size: 12px;
      margin-top: 30px;
    }}
  </style>
</head>
<body>

  <div class="email-container">
    <div class="email-header">
      <h1>{subject}</h1>
    </div>
    <div class="email-content">
      <p>Dear {recipient_name},</p>
      <p>We received a request to verify your account. To proceed, please enter the following One Time Password (OTP) in the required field:</p>

      <div class="otp">{otp_code}</div>

      <p>If you didn't request this, please ignore this email.</p>
      <p>Thank you for using our service!</p>
    </div>
    
    <div class="footer">
      <p>If you have any questions, feel free to contact us.</p>
      <p>&copy; {current_year} Your Company Name</p>
    </div>
  </div>

</body>
</html>
""";
    }



    }