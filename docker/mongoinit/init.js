db.createUser({
  user: "admin",
  pwd: "password",
  roles: [ { role: "readWrite", db: "test" } ]
})

db.users.insert({
  name: "admin"
})