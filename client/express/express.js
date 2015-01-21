'use strict'

var application_root = __dirname,
    express = require('express'),
    path = require('path'),
    bodyParser  = require('body-parser'),
    request = require('request')


var app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));
app.use(express.static(path.join(application_root ,'../app/')));

var port = 4711;
app.listen(port, function () {
    'use strict'
    console.log('Express server listening on port %d in %s mode', port, app.settings.env)
});

var path = 'http://localhost:8080/rest/museum/'

app.get('/api/museum', function(req,res) {
  console.log('get museums')
  var newurl = path + 'museums';
  request.get(newurl, function(error, response, body){
    res.send(body)
  })
});

app.get('/api/museum/:id', function(req,res) {
  console.log('get museum ' + req.params.id)
  var newurl = path+req.params.id;
  request.get(newurl, function(error, response, body){
    res.send(body)
  })
});

app.post('/api/museum', function(req,res) {
  console.log('post museum')
  console.log(req.body)
  var newurl = path+'new/museum';
  request.post({
    headers: {'content-type' : 'application/json'},
    url: newurl,
    json: req.body
    }, function(error, response, body){
        console.log(body);
    }
  )
})

app.put('/api/museum/:id', function(req,res) {
  console.log('put museum')
  var newurl = path+'update/museum';
  request({
    headers: {'content-type' : 'application/json'},
    method: 'PUT',
    url: newurl,
    json: req.body
    }, function(error, response, body){
        console.log(body);
    }
  )
})

app.delete('/api/museum/:id', function(req,res) {
  console.log('delete museum')
  console.log(req.params.id)
  var newurl = path+'delete/museum';
  request({
    headers: {'content-type' : 'application/json'},
    method: 'DELETE',
    url: newurl,
    json: req.body
    }, function(error, response, body){
        console.log(body);
    }
  )
})