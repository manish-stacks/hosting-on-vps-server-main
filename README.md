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

# 🔒 Advanced VPS Security & DevOps Setup

This section adds **extra security, monitoring, CI/CD, Docker deployment, and load balancing** for production servers.

---

# 🛡 Install Fail2Ban (Brute Force Protection)

Fail2Ban protects your server from **SSH brute-force attacks**.

### Step 1: Install Fail2Ban

```bash
sudo apt install fail2ban
```

---

### Step 2: Start and Enable Service

```bash
sudo systemctl start fail2ban
sudo systemctl enable fail2ban
```

---

### Step 3: Create Local Config

```bash
sudo cp /etc/fail2ban/jail.conf /etc/fail2ban/jail.local
```

Edit file:

```bash
sudo nano /etc/fail2ban/jail.local
```

Example configuration:

```ini
[sshd]
enabled = true
port = ssh
logpath = %(sshd_log)s
maxretry = 5
bantime = 3600
```

Restart Fail2Ban

```bash
sudo systemctl restart fail2ban
```

Check status

```bash
sudo fail2ban-client status
```

---

# 🔁 Auto Deployment (CI/CD)

Use **GitHub Actions** for automatic deployment.

Whenever you push code → it **automatically deploys to VPS**.

Create folder:

```
.github/workflows
```

Create file:

```
deploy.yml
```

---

## GitHub Actions Deployment Script

```yaml
name: Deploy to VPS

on:
  push:
    branches:
      - main

jobs:
  deploy:
    runs-on: ubuntu-latest

    steps:
      - name: Deploy using SSH
        uses: appleboy/ssh-action@master
        with:
          host: ${{ secrets.SERVER_IP }}
          username: root
          key: ${{ secrets.SSH_PRIVATE_KEY }}

          script: |
            cd /root/projectname
            git pull
            pnpm install
            pm2 restart all
```

---

## Required GitHub Secrets

Go to:

```
GitHub Repo → Settings → Secrets
```

Add:

```
SERVER_IP
SSH_PRIVATE_KEY
```

---

# 📊 Log Monitoring

Monitor server logs to detect errors.

---

## PM2 Logs

```bash
pm2 logs
```

---

## Nginx Logs

Access logs

```bash
sudo tail -f /var/log/nginx/access.log
```

Error logs

```bash
sudo tail -f /var/log/nginx/error.log
```

---

## System Logs

```bash
journalctl -u nginx
```

---

# 🐳 Docker Deployment

Docker allows running applications inside **isolated containers**.

---

## Step 1: Install Docker

```bash
sudo apt install docker.io
```

Start docker

```bash
sudo systemctl start docker
sudo systemctl enable docker
```

Check version

```bash
docker -v
```

---

## Step 2: Create Dockerfile

```Dockerfile
FROM node:18

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

EXPOSE 3000

CMD ["npm","start"]
```

---

## Step 3: Build Docker Image

```bash
docker build -t projectname .
```

---

## Step 4: Run Docker Container

```bash
docker run -d -p 3000:3000 projectname
```

---

# 📦 Docker Compose (Optional)

Create file

```bash
nano docker-compose.yml
```

Example

```yaml
version: "3"

services:
  app:
    build: .
    ports:
      - "3000:3000"
```

Run

```bash
docker-compose up -d
```

---

# ⚡ Load Balancing with Nginx

Load balancing distributes traffic across **multiple backend servers**.

Example setup with **2 servers**.

---

## Nginx Load Balancer Config

```nginx
upstream backend_servers {
    server 127.0.0.1:3001;
    server 127.0.0.1:3002;
}

server {
    listen 80;
    server_name api.domain.com;

    location / {
        proxy_pass http://backend_servers;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
    }
}
```

Restart nginx

```bash
sudo systemctl restart nginx
```

---

# 🔐 Advanced Security

---

## Disable Root SSH Login

Edit ssh config

```bash
sudo nano /etc/ssh/sshd_config
```

Change

```
PermitRootLogin no
```

Restart ssh

```bash
sudo systemctl restart ssh
```

---

## Change SSH Port

Example change port from **22 → 2222**

```
Port 2222
```

Restart SSH

```bash
sudo systemctl restart ssh
```

Open port

```bash
sudo ufw allow 2222
```

---

## Enable Firewall

```bash
sudo ufw enable
```

Allow necessary ports

```bash
sudo ufw allow 22
sudo ufw allow 80
sudo ufw allow 443
```

Check firewall status

```bash
sudo ufw status
```

---

# 📈 PM2 Monitoring

Monitor server performance.

```bash
pm2 monit
```

Save processes

```bash
pm2 save
```

Startup on reboot

```bash
pm2 startup
```

---

# 🧹 Useful Maintenance Commands

Clear logs

```bash
pm2 flush
```

Restart all apps

```bash
pm2 restart all
```

Delete app

```bash
pm2 delete appname
```

Check server usage

```bash
htop
```

---

# 🎉 Production Server Ready

Your VPS now supports:

✅ Node Hosting  
✅ Nginx Reverse Proxy  
✅ SSL Security  
✅ Fail2Ban Protection  
✅ CI/CD Auto Deploy  
✅ Docker Containers  
✅ Log Monitoring  
✅ Load Balancing  
✅ Firewall Security  
✅ PM2 Process Manager  

🚀 **Your server is now production ready.**
