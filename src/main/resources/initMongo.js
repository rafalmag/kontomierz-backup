// unique keys
db.user_accounts.createIndex( { "id": 1 }, { unique: true } )
db.tags.createIndex( { "id": 1 }, { unique: true } )
db.currencies.createIndex( { "id": 1 }, { unique: true } )
