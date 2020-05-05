CREATE TABLE reports (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, report VARCHAR(200));
ALTER USER 'root' IDENTIFIED WITH mysql_native_password BY 'helloworld';
CREATE USER 'dev' IDENTIFIED WITH mysql_native_password BY 'helloworld';
GRANT ALL PRIVILEGES ON goapp.* TO 'dev';
FLUSH PRIVILEGES;