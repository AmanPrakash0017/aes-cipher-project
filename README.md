# 🔐 AES Encryption & Decryption System (Java)

A **Java-based AES Encryption and Decryption Tool** that supports **CBC** and **GCM** modes for text and file encryption.  
It also includes **password-based encryption (PBKDF2)** for enhanced security.  

This project demonstrates how to use Java’s built-in **cryptographic APIs** to securely encrypt and decrypt data using the **Advanced Encryption Standard (AES)** algorithm.

---

## 🧩 Features

- 🔒 **AES CBC Mode** — Standard encryption/decryption with IV.
- 🧠 **AES GCM Mode** — Authenticated encryption for data integrity.
- 🗂️ **File Encryption** — Encrypt and decrypt any file using AES.
- 🔑 **Password-Based Encryption (PBKDF2)** — Derives a key securely from a password.
- 🧱 **256-bit AES Key Generation** — Ensures high security.
- 🧮 **Base64 Encoding** — Displays keys and ciphertexts in readable format.
- 🧰 **Menu-Driven Console Interface** — Simple and user-friendly navigation.

---

## ⚙️ Technologies Used

- **Language:** Java  
- **Libraries:**  
  - `javax.crypto`  
  - `java.security`  
  - `java.io`  
  - `java.util`  

---

## 🚀 How to Run

### 1️⃣ Clone the Repository
```bash
2️⃣ Compile the Code
javac AESCipherProject.java

3️⃣ Run the Program
java AESCipherProject

🧠 Usage Instructions

When you run the program, you’ll see a menu:

=== MAIN MENU ===
1. Text Encryption/Decryption (CBC Mode)
2. Text Encryption/Decryption (GCM Mode)
3. File Encryption/Decryption
4. Password-Based Encryption
5. Exit


Choose an option:

Enter text to encrypt or a file path to process.

The program will generate a secure AES key and IV.

Use the same key and IV to decrypt later.

🧾 Example Output
--- AES CBC Mode ---
Enter text to encrypt: Hello Java Security!

✓ Encrypted: LxHj3gVwO5D7M2P5P2Q3VA==
✓ Decrypted: Hello Java Security!

[Technical Details]
Key (Base64): Zx8k+sp7JrTG5fXoEbZxW0Q==
IV (Base64): iD91K+6zVw5wbiW8Zytd0A==

🧑‍💻 Developer Notes

AES key size: 256 bits

IV size (CBC): 16 bytes

IV size (GCM): 12 bytes

Salt used for PBKDF2: "SecureSalt123" (can be customized)

Cipher algorithms used:

AES/CBC/PKCS5Padding

AES/GCM/NoPadding

📦 Project Structure
AESCipherProject/
├── AESCipherProject.java    # Main menu & user interaction
├── AESCipher.java           # Core AES encryption/decryption logic
└── README.md                # Documentation

🧤 Security Disclaimer

This project is built for educational and learning purposes.
Do not use this code in production systems without additional security measures such as:

Secure key management

Random salt storage

IV persistence and authentication

👨‍💻 Author

Aman Prakash Jena
💻 Java Developer & Security Enthusiast
📧 [Your Email or LinkedIn]
📍 India

⭐ If you found this project useful, don’t forget to star the repository on GitHub!


---

Would you like me to make it look **more academic (for submission/report)** or **more professional (for GitHub portfolio presentation)**?  
I can adjust tone and formatting accordingly.
git clone https://github.com/<your-username>/AESCipherProject.git
cd AESCipherProject
