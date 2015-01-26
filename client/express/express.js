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

var sendRes = function(res, body){
  console.log(body);
  res.send(body);
}

// =========================== MUSEUM =============================

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
  var newurl = path+'delete/museum/'+req.params.id;
  request({
    headers: {'content-type' : 'application/json'},
    method: 'DELETE',
    url: newurl
    }, function(error, response, body){
        console.log(body);
    }
  )
})

// =========================== WORK =============================

app.get('/api/work/:id', function(req,res) {
  console.log('get work ' + req.params.id)
  var newurl = path+'paint/'+req.params.id;
  request.get(newurl, function(error, response, body){
    if(body){
      sendRes(res, body);
      return;
    }
    else{
      newurl = path+'sculpture/'+req.params.id;
      request.get(newurl, function(error, response, body){
        sendRes(res, body);
      })
    }
  })
});

app.post('/api/work', function(req,res) {
  console.log('post work')
  var newurl = "";
  if(req.body.type == "paint"){
    newurl = path+'new/paint';
  }
  else
     newurl = path+'new/sculpture';
  request.post({
    headers: {'content-type' : 'application/json'},
    url: newurl,
    json: req.body
    }, function(error, response, body){
        console.log(body);
    }
  )
})

app.put('/api/work/:id', function(req,res) {
  console.log('put work')
  var newurl = "";
  if(req.body.type == "paint"){
    newurl = path+'update/paint';
  }
  else
    newurl = path+'update/sculpture';
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

app.delete('/api/work/:id', function(req,res) {
  console.log('delete work')
  var newurl = "";
  if(req.body.type == "paint"){
    newurl = path+'delete/paint/'+req.params.id;
  }
  else
    newurl = path+'delete/sculpture/'+req.params.id;
  request({
    headers: {'content-type' : 'application/json'},
    method: 'DELETE',
    url: newurl
    }, function(error, response, body){
        console.log(body);
    }
  )
})

// =========================== PICTURE =============================

app.get('/api/picture/:id', function(req,res) {
  console.log('get picture ' + req.params.id)
  var newurl = path+'picture/'+req.params.id;
  request.get(newurl, function(error, response, body){
    res.send(body)
  })
});

app.post('/api/picture', function(req,res) {
  console.log('post picture')
  var newurl = path+'new/picture';
  request.post({
    headers: {'content-type' : 'application/json'},
    url: newurl,
    json: req.body
    }, function(error, response, body){
        console.log(body);
    }
  )
})

app.put('/api/picture/:id', function(req,res) {
  console.log('put picture')
  var newurl = path+'update/picture';
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

app.delete('/api/picture/:id', function(req,res) {
  console.log('delete picture')
  var newurl = path+'delete/picture/'+req.params.id;
  request({
    headers: {'content-type' : 'application/json'},
    method: 'DELETE',
    url: newurl
    }, function(error, response, body){
        console.log(body);
    }
  )
})

// =========================== AUTHOR =============================

app.get('/api/author/:id', function(req,res) {
  console.log('get author ' + req.params.id)
  var newurl = path+'author/'+req.params.id;
  request.get(newurl, function(error, response, body){
    res.send(body)
  })
});

app.post('/api/author', function(req,res) {
  console.log('post author')
  var newurl = path+'new/author';
  request.post({
    headers: {'content-type' : 'application/json'},
    url: newurl,
    json: req.body
    }, function(error, response, body){
        console.log(body);
    }
  )
})

app.put('/api/author/:id', function(req,res) {
  console.log('put author')
  var newurl = path+'update/author';
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

app.delete('/api/author/:id', function(req,res) {
  console.log('delete author')
  var newurl = path+'delete/author/'+req.params.id;
  request({
    headers: {'content-type' : 'application/json'},
    method: 'DELETE',
    url: newurl
    }, function(error, response, body){
        console.log(body);
    }
  )
})

// =========================== COLLECTION =============================

app.get('/api/collection/:id', function(req,res) {
  console.log('get collection ' + req.params.id)
  var newurl = path+'collectionWork/'+req.params.id;
  request.get(newurl, function(error, response, body){
    if(body){
      sendRes(res, body);
      return;
    }
    else{
      newurl = path+'collectionPicture/'+req.params.id;
      request.get(newurl, function(error, response, body){
        sendRes(res, body);
      })
    }
  })
});

app.post('/api/collection', function(req,res) {
  console.log('post collection')
  var newurl = "";
  if(req.body.type == "collectionWork"){
    newurl = path+'new/collectionWork';
  }
  else
    newurl = path+'new/collectionPicture';
  request.post({
    headers: {'content-type' : 'application/json'},
    url: newurl,
    json: req.body
    }, function(error, response, body){
        console.log(body);
    }
  )
})

app.put('/api/collection/:id', function(req,res) {
  console.log('put collection')
  var newurl = "";
  if(req.body.type == "collectionWork"){
    newurl = path+'update/collectionWork'; 
  }
  else
    newurl = path+'update/collectionPicture';
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

app.delete('/api/collection/:id', function(req,res) {
  console.log('delete colelction')
  var newurl = "";
  if(req.body.type == "collectionWork"){
    newurl = path+'delete/collectionWork/'+req.params.id;
  }
  else
    newurl = path+'delete/collectionPicture/'+req.params.id;
  request({
    headers: {'content-type' : 'application/json'},
    method: 'DELETE',
    url: newurl
    }, function(error, response, body){
        console.log(body);
    }
  )
})

// =========================== REPRODUCTION =============================

app.get('/api/reproduction/:id', function(req,res) {
  console.log('get reproductionPaint ' + req.params.id)
  var newurl =  path+'reproductionPaint/'+req.params.id;
  request.get(newurl, function(error, response, body){
    if(body){
      sendRes(res, body);
      return;
    }
    else{
      newurl = path+'reproductionSculpture/'+req.params.id;;
      request.get(newurl, function(error, response, body){
        sendRes(res, body);
      })
    }
  })
});

app.post('/api/reproduction', function(req,res) {
  console.log('post reproduction')
  var newurl = "";
  if(req.body.type == "reproductionPaint"){
    newurl = path+'new/reproductionPaint';
  }
  else
    newurl = path+'new/reproductionSculpture';
  request.post({
    headers: {'content-type' : 'application/json'},
    url: newurl,
    json: req.body
    }, function(error, response, body){
        console.log(body);
    }
  )
})

app.put('/api/reproduction/:id', function(req,res) {
  console.log('put reproduction')
  var newurl = ""
  if(req.body.type == "reproductionPaint"){
    newurl = path+'update/reproductionPaint';
  }
  else{
    newurl = path+'update/reproductionSculpture';
  }
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

app.delete('/api/reproduction/:id', function(req,res) {
  console.log('delete reproduction')
  var newurl = ""
  if(req.body.type == "reproductionPaint"){
    newurl = path+'delete/reproductionPaint/'+req.params.id;
  }
  else
    newurl = path+'delete/reproductionSculpture/'+req.params.id;    
  request({
    headers: {'content-type' : 'application/json'},
    method: 'DELETE',
    url: newurl
    }, function(error, response, body){
        console.log(body);
    }
  )
})