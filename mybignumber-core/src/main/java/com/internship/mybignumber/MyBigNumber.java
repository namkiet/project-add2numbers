package com.internship.mybignumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Core class for adding two arbitrarily large numbers represented as strings.
 * Implements elementary-school column addition from right to left.
 *
 * <p>Requirements:
 * <ul>
 *   <li>Class name: <code>MyBigNumber</code></li>
 *   <li>Method signature: <code>String sum(String stn1, String stn2)</code></li>
 *   <li>Step logging and history recording</li>
 * </ul>
 */
public class MyBigNumber {

    private static final Logger logger = LoggerFactory.getLogger(MyBigNumber.class);

    private final IStepListener listener;

    /**
     * Default constructor using standard SLF4J logging for each addition step.
     */
    public MyBigNumber() {
        this((stepNum, msg) -> logger.info("Bước {}: {}", stepNum, msg));
    }

    /**
     * Constructor allowing a custom listener (e.g. for Web UI progress capture or testing).
     *
     * @param listener The listener receiving step notifications.
     */
    public MyBigNumber(IStepListener listener) {
        this.listener = (listener != null) ? listener : (stepNum, msg) -> {};
    }

    /**
     * Adds two numbers represented as numeric strings using column addition.
     *
     * @param stn1 First numeric string (e.g. "1234")
     * @param stn2 Second numeric string (e.g. "897")
     * @return Sum of stn1 and stn2 as a string (e.g. "2131")
     */
    public String sum(String stn1, String stn2) {
        // Defensive handling for null input
        String s1 = (stn1 == null) ? "" : stn1.trim();
        String s2 = (stn2 == null) ? "" : stn2.trim();

        if (s1.isEmpty() && s2.isEmpty()) {
            return "0";
        }
        if (s1.isEmpty()) {
            return s2;
        }
        if (s2.isEmpty()) {
            return s1;
        }

        int i = s1.length() - 1;
        int j = s2.length() - 1;
        int carry = 0;
        int step = 0;

        StringBuilder result = new StringBuilder();

        while (i >= 0 || j >= 0 || carry > 0) {
            step++;
            int digit1 = (i >= 0) ? (s1.charAt(i) - '0') : 0;
            int digit2 = (j >= 0) ? (s2.charAt(j) - '0') : 0;

            int columnSum = digit1 + digit2 + carry;
            int unitDigit = columnSum % 10;
            int nextCarry = columnSum / 10;

            // Build detailed descriptive step log following requirement format
            StringBuilder stepMsg = new StringBuilder();
            stepMsg.append("Lấy ").append(digit1).append(" cộng với ").append(digit2);
            if (carry > 0) {
                stepMsg.append(" cộng tiếp với nhớ ").append(carry);
            }
            stepMsg.append(" được ").append(columnSum).append(". ");
            stepMsg.append("Lưu ").append(unitDigit).append(" vào kết quả");

            result.insert(0, unitDigit);
            stepMsg.append(" được kết quả tạm là \"").append(result).append("\". ");

            if (nextCarry > 0) {
                stepMsg.append("Ghi nhớ ").append(nextCarry).append(".");
            } else {
                stepMsg.append("Nhớ 0.");
            }

            // Emit to listener & logger
            listener.onStep(step, stepMsg.toString());

            carry = nextCarry;
            i--;
            j--;
        }

        logger.info("Phép toán hoàn tất: {} + {} = {}", s1, s2, result);
        return result.toString();
    }
}
