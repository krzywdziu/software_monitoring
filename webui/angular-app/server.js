const express = require('express');
const app = express();
const path = require('path');
const bodyParser = require("body-parser");
const cors = require('cors');

const port = 8080;

app.use(bodyParser.json());
app.use(cors());

var distDir = path.join(__dirname, "/dist/");
app.use(express.static(distDir));

app.listen(port, () => {
  console.log(`Serwer uruchomiony na porcie ${port}`);
});
