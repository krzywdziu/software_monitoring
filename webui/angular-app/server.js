const express = require('express');
const app = express();
const path = require('path');
const bodyParser = require("body-parser");
const port = 8080;

app.use(bodyParser.json());

var distDir = path.join(__dirname, "/dist/");
app.use(express.static(distDir));

/*
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'src', 'app', 'home', 'index.html'));
});
*/

app.listen(port, () => {
  console.log(`Serwer uruchomiony na porcie ${port}`);
});
