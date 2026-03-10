# 🚀 VPS Server Hosting Guide (Node.js / Next.js / React)

This guide explains how to deploy **Backend and Frontend applications on a VPS server** using:

- Node.js
- PM2
- Nginx
- SSL (Certbot)

---

# ⚙️ Initial VPS Setup

## Step 1: Update System
```bash
sudo apt update
sudo apt upgrade
```

---

## Step 2: Install Node Version Manager (NVM)

```bash
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
```

---

## Step 3: Install Node.js and NPM

```bash
apt install nodejs
sudo apt install npm

node -v
npm -v
```

---

## Step 4: Install Git and GitHub CLI

```bash
sudo apt install git
sudo apt install gh

gh auth login
```

---

## Step 5: Install Global Packages

```bash
npm i -g pnpm
npm i -g pm2
```

- **pnpm** → install node modules
- **pm2** → run server 24x7

---

## Step 6: Install Nginx and Certbot

```bash
sudo apt install nginx
sudo apt-get install certbot python3-certbot-nginx
```

---

## ⚠️ Reminder

After installing new software always run:

```bash
sudo apt update
sudo apt upgrade
```

---

# 🌐 Live Backend on VPS

### ⚠️ Important
Add DNS records for:

```
api.DOMAIN_NAME
www.api.DOMAIN_NAME
```

---

# Step 1: Clone Repository

```bash
git clone <git repo link>
```

Example:

```bash
git clone https://github.com/HARSH-VARDHAN-MISHRA/Laboratory.git
```

---

# Step 2: Install Dependencies

```bash
pnpm install
```

---

# Step 3: Create `.env` File

```bash
nano .env
```

Save file:

```
Ctrl + O
Enter
Ctrl + X
```

Check file:

```bash
cat .env
```

⚠️ Remember the **PORT number** (used in nginx config).

---

# Step 4: Run Server (Test)

```bash
node <server_file_name>
```

Example

```bash
node server.js
```

---

# Step 5: Run Server using PM2

```bash
pm2 start <server_file_name> --name <project_name>
```

Example

```bash
pm2 start server.js --name labmantra
```

### NestJS Example

```bash
pm2 start dist/main.js --name nest-appointment
```

### Next.js Example

```bash
pm2 start npm --name next-app -- start
```

Check running servers:

```bash
pm2 ls
```

---

# Step 6: Configure Nginx

Go to nginx folder:

```bash
cd /etc/nginx
cd sites-available
```

Create config file:

```bash
nano <server_name>.conf
```

Example

```bash
nano labmantra.conf
```

---

## Add this configuration

```nginx
server {
    listen 80;
    server_name api.DOMAIN_NAME www.api.DOMAIN_NAME;

    location / {
        proxy_pass http://localhost:PORT_NUMBER_ON_ENV_FILE;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
    }
}
```

---

# Step 7: Enable Nginx Config

```bash
cd ../sites-enabled
ln -s ../sites-available/<server_name>.conf
```

Test nginx config:

```bash
sudo nginx -t
```

---

# Step 8: Request SSL Certificate

```bash
sudo certbot --nginx -d api.DOMAIN_NAME.com -d www.api.DOMAIN_NAME.com
```

Example

```bash
sudo certbot --nginx -d api.labmantra.com -d www.api.labmantra.com
```

---

# Step 9: Restart Nginx

```bash
sudo systemctl restart nginx
```

---

# 🎉 Backend is Live

---

# 🔄 Update Backend

## Step 1: Pull Latest Code

```bash
git pull
```

---

## Step 2: Install Packages

```bash
pnpm install
```

---

## Step 3: Restart PM2 Server

```bash
pm2 restart <list_number>
```

---

## Step 4: Restart Nginx

```bash
sudo systemctl restart nginx
```

---

# 🌐 Live Frontend on VPS

### ⚠️ Add DNS Records

```
DOMAIN_NAME
www.DOMAIN_NAME
```

---

# Step 1: Clone Repository

```bash
git clone <git repo link>
```

Example

```bash
git clone https://github.com/HARSH-VARDHAN-MISHRA/ABC.git
```

---

# Step 2: Install Dependencies

```bash
pnpm install
```

---

# Step 3: Add `.env` File

```bash
nano .env
```

Check file

```bash
cat .env
```

⚠️ Update **API URL inside env**

---

# Step 4: Build Project

```bash
npm run build
```

Delete folder if needed

```bash
rm -r build
```

---

# Step 5: Configure Nginx

```bash
cd /etc/nginx
cd sites-available
nano <server_name>.conf
```

---

## Nginx Config

```nginx
server {
    listen 80;
    server_name www.DOMAIN_NAME.com DOMAIN_NAME.com;

    root /root/Test/build;

    location / {
        try_files $uri $uri/ /index.html;
    }
}
```

Example

```nginx
server {
    listen 80;
    server_name www.test.in test.in;

    root /root/Test/build;

    location / {
        try_files $uri $uri/ =404;
    }
}
```

---

# Step 6: Enable Config

```bash
cd ../sites-enabled
ln -s ../sites-available/<server_name>.conf
```

Test config

```bash
sudo nginx -t
```

---

# Step 7: Add SSL

```bash
sudo certbot --nginx -d DOMAIN_NAME.com -d www.DOMAIN_NAME.com
```

Example

```bash
sudo certbot --nginx -d test.com -d www.test.com
```

---

# Step 8: Restart Nginx

```bash
sudo systemctl restart nginx
```

---

# 🎉 Frontend is Live

---

# 🔄 Update Frontend

## Step 1

```bash
git pull
```

---

## Step 2

```bash
npm install
```

---

## Step 3

Delete old build

```bash
rm -r build
```

Create new build

```bash
npm run build
```

---

## Step 4

Restart nginx

```bash
sudo systemctl restart nginx
```

---

# ⚡ Next.js with PM2

Start Next.js server

```bash
pm2 start npm --name transpeed -- run start
```

Start with port

```bash
PORT=4000 pm2 start npm --name transpeed -- run start
```

Save pm2

```bash
pm2 save
pm2 startup
pm2 list
```

---

# 🔐 Open Firewall Port

```bash
sudo ufw allow <port>
```

---

# 📦 PM2 Ecosystem Setup

Create config

```bash
nano ecosystem.config.js
```

Add config

```javascript
module.exports = {
  apps: [
    {
      name: "projectname",
      script: "npm",
      args: "start",
      cwd: "/root/projectrootname",
      env: {
        NODE_ENV: "production",
      },
    },
  ],
};
```

Start with PM2

```bash
pm2 start ecosystem.config.js
```

---

# 🌍 Example Nginx Config (Next.js)

```nginx
server {
    listen 80;
    server_name drkm.admin.adsdigitalmedia.com www.drkm.admin.adsdigitalmedia.com;

    location / {
        proxy_pass http://localhost:3006;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_cache_bypass $http_upgrade;
    }
}
```

Reload nginx

```bash
sudo systemctl reload nginx
```

---

# 🔑 Multiple Domain SSL Example

```bash
sudo certbot --nginx \
-d drkm.admin.adsdigitalmedia.com \
-d www.drkm.admin.adsdigitalmedia.com \
-d drkm.adsdigitalmedia.com \
-d www.drkm.adsdigitalmedia.com \
-d drkm.api.adsdigitalmedia.com \
-d www.drkm.api.adsdigitalmedia.com
```

---

# 📥 Download File From Server

```bash
scp root@82.112.236.65:/root/dr.rkm/admin/.env C:\Users\shiva\Downloads\.env
```

---

# 🎉 Done

Your **Backend + Frontend VPS Deployment Setup is Complete** 🚀
