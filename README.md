<h1>Chatop API</h1>

<h1>Installation</h1>

<h2>Step 1 - Prerequistes</h2>
<br>
<p>Make sure the following softs are installed</p>
<ul>
    <li>Java JDK 17 : <a href="https://www.oracle.com/fr/java/technologies/downloads/#jdk17-windows">download here</a></li>
    <li>Maven: <a href="https://maven.apache.org/download.cgi">download here</a></li>
    <li>MySQL >= 8 : <a href="https://dev.mysql.com/downloads/installer/">download here</a></li>
    <li>A MySQL GUI Tool (optionnal) : <a href="https://dev.mysql.com/downloads/workbench/">download here</a></li>
    <li>Node >= 16 and npm 8 : <a href="https://nodejs.org/en/download/">download here</a></li>
</ul>
When installing MySQL, you will be asked for default credentials, choose them and add them to your environment variables as following :
<ul>
    <li>spring.datasource.username: (username)</li>
    <li>spring.datasource.password: (password)</li>
</ul>
<br>
<h2>Step 2 - Database creation</h2>
<br>
<ul>
    <li>Start MySQL</li>
    <li>Start your MySQL GUI tool and connect to your MySQL</li>
    <li>Create the BDD by importing the SQL script located in ./resources/script.sql</li>
</ul>
<br>
<h2>Step 3 - Security</h2>
<br>
Set a JWT secret in your environment variables as following :
<ul>
    <li>jwt.secret: (asecret)</li>
</ul>
<br>
<h2>Step 4 - File hosting</h2>
<br>
<ul>
    <li>In your terminal run `npm install -g http-server`</li>
    <li>Go to the root of the project with the terminal</li>
    <li>Then run `http-server ./`</li>
</ul>
<br>
<h2>Step 5 - Start the API</h2>
Once everything running fine you can start the Spring boot app.<br>
Go to the root of the project and run `mvn spring-boot:run`
<br><br>
<h1>Documentation</h1>
While the API is running you can access its swagger documentation at : <a href="http://localhost:3001/api/swagger-ui/">http://localhost:3001/api/swagger-ui/</a>
