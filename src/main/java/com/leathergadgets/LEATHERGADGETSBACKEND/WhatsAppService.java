// WhatsAppService.java
package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@Service
public class WhatsAppService {

    private static final Logger LOGGER = Logger.getLogger(WhatsAppService.class.getName());

    // Interakt API URL and API Key
    private static final String INTERAKT_API_URL = "https://api.interakt.ai/v1/public/message";
    private static final String INTERAKT_API_KEY = "YOUR_INTERAKT_API_KEY"; // 🔁 Replace with real API key

    public boolean sendMessageWithImage(String mobile, String message, String imageUrl, String qrCodeUrl) {
        try {
            mobile = formatIndianMobile(mobile);
            if (mobile == null || mobile.isEmpty()) {
                LOGGER.warning("❌ Invalid mobile number format.");
                return false;
            }

            String payload = String.format("""
                {
                  "countryCode": "91",
                  "phoneNumber": "%s",
                  "callbackData": "LeatherGadgetsOrder",
                  "type": "text",
                  "message": "%s"
                }
            """, mobile.replace("+91", ""), buildMessage(message, imageUrl, qrCodeUrl));

            URL url = new URL(INTERAKT_API_URL);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + INTERAKT_API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            LOGGER.info("✅ Interakt API response code: " + responseCode);

            return responseCode == 200 || responseCode == 201;

        } catch (Exception e) {
            LOGGER.severe("💥 Interakt send error: " + e.getMessage());
            return false;
        }
    }

    private String buildMessage(String base, String img, String qr) {
        StringBuilder sb = new StringBuilder(base);
        if (img != null && !img.isEmpty()) sb.append("\n\n📸 Product Image: ").append(img);
        if (qr != null && !qr.isEmpty()) sb.append("\n🏷️ QR Code: ").append(qr);
        return sb.toString();
    }

    private String formatIndianMobile(String mobile) {
        if (mobile == null) return null;
        mobile = mobile.replaceAll("\\D", "");
        if (mobile.length() == 10) return "+91" + mobile;
        if (mobile.length() == 12 && mobile.startsWith("91")) return "+" + mobile;
        if (mobile.length() == 11 && mobile.startsWith("0")) return "+91" + mobile.substring(1);
        return null;
    }
}
