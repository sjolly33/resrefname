'use strict'

var application_root = __dirname,
    express = require('express'),
    path = require('path'),
    bodyParser  = require('body-parser')

var app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));
app.use(express.static(path.join(application_root ,'../app/')));

var port = 4711;
app.listen(port, function () {
    'use strict'
    console.log('Express server listening on port %d in %s mode', port, app.settings.env)
});