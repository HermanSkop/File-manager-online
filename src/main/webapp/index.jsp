<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="folder.png" type="image/png">
  <title>Login Page</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: Arial, sans-serif;
      background-color: #f2f2f2;
    }

    form {
      background-color: #fff;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
      padding: 20px;
      width: 400px;
      margin: 50px auto;
      box-sizing: border-box;
    }

    h2 {
      text-align: center;
      margin-top: 0;
    }

    input[type="text"], input[type="password"] {
      padding: 10px;
      margin-bottom: 20px;
      width: 100%;
      border: none;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
      box-sizing: border-box;
    }

    input[type="submit"] {
      background-color: #657585;
      color: white;
      border: none;
      border-radius: 5px;
      padding: 10px;
      width: 100%;
      cursor: pointer;
    }

    input[type="submit"]:hover {
      background-color: #788a9a;
    }

    p {
      text-align: center;
    }

    a {
      color: #5c7ccc;
      text-decoration: none;
    }
  </style>
</head>
<body>
<form action="blogin-servlet" method="post">
  <h2>Login Page</h2>
  <label for="username">Username:</label>
  <input type="text" id="username" name="username" placeholder="Enter your username">
  <label for="password">Password:</label>
  <input type="password" id="password" name="password" placeholder="Enter your password">
  <input type="submit" value="Login">
  <p>Don't have an account? <a href="signup.jsp">Sign up</a></p>
</form>
</body>
</html>


