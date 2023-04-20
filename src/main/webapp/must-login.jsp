<!doctype html>
<html lang="en">
<head>
    <link rel="icon" href="folder.png" type="image/png">
    <title>Access Denied</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        .container {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            padding: 20px;
            width: 400px;
            margin: 50px auto;
            box-sizing: border-box;
            text-align: center;
        }

        h2 {
            margin-top: 0;
            color: #d9534f;
        }

        p {
            margin-bottom: 20px;
            color: #666;
        }

        button {
            background-color: #d9534f;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
        }

        button:hover {
            background-color: #c9302c;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Access denied</h2>
    <p id="message">System cannot recognise you. <br><br/> Log in and try again.</p>
    <button onclick="window.location.href='index.jsp'">Log in</button>
</div>
</body>
</html>