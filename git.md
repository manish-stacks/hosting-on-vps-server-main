# 🔐 Git SSH Setup & Repository Push Guide

This guide explains how to:

- Generate SSH key
- Add SSH key to GitHub
- Test SSH connection
- Clone repository using SSH
- Push code to another GitHub repository

---

# 🔧 Step 1: Generate New SSH Key

Open **Terminal / Git Bash** and run:

```bash
ssh-keygen -t ed25519 -C "codewithhbs@gmail.com"
```

You will see prompts:

```
Enter a file in which to save the key (/c/Users/username/.ssh/id_ed25519):
```

Press **Enter** to accept default path.

```
Enter passphrase (empty for no passphrase):
```

- Leave blank OR
- Enter a passphrase (optional)

Files created:

```
~/.ssh/id_ed25519
~/.ssh/id_ed25519.pub
```

---

# 📋 Step 2: Copy SSH Public Key

Run:

```bash
cat ~/.ssh/id_ed25519.pub
```

Example output:

```
ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAI... codewithhbs@gmail.com
```

Copy the **entire key** from:

```
ssh-ed25519 → till email
```

---

# 🌐 Step 3: Add SSH Key to GitHub

Go to GitHub:

```
GitHub → Settings → SSH and GPG Keys
```

Click:

```
New SSH Key
```

Fill fields:

```
Title : My Laptop Key
Key   : (Paste SSH key)
```

Click:

```
Add SSH Key
```

---

# ✅ Step 4: Test SSH Connection

Run:

```bash
ssh -T git@github.com
```

Expected output:

```
Hi username! You've successfully authenticated.
```

---

# 📥 Step 5: Clone Repository using SSH

Example:

```bash
git clone git@github.com:manish-stacks/beveromatic.git
```

Go inside project:

```bash
cd beveromatic
```

---

# 🔄 Step 6: Remove Old Remote

```bash
git remote remove origin
```

Check remotes:

```bash
git remote -v
```

---

# ➕ Step 7: Add New Remote Repository

Example:

```bash
git remote add origin git@github.com:codewithhbs/beveromatic.git
```

Verify remote:

```bash
git remote -v
```

---

# 🚀 Step 8: Push Code to New Repository

If your branch is **main**

```bash
git push -u origin main
```

If your branch is **master**

```bash
git push -u origin master
```

---

# 📊 Useful Git Commands

Check branch:

```bash
git branch
```

Create new branch:

```bash
git checkout -b new-branch
```

Add files:

```bash
git add .
```

Commit changes:

```bash
git commit -m "update code"
```

Push changes:

```bash
git push
```

Pull latest changes:

```bash
git pull
```

---

# 🔎 Check Git Remote

```bash
git remote -v
```

Example output:

```
origin git@github.com:username/repo.git (fetch)
origin git@github.com:username/repo.git (push)
```

---

# 🧹 Remove SSH Key (Optional)

Delete SSH key from system:

```bash
rm ~/.ssh/id_ed25519
rm ~/.ssh/id_ed25519.pub
```

---

# 📁 SSH Key Location

Default location:

```
Windows
C:\Users\username\.ssh\

Linux / Mac
~/.ssh/
```

---
