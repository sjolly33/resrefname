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

// =========================== PAINT =============================

app.get('/api/paint/:id', function(req,res) {
  console.log('get paint ' + req.params.id)
  var newurl = path+'paint/'+req.params.id;
  request.get(newurl, function(error, response, body){
    res.send(body)
  })
});

app.post('/api/paint', function(req,res) {
  console.log('post paint')
  var newurl = path+'new/paint';
  request.post({
    headers: {'content-type' : 'application/json'},
    url: newurl,
    json: req.body
    }, function(error, response, body){
        console.log(body);
    }
  )
})

app.put('/api/paint/:id', function(req,res) {
  console.log('put paint')
  var newurl = path+'update/paint';
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

app.delete('/api/paint/:id', function(req,res) {
  console.log('delete paint')
  var newurl = path+'delete/paint/'+req.params.id;
  request({
    headers: {'content-type' : 'application/json'},
    method: 'DELETE',
    url: newurl
    }, function(error, response, body){
        console.log(body);
    }
  )
})

// =========================== SCULPTURE =============================

app.get('/api/sculpture/:id', function(req,res) {
  console.log('get sculpture ' + req.params.id)
  var newurl = path+'sculpture/'+req.params.id;
  request.get(newurl, function(error, response, body){
    res.send(body)
  })
});

app.post('/api/sculpture', function(req,res) {
  console.log('post sculpture')
  var newurl = path+'new/sculpture';
  request.post({
    headers: {'content-type' : 'application/json'},
    url: newurl,
    json: req.body
    }, function(error, response, body){
        console.log(body);
    }
  )
})

app.put('/api/sculpture/:id', function(req,res) {
  console.log('put sculpture')
  var newurl = path+'update/sculpture';
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

app.delete('/api/sculpture/:id', function(req,res) {
  console.log('delete sculpture')
  var newurl = path+'delete/sculpture/'+req.params.id;
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

// =========================== COLLECTION WORK =============================

app.get('/api/collectionWork/:id', function(req,res) {
  console.log('get collectionWork ' + req.params.id)
  var newurl = path+'collectionWork/'+req.params.id;
  request.get(newurl, function(error, response, body){
    res.send(body)
  })
});

app.post('/api/collectionWork', function(req,res) {
  console.log('post collectionWork')
  var newurl = path+'new/collectionWork';
  request.post({
    headers: {'content-type' : 'application/json'},
    url: newurl,
    json: req.body
    }, function(error, response, body){
        console.log(body);
    }
  )
})

app.put('/api/collectionWork/:id', function(req,res) {
  console.log('put collectionWork')
  var newurl = path+'update/collectionWork';
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

app.delete('/api/collectionWork/:id', function(req,res) {
  console.log('delete collectionWork')
  var newurl = path+'delete/collectionWork/'+req.params.id;
  request({
    headers: {'content-type' : 'application/json'},
    method: 'DELETE',
    url: newurl
    }, function(error, response, body){
        console.log(body);
    }
  )
})

// =========================== COLLECTION PICTURE =============================

app.get('/api/collectionPicture/:id', function(req,res) {
  console.log('get collectionPicture ' + req.params.id)
  var newurl = path+'collectionPicture/'+req.params.id;
  request.get(newurl, function(error, response, body){
    res.send(body)
  })
});

app.post('/api/collectionPicture', function(req,res) {
  console.log('post collectionPicture')
  var newurl = path+'new/collectionPicture';
  request.post({
    headers: {'content-type' : 'application/json'},
    url: newurl,
    json: req.body
    }, function(error, response, body){
        console.log(body);
    }
  )
})

app.put('/api/collectionPicture/:id', function(req,res) {
  console.log('put collectionPicture')
  var newurl = path+'update/collectionPicture';
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

app.delete('/api/collectionPicture/:id', function(req,res) {
  console.log('delete collectionPicture')
  var newurl = path+'delete/collectionPicture/'+req.params.id;
  request({
    headers: {'content-type' : 'application/json'},
    method: 'DELETE',
    url: newurl
    }, function(error, response, body){
        console.log(body);
    }
  )
})


