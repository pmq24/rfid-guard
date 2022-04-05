package com.github.pmq24.rfid_guard.data;

public class Rfid {

    public Rfid(String epc) throws InvalidRfidException {
        validateEpc(epc);
    }

    private void validateEpc(String epc) throws InvalidRfidException {

        if (epc == null)
            throw new InvalidRfidException("RFID's EPC must not be null");

        if (epcContainsInvalidChars(epc))
            throw new InvalidRfidException("RFID can only contain these letters: 0123456789ABCDEFabcdef-");

        epc = removeDashes(epc);

        if (epc.length() != 24)
            throw new InvalidRfidException("RFID must be exactly 24 letters (ignoring dashes)");

        epc = epc.toUpperCase();

        this.epc = epc;
    }

    public String toString() {

        StringBuilder stringBuilder = new StringBuilder("");

        for (int i = 0; i < epc.length(); i = i + 4) {
            String stringGroup = epc.substring(i, i + 4);
            stringBuilder.append(stringGroup);

            // not add dash to the end of the last group
            if (i != 20)
                stringBuilder.append("-");
        }

        return stringBuilder.toString();

    }

    private boolean epcContainsInvalidChars(String epc) {
        for (char character : epc.toCharArray()) {
            final boolean isInvalidChar = !isValidChar(character);
            if(isInvalidChar)
                return true;
        }
        return false;
    }

    private boolean isValidChar(char c) {
        final String VALID_CHARS = "0123456789ABCDEFabcdef-";
        return VALID_CHARS.contains(String.valueOf(c));
    }

    private String removeDashes(String epc) {
        return epc.replace("-", "");
    }

    private String epc;
}
