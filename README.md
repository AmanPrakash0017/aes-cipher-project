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
git clone https://github.com/AmanPrakash0017/AESCipherProject.git
cd AESCipherProject
```

### 2️⃣ Compile the Java Code
```bash
javac AESCipherProject.java
```
### 3️⃣ Run the Application
```bash
java AESCipherProject
```
### 🧠 How to Use

When the program starts, you’ll see a main menu like this:
```mathemaical
=== MAIN MENU ===
1. Text Encryption/Decryption (CBC Mode)
2. Text Encryption/Decryption (GCM Mode)
3. File Encryption/Decryption
4. Password-Based Encryption
5. Exit
```

👉 Choose an option:

For text: enter your message to encrypt or decrypt.

For files: enter the file path (e.g., C:\Users\Aman\Desktop\test.txt).

Save the key and IV if you want to decrypt later.

### 🧾 Example Output
```pgsql
--- AES CBC Mode ---
Enter text to encrypt: Hello Java Security!

✓ Encrypted: LxHj3gVwO5D7M2P5P2Q3VA==
✓ Decrypted: Hello Java Security!

[Technical Details]
Key (Base64): Zx8k+sp7JrTG5fXoEbZxW0Q==
IV (Base64): iD91K+6zVw5wbiW8Zytd0A==
```
### 🧰 Project Structure
```bash
AESCipherProject/
├── AESCipherProject.java    # Main class with menu and user interface
├── AESCipher.java           # AES encryption/decryption logic
└── README.md                # Project documentation
```
### 🧤 Security Disclaimer

This project is created for educational and learning purposes only.
It demonstrates cryptographic concepts using AES in Java.
Do not use it in production systems without additional security measures such as:

Secure key and IV management

Random salt generation and storage

Encrypted key storage and access control

### 📚 Learning Outcomes

Through this project, I learned how to:

Implement AES encryption using Java’s Cipher class.

Work with encryption modes like CBC and GCM.

Manage keys and initialization vectors securely.

Perform file I/O operations using CipherOutputStream and CipherInputStream.

Build a console-based interactive Java application.

### 👨‍💻 Author

Aman Prakash Jena
💻 Java Developer & Security Enthusiast
📍 India

⭐ If you found this project useful, don’t forget to star the repository! 🌟

```bash
---

✅ **Ready to Use:**  
Just paste this text into a new file named `README.md` in your project folder before committing to GitHub.  

Would you like me to also generate a **short Git commit message and repo description** you can use while uploading this project?
```
