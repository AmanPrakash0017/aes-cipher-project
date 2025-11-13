import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.io.*;

class AESCipher {
    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 256;

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(KEY_SIZE);
        return keyGen.generateKey();
    }

    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, KEY_SIZE);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), ALGORITHM);
    }

    public static IvParameterSpec generateIV() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static String encrypt(String plainText, SecretKey key, IvParameterSpec iv)
            throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, SecretKey key, IvParameterSpec iv)
            throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText, StandardCharsets.UTF_8);
    }

    public static String encryptGCM(String plainText, SecretKey key, byte[] iv)
            throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decryptGCM(String cipherText, SecretKey key, byte[] iv)
            throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText, StandardCharsets.UTF_8);
    }

    public static void encryptFile(String inputFile, String outputFile, SecretKey key, IvParameterSpec iv)
            throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile);
             CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }
        }
    }

    public static void decryptFile(String inputFile, String outputFile, SecretKey key, IvParameterSpec iv)
            throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);

        try (FileInputStream fis = new FileInputStream(inputFile);
             CipherInputStream cis = new CipherInputStream(fis, cipher);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = cis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}

public class AESCipherProject {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║   AES ENCRYPTION/DECRYPTION SYSTEM    ║");
            System.out.println("╚════════════════════════════════════════╝\n");

            while (true) {
                System.out.println("\n=== MAIN MENU ===");
                System.out.println("1. Text Encryption/Decryption (CBC Mode)");
                System.out.println("2. Text Encryption/Decryption (GCM Mode)");
                System.out.println("3. File Encryption/Decryption");
                System.out.println("4. Password-Based Encryption");
                System.out.println("5. Exit");
                System.out.print("\nSelect option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        textEncryptionCBC(scanner);
                        break;
                    case 2:
                        textEncryptionGCM(scanner);
                        break;
                    case 3:
                        fileEncryption(scanner);
                        break;
                    case 4:
                        passwordBasedEncryption(scanner);
                        break;
                    case 5:
                        System.out.println("\nExiting... Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option!");
                }
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void textEncryptionCBC(Scanner scanner) throws Exception {
        System.out.println("\n--- AES CBC Mode ---");
        System.out.print("Enter text to encrypt: ");
        String plainText = scanner.nextLine();

        SecretKey key = AESCipher.generateKey();
        IvParameterSpec iv = AESCipher.generateIV();

        String encrypted = AESCipher.encrypt(plainText, key, iv);
        System.out.println("\n✓ Encrypted: " + encrypted);

        String decrypted = AESCipher.decrypt(encrypted, key, iv);
        System.out.println("✓ Decrypted: " + decrypted);

        System.out.println("\n[Technical Details]");
        System.out.println("Key (Base64): " + Base64.getEncoder().encodeToString(key.getEncoded()));
        System.out.println("IV (Base64): " + Base64.getEncoder().encodeToString(iv.getIV()));
    }

    private static void textEncryptionGCM(Scanner scanner) throws Exception {
        System.out.println("\n--- AES GCM Mode (Authenticated Encryption) ---");
        System.out.print("Enter text to encrypt: ");
        String plainText = scanner.nextLine();

        SecretKey key = AESCipher.generateKey();
        byte[] iv = new byte[12];
        new SecureRandom().nextBytes(iv);

        String encrypted = AESCipher.encryptGCM(plainText, key, iv);
        System.out.println("\n✓ Encrypted: " + encrypted);

        String decrypted = AESCipher.decryptGCM(encrypted, key, iv);
        System.out.println("✓ Decrypted: " + decrypted);

        System.out.println("\n[Note: GCM mode provides both encryption and authentication]");
    }

    private static void fileEncryption(Scanner scanner) throws Exception {
        System.out.println("\n--- File Encryption ---");
        System.out.print("Enter input file path: ");
        String inputFile = scanner.nextLine();

        System.out.print("Encrypt (E) or Decrypt (D)? ");
        String operation = scanner.nextLine().toUpperCase();

        SecretKey key = AESCipher.generateKey();
        IvParameterSpec iv = AESCipher.generateIV();

        if (operation.equals("E")) {
            String outputFile = inputFile + ".encrypted";
            AESCipher.encryptFile(inputFile, outputFile, key, iv);
            System.out.println("✓ File encrypted: " + outputFile);
        } else if (operation.equals("D")) {
            String outputFile = inputFile.replace(".encrypted", ".decrypted");
            AESCipher.decryptFile(inputFile, outputFile, key, iv);
            System.out.println("✓ File decrypted: " + outputFile);
        }

        System.out.println("\n[Important: Save the key and IV to decrypt later!]");
        System.out.println("Key: " + Base64.getEncoder().encodeToString(key.getEncoded()));
        System.out.println("IV: " + Base64.getEncoder().encodeToString(iv.getIV()));
    }

    private static void passwordBasedEncryption(Scanner scanner) throws Exception {
        System.out.println("\n--- Password-Based Encryption ---");
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter text to encrypt: ");
        String plainText = scanner.nextLine();

        String salt = "SecureSalt123";
        SecretKey key = AESCipher.getKeyFromPassword(password, salt);
        IvParameterSpec iv = AESCipher.generateIV();

        String encrypted = AESCipher.encrypt(plainText, key, iv);
        System.out.println("\n✓ Encrypted: " + encrypted);

        System.out.print("\nEnter password to decrypt: ");
        String decryptPassword = scanner.nextLine();

        SecretKey decryptKey = AESCipher.getKeyFromPassword(decryptPassword, salt);

        try {
            String decrypted = AESCipher.decrypt(encrypted, decryptKey, iv);
            System.out.println("✓ Decrypted: " + decrypted);
        } catch (Exception e) {
            System.out.println("✗ Decryption failed! Wrong password.");
        }
    }
}
