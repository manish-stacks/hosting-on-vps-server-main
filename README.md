# Hosting on VPS Server

## Setup Hosting

1. Update and upgrade the system:
    ```bash
    sudo apt update
    sudo apt upgrade
    ```

2. Install Node Version Manager (NVM):
    ```bash
    curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
    ```

3. Install Node.js and NVM:
    ```bash
    apt install nodejs
    nvm install --lts
    node -v
    ```

4. Install Git and GitHub CLI:
    ```bash
    sudo apt install git
    sudo apt install gh
    gh auth login
    ```

5. Install global npm packages:
    ```bash
    npm i -g pnpm  # For installing modules in node
    npm i -g pm2   # To run our server 24x7
    ```

6. Install Nginx and Certbot:
    ```bash
    sudo apt install nginx
    sudo apt-get install certbot python3-certbot-nginx  # For requesting the SSL Certificate
    ```

### Reminder

After every new download, please run the following commands:
```bash
sudo apt update
sudo apt upgrade
```





# Live Backend on VPS

### Firstly, Please remember to add the root in domain DNS for `api` and `www.api`.

### Step 1:
Command: 
```bash
git clone <git repo Link>
```
Example: `git clone https://github.com/HARSH-VARDHAN-MISHRA/Laboratory.git`

### Step 2:
Go to server file folder and download the node modules.  
Command: 
```bash
pnpm install
```

### Step 3:
Add the `.env` file:
- Command: `nano .env` (nano command is used to create a file)
- Now add the file content then:
  - `Ctrl + O`
  - `Enter`
  - `Ctrl + X` (To return back)
- If you want to see the changes, then use the command: `cat <file_name>`
- Reminder: Please change the PORT number and remember it for adding further in the `.conf` file.

### Step 4:
Run the command to check if the server is running: `node <server_file_name>`  
Example: `node server.js`

### Step 5:
Add the server to the pm2 list.  
Command: 
```bash
pm2 start <server_file_name> --name <project_name_shown_in_PM2_list>
```
Example: `pm2 start server.js --name labmantra`  
To see all the pm2 server lists: 
```bash
pm2 ls
```

### Step 6:
Go to `sites-available` and create a file `<server_name>.conf`.  
Example: `nano labmantra.conf`

Commands to create this file:
1. `cd /etc/nginx`
2. `cd ./sites-available`
3. `nano <server_name>.conf` (Example: `nano labmantra.conf`)

What to add in this file:

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
### Step 7:
Create a `.conf` file in `sites-enabled` with the same name as the file created in `sites-available` .

Commands to create this file:
1. `cd ../sites-enabled`
2. `ln -s ../sites-available/<server_name>.conf`.
3. `sudo nginx -t` // configure and test 

### Step 8:
Request for SSL certificate .
Command : 
```bash
sudo certbot --nginx -d api.DOMAIN_NAME.com -d www.api. DOMAIN_NAME.com
```
Example : `sudo certbot --nginx -d api.labmantra.com -d www.api.labmantra.com`

### Step 9:
Restart the nginx 
```bash
sudo systemctl restart nginx
```
### Congratulations 🥳 , Your Backend is live 


# Update Backend 

### Step 1 :
Update the code by git Repository.
```bash
git pull
```

### Step 2 : 
Update the server by installing it.

### Step 3 : 
Restart the pm2 server 
```bash
pm2 restart <List_Number>
```
### Step 4 :
Restart the System
```bash
sudo systemctl restart nginx
```

# Live FrontEnd on VPS 

### Firstly, Please remember to add the root in domain DNS for `DOMAIN_NAME` and `www.DOMAIN_NAME`.

### Step 1:
Command: 
```bash
git clone <git repo Link>
```
Example: `git clone https://github.com/HARSH-VARDHAN-MISHRA/ABC.git`

### Step 2:
Go to server file folder and download the node modules.  
Command: 
```bash
pnpm install
```

### Step 3:
If .env then add it to the file. Add the `.env` file:
- Command: `nano .env` (nano command is used to create a file)
- Now add the file content then:
  - `Ctrl + O`
  - `Enter`
  - `Ctrl + X` (To return back)
- If you want to see the changes, then use the command: `cat <file_name>`
- Reminder: Please  change the ENV file url 

### Step 4:
Create build file 
Command: 
```bash
npm run build
```
###### if want to delete then Command : rm  -r file_name

### Step 5:
Go to `sites-available` and create a file `<server_name>.conf` file.  
Example: `nano testfile.conf`

Commands to create this file:
1. `cd /etc/nginx`
2. `cd ./sites-available`
3. `nano <server_name>.conf` (Example: `nano testfile.conf`)

What to add in this file:

```nginx
server {	
    listen 80;
        server_name www.DOMAIN_NAME.com DOMAIN_NAME.com;

    root /root/Test/build;  // Path of the file and if created by vite then ( build - dist )

    location / {
        try_files $uri $uri/ /index.html;
    }
}
```

### Example : 
```bash
server {
    listen 80;
        server_name www.test.in test.in;

    root /root/Test/build;

    location / {
        try_files $uri $uri/ =404;
    }
}
```
- Then run the command : 
```bash
sudo nginx -t // for configure and test the file
```
### Step 6:
Create a `.conf` file in `sites-enabled` with the same name as the file created in `sites-available` .

Commands to create this file:
1. `cd ../sites-enabled`
2. `ln -s ../sites-available/<server_name>.conf`.
3. `sudo nginx -t` // configure and test 

### Step 7:
Request for SSL certificate .
Command : 
```bash
sudo certbot --nginx -d DOMAIN_NAME.com -d www.DOMAIN_NAME.com
```
Example : `sudo certbot --nginx -d test.com -d www.test.com`

### Step 8:
Restart the nginx 
```bash
sudo systemctl restart nginx
```
### Congratulations 🥳 , Your Frontend is live 



# Update FrontEnd

### Step 1 :
Update the code by git Repository.
```bash
git pull
```

### Step 2 : 
Update the client by installing it.
```nginx
npm install
```
### Step 3 : 
Delete the OLD build folder.
```nginx
rm -r build
```
Now Create a new BUILD folder.
```nginx
npm run build
```
### Step 4 :
Restart the System
```bash
sudo systemctl restart nginx
```
