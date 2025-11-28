package com.dairy.Services;

import org.springframework.stereotype.Service;

@Service
public class SmsService {

    // Simulating sending OTP via SMS (this should be replaced with actual SMS API integration)
    public boolean sendOtp(String mobile, String otp) {
        // In a real-world scenario, you would integrate with an SMS provider like Twilio or Nexmo here
        System.out.println("Sending OTP: " + otp + " to mobile: " + mobile);

        // Return true to simulate a successful SMS send
        return true; // Simulating successful SMS send
    }
}
