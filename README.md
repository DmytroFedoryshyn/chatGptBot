<h1>ChatGptBot</h1>

<p>ChatGptBot is a versatile Telegram bot seamlessly integrated with ChatGPT. It facilitates user interactions with ChatGPT through a user-friendly bot interface. Administrators, on the other hand, enjoy additional functionalities such as registration, sending messages to users via a web interface, and accessing chat history.</p>

<p>The bot intelligently saves the recent conversation history, enabling ChatGPT to retain context and enhancing the overall user experience. It automatically truncates messages that surpass the ChatGPT API limit, ensuring smooth and efficient communication.</p>

<h2>Technologies Used</h2>
<ul>
    <li>Java 17</li>
    <li>Spring Boot</li>
    <li>Spring Security</li>
    <li>Hibernate</li>
    <li>Tomcat</li>
    <li>PostgreSQL</li>
    <li>REST</li>
    <li>JSON</li>
    <li>Jackson</li>
    <li>Maven</li>
</ul>

<h2>Web API Description</h2>
<table>
    <thead>
        <tr>
            <th>Endpoint link</th>
            <th>HTTP method</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>/api/auth/register</td>
            <td>POST</td>
            <td>Registration Endpoint</td>
        </tr>
        <tr>
            <td>/api/auth/login</td>
            <td>POST</td>
            <td>Login Endpoint</td>
        </tr>
        <tr>
            <td>/api/admin/send-message/{chatId}</td>
            <td>POST</td>
            <td>Allows administrators to send messages to users</td>
        </tr>
        <tr>
            <td>/api/admin/message-history/{chatId}</td>
            <td>GET</td>
            <td>Retrieve chat history by Telegram chat ID</td>
        </tr>
        <tr>
            <td>/api/admin/telegram_users/</td>
            <td>GET</td>
            <td>Get information about all Telegram users</td>
        </tr>
        <tr>
            <td>/api/admin/telegram_user/{chatId}</td>
            <td>GET</td>
            <td>Get information about a specific Telegram user</td>
        </tr>
    </tbody>
</table>

<h2>Installation</h2>
<h3>Prerequisites</h3>
<ul>
    <li>Install Angular to run the web client.</li>
    <li>For local testing:
        <ul>
            <li>Install Java.</li>
            <li>Install PostgreSQL and create a database named <code>chatGptBotDB</code>.</li>
        </ul>
    </li>
</ul>

<h2>How to Install and Use the Project</h2>
<h3>To Run the Backend Part:</h3>
<ol>
    <li>Generate an executable JAR file using the command: <code>mvn clean package</code>.</li>
    <li>Navigate to the target folder: <code>cd target</code>.</li>
    <li>Execute the JAR file: <code>java -jar ChatGPTBot-1.0-SNAPSHOT.jar</code>.</li>
</ol>

<h3>To Run the Frontend Part:</h3>
<ol>
    <li>Open a new terminal instance.</li>
    <li>Run the command: <code>ng serve</code>.</li>
    <li>Open the browser at <code>localhost:4200/login</code>.</li>
</ol>
