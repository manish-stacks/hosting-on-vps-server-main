# 🍃 MongoDB Backup & Restore Guide

This guide explains how to **backup and restore MongoDB databases** using:

- `mongodump` → Create database backup
- `mongorestore` → Restore database backup

This works for:

- MongoDB Atlas
- VPS MongoDB
- Local MongoDB

---

# 📦 Install MongoDB Database Tools

If `mongodump` or `mongorestore` is not installed, install MongoDB tools.

### Ubuntu / VPS

```bash
sudo apt install mongodb-database-tools
```

Check installation

```bash
mongodump --version
mongorestore --version
```

---

# 💾 Backup MongoDB Database (mongodump)

Create a backup of your MongoDB database.

```bash
mongodump --uri="MONGODB_CONNECTION_URL" --out=./dump
```

Example:

```bash
mongodump --uri="mongodb+srv://username:password@cluster.mongodb.net/dbname" --out=./dump
```

This will create a folder:

```
dump/
 ┣ dbname
 ┃ ┣ collection1.bson
 ┃ ┣ collection2.bson
 ┃ ┗ collection.metadata.json
```

---

# 📂 Backup Specific Database

```bash
mongodump --uri="MONGO_URL" --db=database_name --out=./dump
```

Example

```bash
mongodump --uri="mongodb://localhost:27017" --db=mydatabase --out=./dump
```

---

# 📁 Backup Specific Collection

```bash
mongodump \
--uri="MONGO_URL" \
--db=database_name \
--collection=collection_name \
--out=./dump
```

---

# 🔁 Restore MongoDB Database (mongorestore)

Restore the backup database.

```bash
mongorestore --uri="MONGODB_CONNECTION_URL" ./dump
```

Example:

```bash
mongorestore --uri="mongodb+srv://username:password@cluster.mongodb.net/dbname" ./dump
```

---

# 🔄 Restore Specific Database

```bash
mongorestore \
--uri="MONGO_URL" \
--db=database_name \
dump/database_name
```

---

# ⚠️ Restore and Overwrite Existing Data

If the database already exists and you want to overwrite it:

```bash
mongorestore --drop --uri="MONGO_URL" ./dump
```

`--drop` deletes existing collections before restoring.

---

# 📤 Backup from VPS to Local Machine

Run on **local machine**:

```bash
scp -r root@SERVER_IP:/root/project/dump ./dump
```

---

# 📥 Upload Backup to VPS

```bash
scp -r ./dump root@SERVER_IP:/root/project/
```

---

# ⏱ Auto Backup using Cron Job

Create automatic MongoDB backup every day.

Open cron editor:

```bash
crontab -e
```

Example daily backup (2 AM):

```bash
0 2 * * * mongodump --uri="MONGO_URL" --out=/root/mongo-backup/$(date +\%F)
```

This will create backups like:

```
mongo-backup/
 ┣ 2026-03-10
 ┣ 2026-03-11
 ┗ 2026-03-12
```

---

# 🧹 Delete Old Backups Automatically

Keep backups only for **7 days**.

```bash
find /root/mongo-backup -type d -mtime +7 -exec rm -rf {} \;
```

Add to cron:

```bash
0 3 * * * find /root/mongo-backup -type d -mtime +7 -exec rm -rf {} \;
```

---

# ⚡ Useful MongoDB Backup Commands

Backup database with compression:

```bash
mongodump --uri="MONGO_URL" --gzip --out=./dump
```

Restore compressed backup:

```bash
mongorestore --uri="MONGO_URL" --gzip ./dump
```

---

```

---
