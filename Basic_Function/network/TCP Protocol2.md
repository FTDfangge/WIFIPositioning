# TCP Protocol

----------------------------

## User information transmission

### Register

|direction|Message type|
|-|-|
|client->server|"R" + # + "name" + # + "password"|
|||
|server->client|"YR" + # + "id"|
|server->client|"NR" + # + "Name already exists"|

### Log in
|direction|Message type|
|-|-|
|client->server|"L" + # + "id" + # + "password"|
|||
|server->client|"YL" + # + "id" + # + "password" + # + "name" + # + "number" + # + "personality"|
|server->client|"NL" + # + "user id doesn't exist"|
|server->client|"NL" + # + "password is wrong"|


### Modify information
|direction|Message type|
|-|-|
|client->server|"M1" + # + "new password"|
|client->server|"M2" + # + "new name" + # + "new number" + # + "new personality"|
|client->server|"M3"|
|client->server|"picture"|
|||
|server->client|"YM1" + # + "password"|
|server->client|"NM1" + # + "Something error"|
|server->client|"YM2" + # + "name" + # + "number" + # + "personality"|
|server->client|"NM2" + # + "Something error"|
|server->client|"YM3"|
|server->client|"NM3" + # + "Something error"|

----------------------------

## Position Query

### Basic information
|direction|Message type|
|-|-|
|client->server|"Q" + # + "room id"|
|||
|server->client|"YQ" + # + "No feedback"|
|server->client|"YQ" + # + "name" + # + "feedback"|
|server->client|"NQ" + # + "Query failed"|

### Add feedback
|direction|Message type|
|-|-|
|client->server|"F" + # + "room id" + # + "user name" + # + "feedback"|
|||
|server->client|"YF"|
|server->client|"NF" + # + "Something error"|

### Malfunction remind
|direction|Message type|
|-|-|
|client->server|"E" + # + "room id" + # + "explain"|
