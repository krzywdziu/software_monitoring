const express = require('express');
const app = express();
const path = require('path');
const port = 3000;

// public directory setup
app.use(express.static('public'));
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'templates', 'home.html'));
});

app.listen(port, () => {});
